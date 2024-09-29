package cokoball.back.domain.Service;

import cokoball.back.domain.DTO.Diary.DiaryDTO;
import cokoball.back.domain.DTO.Diary.DiaryWithSolutionDTO;
import cokoball.back.domain.Entity.Diary;
import cokoball.back.domain.Entity.Solution;
import cokoball.back.domain.Repository.SolutionRepository;
import cokoball.back.domain.Repository.DiaryRepository;
import cokoball.back.domain.Repository.EmotionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class DiaryService {
    private final DiaryRepository diaryRepository;
    private final EmotionRepository emotionRepository;
    private final SolutionRepository solutionRepository;

    public List<DiaryWithSolutionDTO> getDiariesByEmotion(Long emotionId) {
        return solutionRepository.findAllByEmotionId(emotionId)
                .stream().map(
                        solution -> {
                            List<Diary> allBySolutionId = diaryRepository.findAllBySolutionId(solution.getId());
                            return new DiaryWithSolutionDTO(solution.getContent(), allBySolutionId);
                        }
                ).toList();
    }

    // 일기 저장 로직
    @Transactional
    public void saveDiary(DiaryDTO diaryDto) {
        Solution solution = solutionRepository.findById(diaryDto.getSolutionId()).orElseThrow(() -> new IllegalArgumentException("솔루션이 없엉."));
        // 새로운 Diary 엔티티 생성
        Diary diary = Diary.create(diaryDto.getContent(), solution);
        // DB에 저장
        diaryRepository.save(diary);
    }
}

