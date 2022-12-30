package ru.kataaas.ims.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import ru.kataaas.ims.dto.LoginByPhoneNumberDTO;
import ru.kataaas.ims.dto.LoginDTO;
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
public class AuthController {

    private final JwtUtil jwtUtil;

    private final AuthenticationManager authenticationManager;

    private final CustomPersonDetailsService userDetailsService;

    public AuthController(JwtUtil jwtUtil,
                          AuthenticationManager authenticationManager,
                          CustomPersonDetailsService userDetailsService) {
        this.jwtUtil = jwtUtil;
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
    }

    @PostMapping("/user/auth")
    public String createAuthenticationTokenForUser(@Valid @RequestBody LoginByPhoneNumberDTO loginDTO, HttpServletResponse response) {
        authenticate(loginDTO.getLogin(), loginDTO.getPassword());
        return generateJwtAuthToken(loginDTO.getLogin(), ERole.ROLE_USER, response);
    }

    @PostMapping("/vendor/auth")
    public String createAuthenticationTokenForVendor(@Valid @RequestBody LoginByPhoneNumberDTO loginDTO, HttpServletResponse response) {
        authenticate(loginDTO.getLogin(), loginDTO.getPassword());
        return generateJwtAuthToken(loginDTO.getLogin(), ERole.ROLE_VENDOR, response);
    }

    @PostMapping("/employee/auth")
    public String createAuthenticationTokenForEmployee(@Valid @RequestBody LoginDTO loginDTO, HttpServletResponse response) {
        authenticate(loginDTO.getLogin(), loginDTO.getPassword());
        return generateJwtAuthToken(loginDTO.getLogin(), ERole.ROLE_EMPLOYEE, response);
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
