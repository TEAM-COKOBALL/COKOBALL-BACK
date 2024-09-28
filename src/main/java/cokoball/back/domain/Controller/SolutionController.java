package cokoball.back.domain.Controller;

import cokoball.back.domain.DTO.DiaryDTO;
import cokoball.back.domain.DTO.SolutionDTO;
import cokoball.back.domain.Entity.Solution;
import cokoball.back.domain.Service.SolutionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/solutions")
public class SolutionController {

    private final SolutionService solutionService;

    @Autowired
    public SolutionController(SolutionService solutionService) {
        this.solutionService = solutionService;
    }

    @PostMapping
    public ResponseEntity<Solution> createSolution(@RequestBody SolutionDTO solutionDto) {
        Solution solution = solutionService.saveSolution(solutionDto);
        return ResponseEntity.ok(solution);
    }

    @GetMapping("/{emotionId}")
    public List<SolutionDTO> getSolutionByEmotion(@PathVariable Long emotionId) {
        return SolutionService.getSolutionByEmotion(emotionId);
    }
}
