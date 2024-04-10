package com.example.myproject.util;


import java.security.Key;

import com.example.myproject.model.Account;
import com.example.myproject.repository.AccountRepository;
import com.example.myproject.service.TokenService;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

@Component
public class TokenUtil {

    private static final Logger logger = LogManager.getLogger(TokenUtil.class);
    private static final Key key = Keys.secretKeyFor(io.jsonwebtoken.SignatureAlgorithm.HS256);
    private final AccountRepository accountRepository;

    public TokenUtil(AccountRepository accountRepository){
        this.accountRepository = accountRepository;
    }

    public static String getEmailFromToken(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject();
    }

    public Account getAccountFromToken(String token) {
        try {
            if (token.startsWith("Bearer ")) {
                token = token.substring(7);
            }
            // TokenService parsen und Claims (Daten) auslesen
            Jws<Claims> jws = Jwts.parserBuilder().setSigningKey(TokenService.getKey()).build()
                    .parseClaimsJws(token);
            Claims claims = jws.getBody();
            String email = claims.getSubject();

            return this.accountRepository.findByEmail(email);
        } catch (Exception e) {
            // Bei Fehler oder ungültigem TokenService wird eine Exception geworfen
            logger.warn("Retrieve Account with TokenService failed " + e);
            return null;
        }
    }
}
