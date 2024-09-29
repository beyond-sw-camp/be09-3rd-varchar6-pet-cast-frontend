package com.varchar6.petcast.serviceothers.domain.qna.command.application.service;

import com.varchar6.petcast.serviceothers.domain.qna.command.application.dto.request.QnaCreateRequestDTO;
import com.varchar6.petcast.serviceothers.domain.qna.command.application.dto.request.QnaDeleteAnswerRequestDTO;
import com.varchar6.petcast.serviceothers.domain.qna.command.application.dto.request.QnaUpdateRequestDTO;
import com.varchar6.petcast.serviceothers.domain.qna.command.domain.aggregate.Qna;
import com.varchar6.petcast.serviceothers.domain.qna.command.domain.repository.QnaRepository;

import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.NoSuchElementException;

@Slf4j
@Service(value = "commandQnaService")
public class QnaServiceImpl implements QnaService{

    private final QnaRepository qnaRepository;
    private final ModelMapper modelMapper;

    private static final String FORMAT = "yyyy-MM-dd'T'HH:mm:ss";
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern(FORMAT);

    public QnaServiceImpl(QnaRepository qnaRepository, ModelMapper modelMapper) {
        this.qnaRepository = qnaRepository;
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        this.modelMapper = modelMapper;
    }

    @Override
    @Transactional
    public int insertQna(QnaCreateRequestDTO qnaCreateRequestDTO) {

        Qna qna = modelMapper.map(qnaCreateRequestDTO, Qna.class);
        qna.setCreatedAt(LocalDateTime.now().format(FORMATTER));
        qna.setActive(true);
        qna.setAnswered(false);
        qna.setAnswererId(null);
        qna.setAnswer("");

        try {
            qnaRepository.save(qna);
            return 1;
        }catch(Exception e){
            throw new RuntimeException("qna 입력 실패", e);
        }
    }

    @Override
    @Transactional
    public int updateQna(QnaUpdateRequestDTO qnaUpdateRequestDTO) {
        try {
            Qna qna = qnaRepository.findById(qnaUpdateRequestDTO.getId())
                    .orElseThrow(() -> new NoSuchElementException("해당 Qna가 존재하지 않습니다."));

            qna.setAnsweredAt(LocalDateTime.now().format(FORMATTER));
            qna.setAnswered(true);

            return 1;
        } catch (NoSuchElementException | IllegalArgumentException e) {
            throw e;
        }
        catch (Exception e) {
            throw new RuntimeException("답변 작성 실패", e);
        }
    }

    @Override
    @Transactional
    public int deleteQnaAnswer(QnaDeleteAnswerRequestDTO qnaDeleteAnswerRequestDTO) {
       try {
            Qna qna = qnaRepository.findById(qnaDeleteAnswerRequestDTO.getId())
                    .orElseThrow(() -> new NoSuchElementException("해당 Qna가 존재하지 않습니다."));

            /* 설명. 답변자 아이디와 db의 아이디와 비교 */
            if (!qna.getAnswererId().equals(qnaDeleteAnswerRequestDTO.getAnswererId())) {
                throw new IllegalArgumentException("검증 실패: 업체에 등록된 아이디와 일치하지 않습니다.");
            }

            qna.setAnswer("");

            return 1;
        }catch (NoSuchElementException | IllegalArgumentException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("답변 삭제 실패",e);
        }
    }

    @Override
    @Transactional
    public int setQnaActive(int id, int memberId) {
       try {
            Qna qna = qnaRepository.findById(id)
                    .orElseThrow(() -> new NoSuchElementException("해당 Qna가 존재하지 않습니다."));
            if (qna.getQuestionerId() != memberId)
                throw new IllegalArgumentException("검증 실패: 작성한 회원 아이디가 없습니다.");

            qna.setActive(false);
            return 1;
        } catch (NoSuchElementException | IllegalArgumentException e) {
            throw e;
        }catch (Exception e) {
            throw new RuntimeException("qna 비활성화 실패", e);
        }
    }

}
