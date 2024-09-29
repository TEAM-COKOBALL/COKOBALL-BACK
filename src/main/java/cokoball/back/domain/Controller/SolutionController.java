package cokoball.back.domain.Controller;

import cokoball.back.domain.DTO.SolutionDTO;
import cokoball.back.domain.DTO.SolutionDTOList;
import cokoball.back.domain.Entity.Solution;
import cokoball.back.domain.Service.SolutionService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/solutions")
@RequiredArgsConstructor
public class SolutionController {

    private final SolutionService solutionService;

    @GetMapping("/{emotionId}")
    @Operation(summary = "감정 ID를 받은 후 솔루션 조회 API")
    public ResponseEntity<List<SolutionDTO>> getSolutionByEmotion(@PathVariable Long emotionId) {
        return ResponseEntity.ok().body(solutionService.getSolutionsByEmotion(emotionId));
    }

    @PostMapping("/batch")
    @Operation(summary = "나 사용법 작성")
    public ResponseEntity<String> createWhoAmI(@RequestBody SolutionDTOList solutions) {
        solutions.getSolutions().forEach(
                tmp -> solutionService.saveSolution(tmp.getEmotionId(), tmp.getSolutionContent()));
        return ResponseEntity.ok().body("{\n msg: success \n}");
    }
}
