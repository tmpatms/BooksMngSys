package org.ddd.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author dudaidong
 * @date 2023/06/25
 */

@Configuration
public class InterceptorConfigurer implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new Interceptor()).addPathPatterns("/**")
                .excludePathPatterns("/swagger/**")
                .excludePathPatterns("/swagger-resources/**", "/webjars/**", "/v2/**", "/swagger-ui.html/**")
                .excludePathPatterns("/users/login")
                .excludePathPatterns("/books/getbooklist")
                .excludePathPatterns("/books/searchbookbyid")
                .excludePathPatterns("/books/searchbookbyname")
                .excludePathPatterns("/users/registeruser");
    }

}
