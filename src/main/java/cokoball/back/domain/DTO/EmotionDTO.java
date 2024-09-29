package cokoball.back.domain.DTO;

import cokoball.back.domain.Entity.Emotion;
import lombok.Data;

@Data
public class EmotionDTO {
    private Long emotionId;
    private String emotionName;

    public EmotionDTO(Emotion emotion) {
        this.emotionId = emotion.getId();
        this.emotionName = emotion.getType();
    }
}
