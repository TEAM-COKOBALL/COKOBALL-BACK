package cokoball.back.common.Security;

import io.jsonwebtoken.JwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        // Authorization 헤더에서 토큰 추출
        try {
            String token = getAccessTokenFromHeader(request);

            log.info("token: {}", token);

            if(token == null || token.isEmpty()) {
                log.info("토큰 검사를 안함.");
                filterChain.doFilter(request, response);
                return ;
            }

            // 토큰 검증
            if (!jwtTokenProvider.validateToken(token)) {
                response.setStatus(HttpStatus.UNAUTHORIZED.value());
                response.getWriter().write("유효하지 않은 토큰입니다.");
                return;
            }

            // 토큰에서 사용자 정보 추출
            String username = jwtTokenProvider.getUsernameFromToken(token);
            request.setAttribute("username", username); // 요청 속성에 사용자 정보 추가

            // 다음 필터로 요청 전달
            filterChain.doFilter(request, response);

        } catch (IOException e) {
            log.debug("jwt 필터 에러 발생");
        } catch (ServletException e) {
            throw new RuntimeException(e);
        }
    }

    private String getAccessTokenFromHeader(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }
}
