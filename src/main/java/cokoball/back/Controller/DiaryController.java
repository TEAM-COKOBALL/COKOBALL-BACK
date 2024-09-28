package cokoball.back.Controller;

import cokoball.back.DTO.DiaryDTO;
import cokoball.back.Service.DiaryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/diaries")
public class DiaryController {

    private final DiaryService diaryService;

    public DiaryController(DiaryService diaryService) {
        this.diaryService = diaryService;
    }

    @GetMapping("/emotion/{emotionId}")
    public List<DiaryDTO> getDiariesByEmotion(@PathVariable Long emotionId) {
        return diaryService.getDiariesByEmotion(emotionId);
    }
}


// 맨 처음 사용자가 Emotion 에 따른 Solution을 적으면 이걸 db에 저장해야 해.
// 관련 코드를 작성해 줘.
// db terminal은 아래를 참고해 줘.