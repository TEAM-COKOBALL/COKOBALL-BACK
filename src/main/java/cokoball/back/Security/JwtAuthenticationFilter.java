package cokoball.back.Security;

import cokoball.back.Security.JwtTokenProvider;
import cokoball.back.User.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        // Authorization 헤더에서 토큰 추출
        String header = request.getHeader("Authorization");
        if (header == null || !header.startsWith("Bearer ")) {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.getWriter().write("Authorization 헤더가 없습니다.");
            return;
        }

        // 토큰 검증
        String token = header.substring(7);
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
    }
}
