package cokoball.back.domain.DTO.Diary;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDate;

@Data
public class DiaryDTO {
    private LocalDate createdAt;
    @NotBlank
    private String content;
    private Long solutionId;

    public DiaryDTO(String content) {
        this.createdAt = null;
        this.content = content;
        this.solutionId = null;
    }

    public DiaryDTO(LocalDate createdAt, String content) {
        this.createdAt = createdAt;
        this.content = content;
        this.solutionId = null;
    }

    public DiaryDTO(LocalDate createdAt, String content, Long solutionId) {
        this.createdAt = createdAt;
        this.content = content;
        this.solutionId = solutionId;
    }
}