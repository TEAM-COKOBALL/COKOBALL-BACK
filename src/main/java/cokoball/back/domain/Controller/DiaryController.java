package cokoball.back.domain.Controller;

import cokoball.back.domain.DTO.DiaryDTO;
import cokoball.back.domain.Entity.Diary;
import cokoball.back.domain.Service.DiaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.List;

@RestController
@RequestMapping("/api/diaries")
@RequiredArgsConstructor
public class DiaryController {

    private final DiaryService diaryService;

    // 특정 emotionId와 diaryId로 일기 작성
    @PostMapping("/{emotionId}")
    public ResponseEntity<Diary> createDiary(
            @PathVariable Long emotionId,
            @RequestBody DiaryDTO diaryDto) {

        // diaryId와 emotionId를 DTO에 반영
        diaryDto.setEmotionId(emotionId);

        // 서비스에서 일기 저장 로직 호출
        Diary savedDiary = diaryService.saveDiary(diaryDto);
        return ResponseEntity.ok(savedDiary);
    }

    @GetMapping("/{emotionId}")
    public List<DiaryDTO> getDiariesByEmotion(@PathVariable Long emotionId) {
        return diaryService.getDiariesByEmotion(emotionId);
    }
}