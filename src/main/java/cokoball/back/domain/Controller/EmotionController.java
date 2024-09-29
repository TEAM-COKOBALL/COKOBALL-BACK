package cokoball.back.domain.Controller;

import cokoball.back.domain.DTO.EmotionDTO;
import cokoball.back.domain.Service.EmotionService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/emotions")
public class EmotionController {

    private final EmotionService emotionService;

    @GetMapping
    @Operation(summary = "사용자 ID를 받아서 감정상태 조회 API")
    public ResponseEntity<List<EmotionDTO>> getAllEmotions(Long userId) {
        return ResponseEntity.ok().body(emotionService.getAllEmotions(userId));
    }
}
