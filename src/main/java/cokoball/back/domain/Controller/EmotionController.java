package cokoball.back.domain.Controller;

import cokoball.back.domain.DTO.EmotionDTO;
import cokoball.back.domain.Service.EmotionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/api/emotions")
public class EmotionController {

    private final EmotionService emotionService;

    public EmotionController(EmotionService emotionService) {
        this.emotionService = emotionService;
    }

    @GetMapping
    public ResponseEntity<List<EmotionDTO>> getAllEmotions() {
        // 인증된 사용자 정보 가져오기
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // 사용자 정보가 없거나 인증되지 않았을 경우 401 Unauthorized 반환
        if (authentication == null || !authentication.isAuthenticated()) {
            return ResponseEntity.status(401).build(); // 401 Unauthorized
        }

        // 인증된 사용자일 경우 로직 실행
        List<EmotionDTO> emotions = emotionService.getAllEmotions();
        return ResponseEntity.ok(emotions);
    }
}
