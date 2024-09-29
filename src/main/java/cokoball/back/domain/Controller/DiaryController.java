package cokoball.back.domain.Controller;

import cokoball.back.domain.DTO.Diary.DiaryDTO;
import cokoball.back.domain.DTO.Diary.DiaryWithSolutionDTO;
import cokoball.back.domain.Service.DiaryService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.List;

@RestController
@RequestMapping("/api/diaries")
@RequiredArgsConstructor
public class DiaryController {

    private final DiaryService diaryService;

    @PostMapping
    @Operation(summary = "일기 생성 API")
    public ResponseEntity<Void> createDiary(@RequestBody DiaryDTO diaryDto) {
        // 서비스에서 일기 저장 로직 호출
        diaryService.saveDiary(diaryDto);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{emotionId}")
    @Operation(summary = "감정 상태에 따른 해결방안 + 일기 목록 API")
    public ResponseEntity<List<DiaryWithSolutionDTO>> getDiariesByEmotion(@PathVariable Long emotionId) {
        return ResponseEntity.ok().body(diaryService.getDiariesByEmotion(emotionId));
    }


}