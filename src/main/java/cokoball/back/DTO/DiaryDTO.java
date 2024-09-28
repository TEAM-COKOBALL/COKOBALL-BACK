package cokoball.back.DTO;

import lombok.Data;
import java.util.Date;

import java.time.LocalDate;

@Data
public class DiaryDTO {
    private Long id;
    private String diaryId;
    private String content;
    private boolean checkSolution;
    private Long userId;
    private Long emotionId;
    private LocalDate createDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDiaryId() {
        return diaryId;
    }

    public void setDiaryId(String diaryId) {
        this.diaryId = diaryId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Boolean getCheckSolution() {
        return checkSolution;
    }

    public void setCheckSolution(Boolean checkSolution) {
        this.checkSolution = checkSolution;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getEmotionId() {
        return emotionId;
    }

    public void setEmotionId(Long emotionId) {
        this.emotionId = emotionId;
    }

    public LocalDate getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDate createDate) {
        this.createDate = createDate;
    }
}