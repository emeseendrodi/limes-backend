/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.limes.backend.security.tokenization;

import com.limes.backend.persistence.entity.Student;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import java.util.function.Function;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Mate Forster
 */
public class JwtService {

    private static String key = "625d4ae6115a6d57b15cf2d8f0fed4bb08c7dfe31225c12d9703ada2af1479f4";
    private static long expirationTimeInMilisec = 3600000;

    public static String extractEmail(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public static <T> T extractClaim(String token, Function<Claims, T> claimsResolver) throws  ExpiredJwtException,SignatureException,UnsupportedJwtException,MalformedJwtException, IllegalArgumentException{
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public static String generateToken(String email) {
        String token = generateToken(new HashMap<>(), email);
        return token;
    }

    public static String generateToken(Map<String, Object> extraClaims, String email) {
        return buildToken(extraClaims, email, expirationTimeInMilisec);
    }

    public static long getExpirationTime() {
        return System.currentTimeMillis() + expirationTimeInMilisec;
    }

    private static String buildToken(Map<String, Object> extraClaims, String email, long expiration) {
        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(email)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public static boolean isTokenValid(String token, Student student) throws  ExpiredJwtException,SignatureException,UnsupportedJwtException,MalformedJwtException, IllegalArgumentException{
        final String email = extractEmail(token);
        return (email.equals(student.getEmail())) && !isTokenExpired(token);
    }

    public static boolean isTokenExpired(String token) throws  ExpiredJwtException,SignatureException,UnsupportedJwtException,MalformedJwtException, IllegalArgumentException{
        return extractExpiration(token).before(new Date());
    }

    private static Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private static Claims extractAllClaims(String token){
        return Jwts.parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private static Key getSignInKey() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(key));
    }
}
