package com.gupta.udapi.config;

import com.gupta.udapi.constants.ConstantStrings;
import com.gupta.udapi.handlers.CustomHeaderExtractionFilter;
import com.gupta.udapi.handlers.JwtFilter;
import com.gupta.udapi.handlers.RequestLoggerFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author amitkumargupta
 */
@Component
public class AppConfig implements WebMvcConfigurer {

    @Autowired
    private JwtFilter jwtFilter;

    @Autowired
    private RequestLoggerFilter requestLoggerFilter;

    @Autowired
    private CustomHeaderExtractionFilter customHeaderExtractionFilter;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(jwtFilter).addPathPatterns(
                ConstantStrings.JWT_FILTER_INCLUDE_PATHS)
                .excludePathPatterns(ConstantStrings.JWT_FILTER_EXCLUDE_PATHS);

        registry.addInterceptor(requestLoggerFilter).addPathPatterns(
                ConstantStrings.JWT_FILTER_INCLUDE_PATHS)
                .excludePathPatterns();

        registry.addInterceptor(customHeaderExtractionFilter).addPathPatterns(
                ConstantStrings.DB_FILTER_INCLUDE_PATHS)
                .excludePathPatterns();
    }
}
