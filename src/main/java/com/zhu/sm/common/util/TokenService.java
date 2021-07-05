package com.zhu.sm.common.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.stereotype.Component;

/**
 * @anthor: HandSome_ZTon
 * @date: 2021/7/5 15:08
 * @className: TokenService
 * @description:
 */

@Component
public class TokenService {

    private static final String secret = "handsomezhutong";

    /**
     * 生成token
     */
    public String createToken(Long adminId) {
        Algorithm algorithm = Algorithm.HMAC256(secret);
        String token = JWT.create()
                .withIssuer("zhutong")
                .withSubject("登录token")
                .withClaim("adminId", adminId)
                .sign(algorithm);
        return token;
    }

    /**
     * 解析token
     */
    public DecodedJWT verifyToken(String strToken) {
        String token = strToken;
        Algorithm algorithm = Algorithm.HMAC256(secret);
        JWTVerifier verifier = JWT.require(algorithm)
                .withIssuer("zhutong")
                .withSubject("登录token")
                .build();
        DecodedJWT jwt = verifier.verify(token);
        return jwt;
    }


    /**
     * 获得token中的adminId
     */
    public Long getAdminId(String token) {
        DecodedJWT decodedJWT = verifyToken(token);
        Long adminId = decodedJWT.getClaim("adminId").asLong();
        return adminId;
    }
}
