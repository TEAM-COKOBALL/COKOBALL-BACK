package cokoball.back.domain.DTO;

import cokoball.back.domain.Entity.Solution;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class SolutionDTO {
    private Long solutionId;
    private String content;

    public SolutionDTO(Solution solution) {
        this.solutionId = solution.getId();
        this.content = solution.getContent();
    }
}
