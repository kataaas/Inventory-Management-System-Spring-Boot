package ru.kataaas.ims.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import ru.kataaas.ims.dto.AuthResponse;
import ru.kataaas.ims.dto.LoginByPhoneNumberDTO;
import ru.kataaas.ims.dto.LoginDTO;
import ru.kataaas.ims.entity.EmployeeEntity;
import ru.kataaas.ims.entity.UserEntity;
import ru.kataaas.ims.entity.VendorEntity;
import ru.kataaas.ims.mapper.EmployeeMapper;
import ru.kataaas.ims.mapper.UserMapper;
import ru.kataaas.ims.mapper.VendorMapper;
import ru.kataaas.ims.service.CustomPersonDetailsService;
import ru.kataaas.ims.service.EmployeeService;
import ru.kataaas.ims.service.UserService;
import ru.kataaas.ims.service.VendorService;
import ru.kataaas.ims.utils.ERole;
import ru.kataaas.ims.utils.JwtUtil;
import ru.kataaas.ims.utils.StaticVariable;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
public class AuthController {

    private final JwtUtil jwtUtil;

    private final UserMapper userMapper;

    private final UserService userService;

    private final VendorMapper vendorMapper;

    private final VendorService vendorService;

    private final EmployeeMapper employeeMapper;

    private final EmployeeService employeeService;

    private final AuthenticationManager authenticationManager;

    private final CustomPersonDetailsService userDetailsService;

    @PostMapping("/user/auth")
    public AuthResponse createAuthenticationTokenForUser(@Valid @RequestBody LoginByPhoneNumberDTO loginDTO, HttpServletResponse response) {
        authenticate(loginDTO.getLogin(), loginDTO.getPassword());
        UserEntity user = userService.findByPhoneNumber(loginDTO.getLogin());
        String token = generateJwtAuthToken(loginDTO.getLogin(), ERole.ROLE_USER, response);
        return userMapper.toAuthResponse(user, token);
    }

    @PostMapping("/vendor/auth")
    public AuthResponse createAuthenticationTokenForVendor(@Valid @RequestBody LoginByPhoneNumberDTO loginDTO, HttpServletResponse response) {
        authenticate(loginDTO.getLogin(), loginDTO.getPassword());
        VendorEntity vendor = vendorService.findByPhoneNumber(loginDTO.getLogin());
        String token = generateJwtAuthToken(loginDTO.getLogin(), ERole.ROLE_VENDOR, response);
        return vendorMapper.toAuthResponse(vendor, token);
    }

    @PostMapping("/employee/auth")
    public AuthResponse createAuthenticationTokenForEmployee(@Valid @RequestBody LoginDTO loginDTO, HttpServletResponse response) {
        authenticate(loginDTO.getLogin(), loginDTO.getPassword());
        EmployeeEntity employee = employeeService.findByLogin(loginDTO.getLogin());
        String token = generateJwtAuthToken(loginDTO.getLogin(), ERole.ROLE_EMPLOYEE, response);
        return employeeMapper.toAuthResponse(employee, token);
    }

    @GetMapping("/logout")
    public ResponseEntity<?> logoutUser(HttpServletResponse response) {
        Cookie cookie = new Cookie(StaticVariable.SECURE_COOKIE, null);
        cookie.setHttpOnly(true);
        cookie.setSecure(false);
        cookie.setPath("/");
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        return ResponseEntity.ok().build();
    }

    private void authenticate(String login, String password) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(login, password));
        } catch (DisabledException e) {
            throw new DisabledException("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("INVALID_CREDENTIALS", e);
        }
    }

    private String generateJwtAuthToken(String login, ERole role, HttpServletResponse response) {
        UserDetails userDetails = userDetailsService.loadByLoginAndRole(login, role);
        String token = jwtUtil.generateJwtToken(userDetails);
        Cookie jwtAuthToken = new Cookie(StaticVariable.SECURE_COOKIE, token);
        jwtAuthToken.setHttpOnly(true);
        jwtAuthToken.setSecure(false);
        jwtAuthToken.setPath("/");
        jwtAuthToken.setMaxAge(2592000); // 30 days
        response.addCookie(jwtAuthToken);
        return token;
    }
}
