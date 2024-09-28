package cokoball.back.Repository;

import cokoball.back.Entity.Emotion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmotionRepository extends JpaRepository<Emotion, Long> {
}
