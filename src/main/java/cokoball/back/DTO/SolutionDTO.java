package cokoball.back.DTO;

public class SolutionDTO {
    private Long emotionId;
    private String content;

    public SolutionDTO() {
    }

    public SolutionDTO(Long emotionId, String content) {
        this.emotionId = emotionId;
        this.content = content;
    }

    public Long getEmotionId() {
        return emotionId;
    }

    public void setEmotionId(Long emotionId) {
        this.emotionId = emotionId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
