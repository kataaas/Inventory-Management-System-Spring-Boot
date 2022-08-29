package ru.kataaas.ims.config;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.WebUtils;
import ru.kataaas.ims.service.CustomPersonDetailsService;
import ru.kataaas.ims.utils.JwtUtil;
import ru.kataaas.ims.utils.StaticVariable;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtWebConfig extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;

    private final CustomPersonDetailsService userDetailsService;

    public JwtWebConfig(JwtUtil jwtUtil, CustomPersonDetailsService userDetailsService) {
        this.jwtUtil = jwtUtil;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String login;
        String jwtToken = null;
        Cookie cookie = WebUtils.getCookie(request, StaticVariable.SECURE_COOKIE);
        if (cookie != null) {
            jwtToken = cookie.getValue();
        }
        if (jwtToken != null) {
            login = jwtUtil.getLoginFromToken(jwtToken);
            try {
                UserDetails userDetails = userDetailsService.loadUserByUsername(login);
                if (jwtUtil.isValidityToken(jwtToken, userDetails)) {
                    UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    SecurityContextHolder.getContext().setAuthentication(auth);
                }
            } catch (Exception e) {
                filterChain.doFilter(request, response);
                SecurityContextHolder.clearContext();
                return;
            }
        }
        filterChain.doFilter(request, response);
    }
}
