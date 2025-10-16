package com.xunji.common.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Map;

@Slf4j
public class JwtUtil {
    /**
     * 生成jwt
     * 使用Hs256算法, 私匙使用固定秘钥
     *
     * @param secretKey jwt秘钥
     * @param ttlMillis jwt过期时间(毫秒)
     * @param claims    设置的信息
     * @return
     */
    public static String createJWT(String secretKey, Long ttlMillis, Map<String, Object> claims) {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        // 确保密钥长度足够，如果不够则进行处理
        byte[] keyBytes = secretKey.getBytes(StandardCharsets.UTF_8);
        // 如果密钥太短，则使用SHA-256哈希扩展
        if (keyBytes.length < 32) {
            keyBytes = DigestUtils.sha256(secretKey);
        }

        SecretKey key = Keys.hmacShaKeyFor(keyBytes);

        long expMillis = System.currentTimeMillis() + ttlMillis;
        Date exp = new Date(expMillis);

        JwtBuilder builder = Jwts.builder()
                .setClaims(claims)
                .signWith(key)
                .setExpiration(exp);

        return builder.compact();
    }

    /**
     * Token解密
     *
     * @param secretKey jwt秘钥 此秘钥一定要保留好在服务端, 不能暴露出去, 否则sign就可以被伪造, 如果对接多个客户端建议改造成多个
     * @param token     加密后的token
     * @return
     */
    public static Claims parseJWT(String secretKey, String token) {
        try {
            byte[] keyBytes = secretKey.getBytes(StandardCharsets.UTF_8);
//            log.info("原始密钥长度: {}", keyBytes.length);
            if (keyBytes.length < 32) {
                keyBytes = DigestUtils.sha256(secretKey);
//                log.info("扩展后密钥长度: {}", keyBytes.length);
            }

            Claims claims = Jwts.parser()
                    .setSigningKey(Keys.hmacShaKeyFor(keyBytes))
                    .parseClaimsJws(token).getBody();
            return claims;
        } catch (Exception e) {
            log.error("JWT解析异常: ", e);
            throw e;
        }
    }


}
