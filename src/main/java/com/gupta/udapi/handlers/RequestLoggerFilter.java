package com.gupta.udapi.handlers;

import com.gupta.udapi.constants.ConstantStrings;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author amitkumargupta
 */
@Component
public class RequestLoggerFilter implements HandlerInterceptor {

    private Logger logger = LogManager.getLogger(RequestLoggerFilter.class.getName());

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        // If the client is behind a proxy
        String remoteAddr = request.getHeader("X-FORWARDED-FOR");

        /*
         * If the client is not behind a proxy, the remote address of the
         *  client machine is found and logged below.
         */
        if (remoteAddr == null || "".equals(remoteAddr)) {
            remoteAddr = request.getRemoteAddr();
        }

        logger.info(ConstantStrings.getLogRequestReceivedMsg(request.getMethod(), remoteAddr));
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
