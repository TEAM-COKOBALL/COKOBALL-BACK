package cokoball.back.domain.Controller;

import cokoball.back.domain.DTO.EmotionDTO;
import cokoball.back.domain.Service.EmotionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/emotions")
public class EmotionController {

    private final EmotionService emotionService;

    public EmotionController(EmotionService emotionService) {
        this.emotionService = emotionService;
    }

    @GetMapping
    public List<EmotionDTO> getAllEmotions() {
        return emotionService.getAllEmotions();
    }
}
