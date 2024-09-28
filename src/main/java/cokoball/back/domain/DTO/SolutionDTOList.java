package cokoball.back.domain.DTO;

import java.util.List;

public class SolutionDTOList {
    private List<SolutionDTO> solutions;

    public SolutionDTOList() {}

    public SolutionDTOList(List<SolutionDTO> solutions) {
        this.solutions = solutions;
    }

    public List<SolutionDTO> getSolutions() {
        return solutions;
    }

    public void setSolutions(List<SolutionDTO> solutions) {
        this.solutions = solutions;
    }
}
