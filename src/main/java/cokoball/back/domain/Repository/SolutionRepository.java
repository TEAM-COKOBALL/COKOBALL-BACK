package cokoball.back.domain.Repository;

import cokoball.back.domain.Entity.Diary;
import cokoball.back.domain.Entity.Emotion;
import cokoball.back.domain.Entity.Solution;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SolutionRepository extends JpaRepository<Solution, Long> {
    List<Diary> findByEmotion(Emotion emotion);
}
