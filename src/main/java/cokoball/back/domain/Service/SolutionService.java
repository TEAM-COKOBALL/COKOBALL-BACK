package cokoball.back.domain.Service;

import cokoball.back.domain.DTO.SolutionDTO;
import cokoball.back.domain.Entity.Emotion;
import cokoball.back.domain.Entity.Solution;
import cokoball.back.domain.Repository.EmotionRepository;
import cokoball.back.domain.Repository.SolutionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SolutionService {
    private final SolutionRepository solutionRepository;
    private final EmotionRepository emotionRepository;

    @Autowired
    public SolutionService(SolutionRepository solutionRepository, EmotionRepository emotionRepository) {
        this.solutionRepository = solutionRepository;
        this.emotionRepository = emotionRepository;
    }

    public Solution saveSolution(SolutionDTO solutionDto) {
        // Emotion ID로 Emotion 객체를 찾음
        Emotion emotion = emotionRepository.findById(solutionDto.emotionId())
                .orElseThrow(() -> new IllegalArgumentException("Emotion not found"));

        // Solution 객체 생성 및 저장
        Solution solution = new Solution(emotion, solutionDto.content());
        return solutionRepository.save(solution);
    }
}
