package cokoball.back.Repository;

import cokoball.back.Entity.Solution;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SolutionRepository extends JpaRepository<Solution, Long> {
}
