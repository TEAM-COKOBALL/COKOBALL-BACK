package cokoball.back.domain.DTO;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class SolutionDTOList {
    private List<EmotionWithSolutionDTO> solutions;

    public SolutionDTOList(List<EmotionWithSolutionDTO> solutions) {
        this.solutions = solutions;
    }

    @Getter @Setter
    public static class EmotionWithSolutionDTO {
        private Long emotionId;
        private String solutionContent;
    }

}
