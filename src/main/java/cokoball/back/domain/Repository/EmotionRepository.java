package cokoball.back.domain.Repository;

import cokoball.back.domain.Entity.Emotion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmotionRepository extends JpaRepository<Emotion, Long> {
}
