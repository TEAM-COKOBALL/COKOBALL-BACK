package cokoball.back.domain.User.Controller;

import cokoball.back.domain.User.DTO.LoginRequestDTO;
import cokoball.back.domain.User.Service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<Void> signUp(
            @RequestParam String username,
            @RequestParam String password,
            @RequestParam String passwordConfirm) {

        log.debug("username : {}", username);

        String result = userService.signUp(username, password, passwordConfirm);
        Map<String, Object> response = new HashMap<>();

        if (result.equals("회원가입 성공")) {
            response.put("status", "success");
            response.put("message", result);
        } else {
            response.put("status", "error");
            response.put("message", result);
        }
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody LoginRequestDTO requestDTO) {

        log.info("username : {} , password : {}", requestDTO.username(), requestDTO.password());

        String token = userService.login(requestDTO.username(), requestDTO.password());
        Map<String, Object> response = new HashMap<>();

        if (token.equals("아이디 또는 비밀번호가 틀렸습니다.")) {
            response.put("status", "error");
            response.put("message", "아이디 또는 비밀번호가 틀렸습니다.");
            return ResponseEntity.status(401).body(response);  // 401 Unauthorized 반환
        } else {
            response.put("status", "success");
            response.put("token", token);  // 로그인 성공 시 JWT 토큰 반환
            response.put("message", "로그인 성공");
            return ResponseEntity.ok(response);  // 200 OK 응답 반환
        }
    }
}
