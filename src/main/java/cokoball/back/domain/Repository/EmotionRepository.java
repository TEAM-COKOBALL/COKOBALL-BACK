package cokoball.back.domain.Repository;

import cokoball.back.domain.Entity.Emotion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EmotionRepository extends JpaRepository<Emotion, Long> {

    List<Emotion> findAllByUserId(Long userId);
}
