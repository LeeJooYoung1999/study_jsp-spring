package org.scoula.security.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

@Component
public class JwtProcessor {
    static private final long TOKEN_VALID_MILISECOND = 1000L * 60 * 5; // 5 분

    private final String secretKey = "충분히긴임의의(랜덤한) 비밀키문자열배정";
    private final Key key = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));

    //private Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);  -- 운영시 사용


    //1.
    // JWT 생성 - 토큰만들기
    public String generateToken(String subject) {
        return Jwts.builder()   //빌더로 토큰만들기.
                .setSubject(subject)
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + TOKEN_VALID_MILISECOND))
                .signWith(key)
                .compact();
    }

    //2.
    //토큰에서 subject 추출(usename id)
    // JWT Subject(username) 추출- 해석 불가인 경우 예외발생
    // 예외 ExpiredJwtException, UnsupportedJwtException, MalformedJwtException, SignatureException,
    // IllegalArgumentException
    public String getUsername(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();

    }

    //3.
    // JWT 검증(유효기간검증) -해석불가인경우예외발생
    public boolean validateToken(String token) {
        Jws<Claims> claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token);
        return true;
    }
}
