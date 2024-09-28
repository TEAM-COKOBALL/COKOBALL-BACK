package cokoball.back.domain.DTO;

import lombok.Data;

import java.time.LocalDate;

@Data
public class DiaryDTO {
    private String content;
    private Long userId;
    private Long emotionId;
    private LocalDate createDate;
}