package com.varchar6.petcast.domain.proposalandestimate.service;

import com.varchar6.petcast.domain.proposalandestimate.aggregate.Estimate;
import com.varchar6.petcast.domain.proposalandestimate.aggregate.EstimateStatus;
import com.varchar6.petcast.domain.proposalandestimate.dto.EstimateRequestDTO;
import com.varchar6.petcast.domain.proposalandestimate.dto.EstimateResponseDTO;
import com.varchar6.petcast.domain.proposalandestimate.repository.EstimateMapper;
import com.varchar6.petcast.domain.proposalandestimate.repository.EstimateRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static com.varchar6.petcast.domain.proposalandestimate.service.ProposalServiceImpl.FORMATTER;

@Service
@Slf4j
public class EstimateServiceImpl implements EstimateService {

    private final EstimateMapper estimateMapper;
    private final EstimateRepository estimateRepository;

    private static final String FORMAT = "yyyy-MM-dd'T'HH:mm:ss";
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern(FORMAT);

    @Autowired
    public EstimateServiceImpl(EstimateMapper estimateMapper, EstimateRepository estimateRepository) {
        this.estimateMapper = estimateMapper;
        this.estimateRepository = estimateRepository;
    }

    // 고객 견적서 목록 조회
    @Override
    public List<EstimateResponseDTO> findAllEstimatesByMemberId(int memberId) {
        List<Estimate> estimates = estimateMapper.findAllEstimatesByMemberId(memberId);
        return estimates.stream().map(this::entityToResponseDTO).toList();
    }

    // 업체 견적서 목록 조회
    @Override
    public List<EstimateResponseDTO> findAllEstimatesByCompanyId(int companyId) {
        List<Estimate> estimates = estimateMapper.findAllEstimatesByCompanyId(companyId);
        return estimates.stream().map(this::entityToResponseDTO).toList();
    }

    // 견적서 상세 조회
    @Override
    public EstimateResponseDTO findEstimateById(int estimateId) {
        Estimate estimate = estimateMapper.findEstimateById(estimateId);
        if (estimate == null) {
            throw new IllegalArgumentException("해당 " + estimateId + " 번 견적서를 찾을 수 없습니다.");
        }
        return entityToResponseDTO(estimate);
    }

    // 견적서 작성
    @Transactional
    public EstimateResponseDTO createEstimate(EstimateRequestDTO estimateRequestDTO) {
        Estimate estimate = estimateDTOToEntity(estimateRequestDTO);
        estimate = estimateRepository.save(estimate);
        return entityToResponseDTO(estimate);
    }

    // 견적서 삭제
    @Transactional
    public void deleteEstimate(int estimateId) {
        if (!estimateRepository.existsById(estimateId)) {
            throw new IllegalArgumentException("해당 " + estimateId + " 번 견적서를 찾을 수 없습니다.");
        }
        estimateRepository.save(estimateId);
    }

    // 견적서 수락
    @Transactional
    public EstimateResponseDTO acceptEstimate(int estimateId) {
        Estimate estimate = estimateRepository.findById(estimateId)
                .orElseThrow(() -> new IllegalArgumentException("해당 " + estimateId + " 번 견적서를 찾을 수 없습니다."));
        estimate.accept();
        estimateRepository.save(estimate);
        return entityToResponseDTO(estimate);
    }

    // 견적서 거절
    @Transactional
    public EstimateResponseDTO rejectEstimate(int estimateId) {
        Estimate estimate = estimateRepository.findById(estimateId)
                .orElseThrow(() -> new IllegalArgumentException("해당 " + estimateId + " 번 견적서를 찾을 수 없습니다.));
        estimate.reject();
        estimateRepository.save(estimate);
        return entityToResponseDTO(estimate);
    }

    private Estimate estimateDTOToEntity(EstimateRequestDTO estimateRequestDTO) {
        return Estimate.builder()
                .cost(estimateRequestDTO.getCost())
                .status(EstimateStatus.SENT)
                .created_at(LocalDateTime.now().format(FORMATTER))
                .updated_at(LocalDateTime.now().format(FORMATTER))
                .active(true)
                .proposal(estimateRequestDTO.getProposal())
                .companyInfo(estimateRequestDTO.getCompanyInfo())
                .build();
    }

    private EstimateResponseDTO entityToResponseDTO(Estimate estimate) {
        return EstimateResponseDTO.builder()
                .id(estimate.getId())
                .cost(estimate.getCost())
                .status(estimate.getStatus())
                .created_at(LocalDateTime.now().format(FORMATTER))
                .updated_at(LocalDateTime.now().format(FORMATTER))
                .active(estimate.isActive())
                .build();
    }
}

}
