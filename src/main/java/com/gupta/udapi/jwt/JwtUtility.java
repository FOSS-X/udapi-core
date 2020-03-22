package com.gupta.udapi.jwt;

import com.gupta.udapi.constants.ConstantStrings;
import com.gupta.udapi.exception.InvalidJwtTokenException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

/**
 * @author amitkumargupta
 */
@Service
public class JwtUtility {

    public static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60;

    private Logger logger = LogManager.getLogger(JwtUtility.class.getName());

    @Value("${jwt.secret}")
    private String secret;

    /**
     * Take a Map of claims (Jwt body), and uses the builder of the Jwts library to generate a new token.
     * @param claimsMap
     * @return
     */
    public String getNewTokenFromClaims(Map<String, Object> claimsMap) {
        return Jwts.builder().setClaims(claimsMap).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
                .signWith(SignatureAlgorithm.HS512, secret).compact();
    }

    /**
     * Takes in a token and if it is valid (i.e, if the secret based signature is valid) the claims are returned.
     * If not, InvalidJwtTokenException is thrown.
     * @param token
     * @return
     */
    public Jws<Claims> validateAndParseJwt(String token) {

        Jws<Claims> claimsJws;
        try {
            claimsJws = Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
        }catch (Exception e) {
            logger.fatal(ConstantStrings.getJwtParseFailedErrorMsg(token));
            throw new InvalidJwtTokenException(ConstantStrings.getJwtParseFailedErrorMsg(token));
        }

        return claimsJws;
    }
}
