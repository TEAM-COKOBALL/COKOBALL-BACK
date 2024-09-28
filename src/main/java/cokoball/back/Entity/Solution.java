package cokoball.back.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Solution {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "emotion_id", nullable = false)
    private Emotion emotion;

    @Column(nullable = false)
    private String content;

    // Constructor, Getter, and Setter

    public Solution() {
    }

    public Solution(Emotion emotion, String content) {
        this.emotion = emotion;
        this.content = content;
    }

    public Long getId() {
        return id;
    }

    public Emotion getEmotion() {
        return emotion;
    }

    public void setEmotion(Emotion emotion) {
        this.emotion = emotion;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}