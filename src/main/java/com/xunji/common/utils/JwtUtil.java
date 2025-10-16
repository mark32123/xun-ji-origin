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
    SecretKey key = getSigningKey(secretKey);
    long expMillis = System.currentTimeMillis() + ttlMillis;
    Date exp = new Date(expMillis);

    JwtBuilder builder = Jwts.builder()
            .setClaims(claims)
            .signWith(key)  // 移除SignatureAlgorithm参数，让jjwt自动处理
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
            SecretKey key = getSigningKey(secretKey);
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
            return claims;
        } catch (Exception e) {
            log.error("JWT解析异常: ", e);
            throw e;
        }
    }

    // 在 JwtUtil 类中创建统一的密钥处理方法
// 在 JwtUtil.getSigningKey 方法中添加日志
    private static SecretKey getSigningKey(String secretKey) {
        byte[] keyBytes = secretKey.getBytes(StandardCharsets.UTF_8);
        log.info("密钥处理 - 原始: {}, 长度: {}", secretKey, keyBytes.length);
        if (keyBytes.length < 32) {
            keyBytes = DigestUtils.sha256(secretKey);
            log.info("密钥处理 - 扩展后长度: {}", keyBytes.length);
        }
        return Keys.hmacShaKeyFor(keyBytes);
    }


}
