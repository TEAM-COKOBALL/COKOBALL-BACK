package cokoball.back.Security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtTokenProvider {

    private final String SECRET_KEY = "XyZ9@#1$wQp!lMvTg2%6&8aBzC5rF4"; // 비밀키, 실제 사용 시에는 환경변수로 관리하세요.
    private final long EXPIRATION_TIME = 86400000; // 1일 (밀리초 기준)

    // 토큰 생성 메서드
    public String createToken(String username) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + EXPIRATION_TIME);

        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                .compact();
    }

    // 토큰에서 사용자 정보 추출
    public String getUsernameFromToken(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject(); // username 추출
    }

    // 토큰 유효성 검증
    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token);
            return true; // 유효한 토큰
        } catch (SignatureException ex) {
            // 서명 불일치
            return false;
        } catch (Exception e) {
            // 기타 예외 처리 (토큰 만료 등)
            return false;
        }
    }
}
