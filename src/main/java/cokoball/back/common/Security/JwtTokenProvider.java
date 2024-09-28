package cokoball.back.common.Security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtTokenProvider {
    private final String SECRET_KEY = "Your64ByteLongSecretKeyWhichIsVerySecureAndLongEnough!214124eyfiusdufy82931fdf";
    private final Key key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes()); // Key 생성
    private final long EXPIRATION_TIME = 86400000; // 1일 (밀리초 기준)

    // 토큰 생성 메서드
    public Claims getClaimsFromToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key) // Signing key 설정
                .build() // 빌드
                .parseClaimsJws(token) // 토큰 파싱
                .getBody(); // 클레임 반환
    }

    public String createToken(String username) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + EXPIRATION_TIME);

        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(key, SignatureAlgorithm.HS512) // 키와 알고리즘 사용
                .compact();
    }

    // 토큰에서 사용자 정보 추출
    public String getUsernameFromToken(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key) // Key 사용
                .build()
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject(); // username 추출
    }

    // 토큰 유효성 검증
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(key) // Key 사용
                    .build()
                    .parseClaimsJws(token);
            return true; // 유효한 토큰
        } catch (Exception e) {
            return false; // 서명 불일치 또는 기타 예외 처리
        }
    }
}
