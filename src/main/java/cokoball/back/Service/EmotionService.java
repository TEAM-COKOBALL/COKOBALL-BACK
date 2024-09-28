package cokoball.back.Service;

import cokoball.back.DTO.EmotionDTO;
import cokoball.back.Entity.Emotion;
import cokoball.back.Repository.EmotionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmotionService {
    private final EmotionRepository emotionRepository;

    public EmotionService(EmotionRepository emotionRepository) {
        this.emotionRepository = emotionRepository;
    }

    public List<EmotionDTO> getAllEmotions() {
        return emotionRepository.findAll().stream().map(this::toDto).collect(Collectors.toList());
    }

    private EmotionDTO toDto(Emotion emotion) {
        EmotionDTO dto = new EmotionDTO();
        dto.setId(emotion.getId());
        dto.setType(emotion.getType());
        return dto;
    }
}
