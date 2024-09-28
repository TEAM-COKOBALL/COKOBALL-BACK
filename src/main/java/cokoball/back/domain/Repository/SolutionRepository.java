package cokoball.back.domain.Repository;

import cokoball.back.domain.Entity.Solution;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SolutionRepository extends JpaRepository<Solution, Long> {
}
