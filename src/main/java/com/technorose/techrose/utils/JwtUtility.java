package com.technorose.techrose.utils;

import com.technorose.techrose.models.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.function.Function;

@Service
public class JwtUtility {
    private static final String SECRET_KEY = "4bb6d1dfbafb64a681139d1586b6f1160d18159afd57c8c79136d7490630407c";

    private static long EXPIRATION_TIME = 864_000_000;

    public static boolean isValid(String token, UserDetails user) {
        String username = extractUsername(token);

        return username.equals(user.getUsername()) && !isTokenExpired(token);
    }

    public static String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    private static boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private static Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public static String generateToken(User user) {
        return Jwts
                .builder()
                .subject(user.getUsername())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(getJwtSecretKey())
                .compact();
    }

    public static <T> T extractClaim(String token, Function<Claims, T> resolver) {
        Claims claims = extractAllClaims(token);
        return resolver.apply(claims);
    }

    public static Claims extractAllClaims(String token) {
        return Jwts
                .parser()
                .verifyWith(getJwtSecretKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    private static SecretKey getJwtSecretKey() {
        byte[] bytesKey = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(bytesKey);
    }
}
