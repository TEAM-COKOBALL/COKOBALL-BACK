package cokoball.back.Service;

import cokoball.back.DTO.SolutionDTO;
import cokoball.back.Entity.Emotion;
import cokoball.back.Entity.Solution;
import cokoball.back.Repository.EmotionRepository;
import cokoball.back.Repository.SolutionRepository;
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
        Emotion emotion = emotionRepository.findById(solutionDto.getEmotionId())
                .orElseThrow(() -> new IllegalArgumentException("Emotion not found"));

        // Solution 객체 생성 및 저장
        Solution solution = new Solution(emotion, solutionDto.getContent());
        return solutionRepository.save(solution);
    }
}
