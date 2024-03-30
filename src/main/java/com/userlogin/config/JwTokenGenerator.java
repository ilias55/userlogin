package com.userlogin.config;

import com.userlogin.model.UserAuthModel;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.Jwts;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwTokenGenerator {
            public String generateToken(UserAuthModel userAuthModel) {
            String username= userAuthModel.getUserName();
            Date currentDate = new Date();
            Date expiryDate = new Date(currentDate.getTime()+ 70000);

            String token = Jwts.builder()
                    .setSubject(username)
                    .setIssuedAt(currentDate)
                    .setExpiration(expiryDate)
                    .signWith(SignatureAlgorithm.HS256, "pokemonsecretrandomstringwithmorethan256bits")
                    .claim("usertype", "ALL")
                    .compact();
            return token;
        }
}
