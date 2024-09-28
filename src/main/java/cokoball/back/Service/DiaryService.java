package cokoball.back.Service;

import cokoball.back.DTO.DiaryDTO;
import cokoball.back.Entity.Diary;
import cokoball.back.Entity.Emotion;
import cokoball.back.User.Entity.User;
import cokoball.back.User.Repository.UserRepository;
import cokoball.back.Repository.DiaryRepository;
import cokoball.back.Repository.EmotionRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DiaryService {

    private final DiaryRepository diaryRepository;
    private final EmotionRepository emotionRepository;
    private final UserRepository userRepository;


    public DiaryService(DiaryRepository diaryRepository, EmotionRepository emotionRepository, UserRepository userRepository) {
        this.diaryRepository = diaryRepository;
        this.emotionRepository = emotionRepository;
        this.userRepository = userRepository;
    }

    public List<DiaryDTO> getDiariesByEmotion(Long emotionId) {
        Emotion emotion = emotionRepository.findById(emotionId).orElseThrow(() -> new RuntimeException("Emotion not found"));
        List<Diary> diaries = diaryRepository.findByEmotion(emotion);
        return diaries.stream().map(this::toDTO).collect(Collectors.toList());
    }

    private DiaryDTO toDTO(Diary diary) {
        DiaryDTO dto = new DiaryDTO();
        dto.setId(diary.getId());
        dto.setDiaryId(diary.getDiaryId());
        dto.setContent(diary.getContent());
        dto.setCheckSolution(diary.getCheckSolution());
        dto.setUserId(diary.getUser() != null ? diary.getUser().getId() : null);
        dto.setEmotionId(diary.getEmotion().getId());
        dto.setCreateDate(diary.getCreateDate());
        return dto;
    }

    // 일기 저장 로직
    public Diary saveDiary(DiaryDTO diaryDto) {
        // Emotion ID로 Emotion 엔티티를 찾음
        Emotion emotion = emotionRepository.findById(diaryDto.getEmotionId())
                .orElseThrow(() -> new IllegalArgumentException("Emotion not found"));

        // User ID로 User 엔티티를 찾음
        User user = userRepository.findById(diaryDto.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        // 새로운 Diary 엔티티 생성
        Diary diary = new Diary(
                diaryDto.getDiaryId(),
                diaryDto.getContent(),
                emotion,
                user,
                diaryDto.getCreateDate()
        );
        diary.setCheckSolution(diaryDto.getCheckSolution());

        // DB에 저장
        return diaryRepository.save(diary);
    }
}

