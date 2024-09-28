package cokoball.back.domain.DTO;

public record SolutionDTO(Long emotionId, String content) {
    public SolutionDTO(Long emotionId, String content) {
        this.emotionId = emotionId;
        this.content = content;
    }
}
