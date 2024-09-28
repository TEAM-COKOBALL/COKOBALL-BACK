package cokoball.back.User.Controller;

import cokoball.back.User.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<Map<String, Object>> signUp(
            @RequestParam String username,
            @RequestParam String password) {

        String result = userService.signUp(username, password);
        Map<String, Object> response = new HashMap<>();

        if (result.equals("회원가입 성공")) {
            response.put("status", "success");
            response.put("message", result);
            return ResponseEntity.ok(response);
        } else {
            response.put("status", "error");
            response.put("message", result);
            return ResponseEntity.badRequest().body(response);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestParam String username, @RequestParam String password) {
        String token = userService.login(username, password);
        Map<String, Object> response = new HashMap<>();

        if (token.equals("아이디 또는 비밀번호가 틀렸습니다.")) {
            response.put("status", "error");
            return ResponseEntity.badRequest().body(response);
        } else {
            response.put("status", "success");
            response.put("token", token);  // 로그인 성공 시 JWT 토큰 반환
            response.put("message", "로그인 성공");
            return ResponseEntity.ok(response);
        }
    }

}
