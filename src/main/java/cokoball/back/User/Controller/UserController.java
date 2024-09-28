package cokoball.back.User.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;

import java.util.HashMap;
import java.util.Map;

@RestController
public class UserController {

    @GetMapping("/api/protected")
    public ResponseEntity<Map<String, Object>> getProtectedData(HttpServletRequest request) {
        // 필터에서 설정한 사용자 정보 사용
        String username = (String) request.getAttribute("username");

        // JSON 응답 생성
        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");
        response.put("message", "This is protected data");
        response.put("username", username);

        return ResponseEntity.ok(response);
    }
}
