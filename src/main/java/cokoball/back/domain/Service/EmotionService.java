package cokoball.back.domain.Service;

import cokoball.back.domain.DTO.EmotionDTO;
import cokoball.back.domain.Entity.Emotion;
import cokoball.back.domain.Entity.Solution;
import cokoball.back.domain.Repository.EmotionRepository;
import cokoball.back.domain.Repository.SolutionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class EmotionService {

    private final EmotionRepository emotionRepository;

    public List<EmotionDTO> getAllEmotions(Long userId) {
        return emotionRepository.findAllByUserId(userId).stream().map(EmotionDTO::new).toList();
    }

}
