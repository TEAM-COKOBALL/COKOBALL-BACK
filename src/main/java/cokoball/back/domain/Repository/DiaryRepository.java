package cokoball.back.domain.Repository;

import cokoball.back.domain.Entity.Diary;
import cokoball.back.domain.Entity.Emotion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DiaryRepository extends JpaRepository<Diary, Long> {
    List<Diary> findByEmotion(Emotion emotion);
}
