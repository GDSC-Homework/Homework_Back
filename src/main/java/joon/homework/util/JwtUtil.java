package joon.homework.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import joon.homework.entity.User;
import joon.homework.exception.InvalidJwtException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {

    public final static long TOKEN_VALIDATION_SECOND = 1L * 60 * 60 * 24; // 1일

    @Value("${spring.jwt.secret}")
    private String SECRET_KEY;

    private Key getSigningKey(String secretKey) {
        byte[] keyBytes = secretKey.getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public Claims extractAllClaims(String token) throws ExpiredJwtException {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey(SECRET_KEY))
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public Long getId(String token) {
        try {
            return extractAllClaims(token).get("id", Long.class);
        } catch (Exception e) {
            throw new InvalidJwtException();
        }
    }

    public Boolean isTokenExpired(String token) {
        final Date expiration = extractAllClaims(token).getExpiration();
        return expiration.before(new Date());
    }

    public String generateToken(User user) {
        return doGenerateToken(user.getId(), TOKEN_VALIDATION_SECOND);
    }

    public String doGenerateToken(Long id, long expireTime) {

        Claims claims = Jwts.claims();
        claims.put("id", id);

        String jwt = Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expireTime * 1000))
                .signWith(getSigningKey(SECRET_KEY), SignatureAlgorithm.HS256)
                .compact();

        return jwt;
    }

    public Boolean validateToken(String token, User user) {
        Boolean result = false;

        try {
            final Long username = getId(token);
            result = username.equals(user.getId()) && !isTokenExpired(token);
        } catch (Exception e) {
            throw new InvalidJwtException();
        }

        return result;
    }
}
