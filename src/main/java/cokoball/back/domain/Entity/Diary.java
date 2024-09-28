package cokoball.back.domain.Entity;

import cokoball.back.domain.User.Entity.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDate;

@Entity
@Getter @Setter
@NoArgsConstructor
public class Diary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String diaryId;

    @Column(nullable = false)
    private String content;

    private Boolean checkSolution;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "emotion_id")
    private Emotion emotion;

    private LocalDate createDate;

    public Diary(String diaryId, String content, Emotion emotion, User user, LocalDate createDate) {
        this.diaryId = diaryId;
        this.content = content;
        this.emotion = emotion;
        this.user = user;
        this.createDate = createDate;
        this.checkSolution = false;
    }
}
