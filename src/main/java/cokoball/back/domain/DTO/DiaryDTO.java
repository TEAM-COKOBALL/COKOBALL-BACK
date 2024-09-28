package cokoball.back.domain.DTO;

import lombok.Data;

import java.time.LocalDate;

@Data
public class DiaryDTO {
    private Long id;
    private String diaryId;
    private String content;
    private Boolean checkSolution;
    private Long userId;
    private Long emotionId;
    private LocalDate createDate;
}