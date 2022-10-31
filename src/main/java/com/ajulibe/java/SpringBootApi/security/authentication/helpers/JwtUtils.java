package com.ajulibe.java.SpringBootApi.security.authentication.helpers;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;


import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;


@Component
public class JwtUtils {
    @Value("${ajulibe.app.jwtSecret}")
    private String jwtSecret;

    @Value("${ajulibe.app.jwtExpirationMs}")
    private int jwtExpirationMs;

    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        //note the username here is the login email
        return doGenerateToken(claims, userDetails.getUsername());
    }


    //retrieve Email from jwt token -- remember the email here is the username
    public String getUsernameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    //retrieve expiration date from jwt token
    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    private Key getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(jwtSecret);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parserBuilder().setSigningKey(getSigningKey()).build().parseClaimsJws(token).getBody();
    }

    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }


    private String doGenerateToken(Map<String, Object> claims, String subject) {
        /*
         * here: we use the recommended JWT method to generate a compliant and safe token
         * when the token is generated, we decode it and then
         * store that decoded result in the properties file
         * **/
        /**
         * Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
         * String secretString = Encoders.BASE64.encode(key.getEncoded());
         * System.out.println("Secret key: " + secretString);
         * @result -- a very secure jwtSecret
         */
        String jwtGenerated;
        try {
            jwtGenerated = Jwts.builder()
                    .setClaims(claims)
                    //setting the subject of the claim to the email
                    .setSubject(subject)
                    .setIssuedAt(new Date(System.currentTimeMillis()))
                    .setExpiration(new Date(System.currentTimeMillis() + jwtExpirationMs))
                    .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                    .compact();
        } catch (JwtException e) {
            throw new JwtException(e.getMessage());
        }
        return jwtGenerated;
    }

    //validate token
    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = getUsernameFromToken(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
}
