package cokoball.back.DTO;

import lombok.Data;

import java.time.LocalDate;

@Data
public class DiaryDTO {
    private Long id;
    private String diaryNum;
    private String content;
    private boolean checkSolution;
    private Long userId;
    private Long emotionId;
    private LocalDate createDate;
}