package ru.kataaas.ims.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;

@Component
public class JwtUtil {

    @Value("${app.jwt-token-validity}")
    public long JWT_TOKEN_VALIDITY;

    @Value("${app.jwt-secret-token}")
    private String JWT_SECRET_TOKEN;

    public String generateJwtToken(UserDetails userDetails) {
        String login = userDetails.getUsername();
        return Jwts.builder()
                .setSubject(login)
                .claim("Role", userDetails.getAuthorities().stream().iterator().next().getAuthority())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY))
                .signWith(SignatureAlgorithm.HS512, JWT_SECRET_TOKEN)
                .compact();
    }

    public boolean isValidityToken(String token, UserDetails userDetails) {
        String login = getLoginFromToken(token);
        return (login.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    public String getLoginFromToken(String token) {
        return getAllClaimsFromToken(token).getSubject();
    }

    public String getRoleFromToken(String token) {
        return getAllClaimsFromToken(token).get("Role").toString();
    }

    private boolean isTokenExpired(String token) {
        return getExpirationDateFromToken(token).before(new Date());
    }

    private Date getExpirationDateFromToken(String token) {
        return getAllClaimsFromToken(token).getExpiration();
    }

    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(JWT_SECRET_TOKEN)
                .parseClaimsJws(token)
                .getBody();
    }

}
