package cokoball.back.domain.DTO.Diary;

import cokoball.back.domain.Entity.Diary;
import cokoball.back.domain.Entity.Solution;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class DiaryWithSolutionDTO {
    private String solutionContent;
    private List<DiaryDTO> diary;

    public DiaryWithSolutionDTO(String content, List<Diary> diaries) {
        solutionContent = content;
        diary = diaries.stream().map(tmp -> new DiaryDTO(tmp.getCreateDate(), tmp.getContent())).toList();
    }
}
