package com.gupta.udapi.handlers;

import com.gupta.udapi.constants.ConstantStrings;
import com.gupta.udapi.exception.InvalidJwtTokenException;
import com.gupta.udapi.jwt.JwtUtility;
import io.jsonwebtoken.Claims;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author amitkumargupta
 */
@Component
public class CustomHeaderExtractionFilter implements HandlerInterceptor {

    @Autowired
    private JwtUtility jwtUtility;

    private Logger logger = LogManager.getLogger(CustomHeaderExtractionFilter.class.getName());

    @Value("${udapi.database.header.name}")
    private String dbHeaderName;

    @Value("#{'${jwt.claims}'.split(',')}")
    private List<String> jwtClaims;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        /*
         * Checking to see if the jwt token is present in the header.
         * If not, an exception is thrown.
         */
        String dbType = request.getHeader(dbHeaderName);
        if (dbType == null || dbType.trim().isEmpty()) {
            logger.fatal(ConstantStrings.NO_DBTYPE_IN_HEADER_ERROR_MSG);
            throw new InvalidJwtTokenException(ConstantStrings.NO_DBTYPE_IN_HEADER_ERROR_MSG);
        }

        request.setAttribute("dbType", dbType);

//        // If the token is found, the token is validated. If the token is invalid, the utility throws an exception.
//        Claims claims =  jwtUtility.validateAndParseJwt(dbType).getBody();
//
//        for (String claim: jwtClaims) {
//            try {
//                request.setAttribute(claim, claims.get(claim));
//            }catch (Exception ex) {
//                throw new InvalidJwtTokenException((ConstantStrings.getJwtParseFailedErrorMsg(dbType)) );
//            }
//
//        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
