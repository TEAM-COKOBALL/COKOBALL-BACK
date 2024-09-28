package cokoball.back.Controller;

import cokoball.back.DTO.SolutionDTO;
import cokoball.back.Entity.Solution;
import cokoball.back.Service.SolutionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
}
