package com.example.jwt_study.global.util;

import com.example.jwt_study.global.service.UserDetailsServiceImpl;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.servlet.util.matcher.PathPatternRequestMatcher;

import java.util.Arrays;

public class SecurityUtil {

    public static UserDetailsServiceImpl getUserDetailsFromSecurityContext() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication==null){
           throw new RuntimeException("로그인 후 이용하셈") ;
        }

        if (!(authentication.getPrincipal() instanceof UserDetailsServiceImpl)) {
            throw new RuntimeException("로그인 후 이용하셈");
        }

        return (UserDetailsServiceImpl) authentication.getPrincipal();
    }

    public static PathPatternRequestMatcher[] permitMatchers() {
        String[] matchers = {
                "/auth/login",
                "/auth/logout",
                "/actuator/**",
                "/error/**"
        };

        PathPatternRequestMatcher.Builder mvc = PathPatternRequestMatcher.withDefaults();
        return Arrays.stream(matchers)
                .map(mvc::matcher)
                .toArray(PathPatternRequestMatcher[]::new);
    }

}
