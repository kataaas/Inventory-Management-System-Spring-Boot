package ru.kataaas.ims.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import ru.kataaas.ims.dto.AuthUserResponse;
import ru.kataaas.ims.dto.LoginDTO;
import ru.kataaas.ims.entity.UserEntity;
import ru.kataaas.ims.entity.VendorEntity;
import ru.kataaas.ims.mapper.UserMapper;
import ru.kataaas.ims.mapper.VendorMapper;
import ru.kataaas.ims.service.CustomPersonDetailsService;
import ru.kataaas.ims.service.UserService;
import ru.kataaas.ims.service.VendorService;
import ru.kataaas.ims.utils.ERole;
import ru.kataaas.ims.utils.JwtUtil;
import ru.kataaas.ims.utils.StaticVariable;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Locale;

@RestController
@RequestMapping("/api/v1")
public class AuthController {

    private final JwtUtil jwtUtil;

    private final UserMapper userMapper;

    private final UserService userService;

    private final VendorMapper vendorMapper;

    private final VendorService vendorService;

    private final AuthenticationManager authenticationManager;

    private final CustomPersonDetailsService userDetailsService;

    public AuthController(JwtUtil jwtUtil,
                          UserMapper userMapper,
                          UserService userService,
                          VendorMapper vendorMapper,
                          VendorService vendorService,
                          AuthenticationManager authenticationManager,
                          CustomPersonDetailsService userDetailsService) {
        this.jwtUtil = jwtUtil;
        this.userMapper = userMapper;
        this.userService = userService;
        this.vendorMapper = vendorMapper;
        this.vendorService = vendorService;
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
    }

    @PostMapping("/user/auth")
    public AuthUserResponse createAuthenticationTokenForUser(@Valid @RequestBody LoginDTO loginDTO, HttpServletResponse response) {
        authenticate(loginDTO.getLogin(), loginDTO.getPassword());
        UserEntity user = userService.findByPhoneNumber(loginDTO.getLogin());
        String token = generateJwtAuthToken(loginDTO.getLogin(), ERole.ROLE_USER, response);
        return userMapper.toAuthResponse(user, token);
    }

    @PostMapping("/vendor/auth")
    public AuthUserResponse createAuthenticationTokenForVendor(@Valid @RequestBody LoginDTO loginDTO, HttpServletResponse response) {
        authenticate(loginDTO.getLogin(), loginDTO.getPassword());
        VendorEntity vendor = vendorService.findByPhoneNumber(loginDTO.getLogin());
        String token = generateJwtAuthToken(loginDTO.getLogin(), ERole.ROLE_VENDOR, response);
        return vendorMapper.toAuthResponse(vendor, token);
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
        UserDetails userDetails = userDetailsService.loadByPhoneNumberAndRole(login, role);
        String token = jwtUtil.generateJwtToken(userDetails);
        Cookie jwtAuthToken = new Cookie(StaticVariable.SECURE_COOKIE, token);
        jwtAuthToken.setHttpOnly(true);
        jwtAuthToken.setSecure(false);
        jwtAuthToken.setPath("/");
        jwtAuthToken.setMaxAge(604800000); // 7 days
        response.addCookie(jwtAuthToken);
        return token;
    }
}
