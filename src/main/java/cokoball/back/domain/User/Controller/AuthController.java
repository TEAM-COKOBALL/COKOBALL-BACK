package cokoball.back.domain.User.Controller;

import cokoball.back.domain.User.DTO.LoginRequestDTO;
import cokoball.back.domain.User.DTO.SignupRequestDTO;
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
    public ResponseEntity<Map<String, Object>> signUp(
            @RequestBody SignupRequestDTO requestDTO) {

        String result = userService.signUp(requestDTO.getUsername(), requestDTO.getPassword(), requestDTO.getPasswordConfirm());
        Map<String, Object> response = new HashMap<>();

        if (result.equals("회원가입 성공")) {
            response.put("status", "success");
            response.put("message", result);
            return ResponseEntity.ok(response); // 200 OK 응답과 함께 데이터 반환
        } else {
            response.put("status", "error");
            response.put("message", result);
            return ResponseEntity.badRequest().body(response); // 400 Bad Request 응답과 함께 데이터 반환
        }
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody LoginRequestDTO requestDTO) {

        log.info("username : {} , password : {}", requestDTO.getUsername(), requestDTO.getPassword());

        // 로그인 처리 및 토큰 생성
        Map<String, Object> result = userService.loginWithUserInfo(requestDTO.getUsername(), requestDTO.getPassword());

        // 로그인 실패
        if (result.get("token").equals("아이디 또는 비밀번호가 틀렸습니다.")) {
            return ResponseEntity.status(401).body(result);  // 401 Unauthorized 반환
        } else {
            return ResponseEntity.ok(result);  // 200 OK 응답 반환
        }
    }
}
