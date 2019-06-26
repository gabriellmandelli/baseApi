package org.start.baseApi.util.authentication;

import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.start.baseApi.configuration.CustomFilter;

import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.DatatypeConverter;
import java.util.Date;

public class ProjectToken {

    private static final Logger logger = LoggerFactory.getLogger(CustomFilter.class);
    private static final String CHAVE = "devBaseApi";

    public String genereteToken(String usuario) {

        Date timeExpiration = new Date(new Date().getTime() + 30 * 6000 * 1000);

        String jwtToken = Jwts.builder()
                .setSubject(usuario)
                .setIssuer("teste")
                .setIssuedAt(new Date())
                .setExpiration(timeExpiration)
                .signWith(SignatureAlgorithm.HS512, CHAVE)
                .compact();

        logger.info("Expiration : " + timeExpiration.toString());
        logger.info("#### valid token : " + jwtToken);

        return jwtToken;
    }

    public Boolean validToken(String token){
        Boolean isValid = true;
        try{
            Claims claims = Jwts.parser()
                    .setSigningKey(DatatypeConverter.parseBase64Binary(CHAVE))
                    .parseClaimsJws(token).getBody();

            logger.info("ID: " + claims.getId());
            logger.info("Subject: " + claims.getSubject());
            logger.info("Issuer: " + claims.getIssuer());
            logger.info("Expiration: " + claims.getExpiration());

            logger.info("#### valid token : " + token);

        }catch(MalformedJwtException e){
            logger.info("#### invalid token : " + token);
            isValid = false;
        }catch (Exception e) {
            logger.info("#### invalid token : " + token);
            isValid = false;
        }
        return isValid;
    }
}
