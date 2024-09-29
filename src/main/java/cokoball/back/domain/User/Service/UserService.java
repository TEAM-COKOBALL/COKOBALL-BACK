package cokoball.back.domain.User.Service;

import cokoball.back.common.Security.JwtTokenProvider;
import cokoball.back.domain.User.Entity.User;
import cokoball.back.domain.User.Repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;
import java.util.HashMap;
import java.util.Map;

@Service
@Transactional(readOnly = true)
@Slf4j
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Transactional
    public String signUp(String username, String password, String passwordConfirm) {
        Optional<User> existingUser = userRepository.findByUsername(username);
        if (existingUser.isPresent()) {
            return "아이디가 이미 존재합니다. 다른 아이디를 선택하세요.";
        }

        // 비밀번호와 비밀번호 확인이 같은지 체크
        if (!password.equals(passwordConfirm)) {
            return "비밀번호와 비밀번호 확인이 일치하지 않습니다.";
        }

        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        userRepository.save(user);

        return "회원가입 성공";
    }

    public Map<String, Object> loginWithUserInfo(String username, String password) {
        Optional<User> user = userRepository.findByUsername(username);
        Map<String, Object> response = new HashMap<>();

        if (user.isPresent() && passwordEncoder.matches(password, user.get().getPassword())) {
            // 로그인 성공 시 토큰 생성 및 반환
            String token = jwtTokenProvider.createToken(username);
            log.info("Generated Token for {}: {}", username, token);  // 토큰 로그 출력

            // 토큰과 함께 사용자 정보도 반환
            response.put("status", "success");
            response.put("token", token);
            response.put("username", username);
            response.put("message", "로그인 성공");
            return response;
        } else {
            response.put("status", "error");
            response.put("token", "아이디 또는 비밀번호가 틀렸습니다.");
            response.put("message", "로그인 실패");
            return response;
        }
    }
}
