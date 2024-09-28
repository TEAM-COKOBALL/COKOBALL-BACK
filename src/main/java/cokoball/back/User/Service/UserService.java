package cokoball.back.User.Service;

import cokoball.back.Security.JwtTokenProvider;
import cokoball.back.User.Entity.User;
import cokoball.back.User.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    public String signUp(String username, String password) {
        Optional<User> existingUser = userRepository.findByUsername(username);
        if (existingUser.isPresent()) {
            return "아이디가 이미 존재합니다. 다른 아이디를 선택하세요.";
        }

        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        user.setLevel(1);
        userRepository.save(user);

        return "회원가입 성공";
    }

    public String login(String username, String password) {
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isPresent() && passwordEncoder.matches(password, user.get().getPassword())) {
            // 로그인 성공 시 토큰 생성 및 반환
            return jwtTokenProvider.createToken(username);
        } else {
            return "아이디 또는 비밀번호가 틀렸습니다.";
        }
    }
}
