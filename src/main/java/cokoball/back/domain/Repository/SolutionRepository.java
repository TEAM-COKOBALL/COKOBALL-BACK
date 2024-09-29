package cokoball.back.domain.Repository;

import cokoball.back.domain.Entity.Diary;
import cokoball.back.domain.Entity.Solution;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SolutionRepository extends JpaRepository<Solution, Long> {
    List<Solution> findAllByEmotionId(Long emotionId);
}
