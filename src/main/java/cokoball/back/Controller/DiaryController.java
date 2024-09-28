package cokoball.back.Controller;

import cokoball.back.DTO.DiaryDTO;
import cokoball.back.DTO.SolutionDTO;
import cokoball.back.Entity.Diary;
import cokoball.back.Entity.Solution;
import cokoball.back.Service.DiaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.List;

@RestController
@RequestMapping("/api/diaries")
public class DiaryController {

    private final DiaryService diaryService;

    @Autowired
    public DiaryController(DiaryService diaryService) {
        this.diaryService = diaryService;
    }

    // 특정 emotionId와 diaryId로 일기 작성
    @PostMapping("/{emotionId}/{diaryId}")
    public ResponseEntity<Diary> createDiary(
            @PathVariable Long emotionId,
            @PathVariable String diaryId,
            @RequestBody DiaryDTO diaryDto) {

        // diaryId와 emotionId를 DTO에 반영
        diaryDto.setEmotionId(emotionId);
        diaryDto.setDiaryId(diaryId);

        // 서비스에서 일기 저장 로직 호출
        Diary savedDiary = diaryService.saveDiary(diaryDto);
        return ResponseEntity.ok(savedDiary);
    }

    @GetMapping("/{emotionId}")
    public List<DiaryDTO> getDiariesByEmotion(@PathVariable Long emotionId) {
        return diaryService.getDiariesByEmotion(emotionId);
    }
}