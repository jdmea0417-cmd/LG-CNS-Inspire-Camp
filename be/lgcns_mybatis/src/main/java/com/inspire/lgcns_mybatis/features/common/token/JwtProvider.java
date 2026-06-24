package com.inspire.lgcns_mybatis.features.common.token;

import org.springframework.stereotype.Component;

@Component
public class JwtProvider {
    public String createAT(String email) {
        return "Bearer xxxxxx";
    }
    public String createRT(String email) {
        return "xxxxxxxxxxxx";
    }
}
