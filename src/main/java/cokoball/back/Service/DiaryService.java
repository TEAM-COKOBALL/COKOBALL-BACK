package cokoball.back.Service;

import cokoball.back.DTO.DiaryDTO;
import cokoball.back.Entity.Diary;
import cokoball.back.Entity.Emotion;
import cokoball.back.Repository.DiaryRepository;
import cokoball.back.Repository.EmotionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DiaryService {

    private final DiaryRepository diaryRepository;
    private final EmotionRepository emotionRepository;

    public DiaryService(DiaryRepository diaryRepository, EmotionRepository emotionRepository) {
        this.diaryRepository = diaryRepository;
        this.emotionRepository = emotionRepository;
    }

    public List<DiaryDTO> getDiariesByEmotion(Long emotionId) {
        Emotion emotion = emotionRepository.findById(emotionId).orElseThrow(() -> new RuntimeException("Emotion not found"));
        List<Diary> diaries = diaryRepository.findByEmotion(emotion);
        return diaries.stream().map(this::toDTO).collect(Collectors.toList());
    }

    private DiaryDTO toDTO(Diary diary) {
        DiaryDTO dto = new DiaryDTO();
        dto.setId(diary.getId());
        dto.setDiaryNum(diary.getDiaryNum());
        dto.setContent(diary.getContent());
        dto.setCheckSolution(diary.isCheckSolution());
        dto.setUserId(diary.getUser() != null ? diary.getUser().getId() : null);
        dto.setEmotionId(diary.getEmotion().getId());
        dto.setCreateDate(diary.getCreateDate());
        return dto;
    }
}

