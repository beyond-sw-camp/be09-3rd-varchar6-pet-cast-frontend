package com.varchar6.petcast.domain.proposalandestimate.command.service;


import com.varchar6.petcast.domain.proposalandestimate.command.domain.aggregate.Estimates;
import com.varchar6.petcast.domain.proposalandestimate.command.application.dto.EstimateRequestDTO;
import com.varchar6.petcast.domain.proposalandestimate.query.mapper.EstimateMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static com.varchar6.petcast.domain.proposalandestimate.command.domain.aggregate.EstimatesStatus.SENT;

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
//        List<Estimate> estimates = estimateMapper.findAllEstimatesByMemberId(memberId);
        return estimateRepository.findAll().stream().map(Estimate -> {
            EstimateResponseDTO estimateResponseDTO = new EstimateResponseDTO();

            estimateResponseDTO.setCost(Estimate.getCost());
//            estimateResponseDTO.setStatus(Estimate.getStatus());
            estimateResponseDTO.setCreated_at(Estimate.getCreated_at());
            estimateResponseDTO.setUpdated_at(Estimate.getUpdated_at());
            estimateResponseDTO.setActive(Estimate.isActive());
        }).toList();
    }

    // 업체 견적서 목록 조회
    @Override
    public List<EstimateResponseDTO> findAllEstimatesByCompanyId(int companyId) {
//        List<Estimate> estimates = estimateMapper.findAllEstimatesByCompanyId(companyId);

        return estimateMapper.findAllEstimatesByCompanyId(companyId);
    }

    // 견적서 상세 조회
    @Override
    public EstimateResponseDTO findEstimateById(int estimateId) {
        Estimates estimates = estimateMapper.findEstimateById(estimateId);
        if (estimates == null) {
            throw new IllegalArgumentException("해당 " + estimateId + " 번 견적서를 찾을 수 없습니다.");
        }
        return entityToResponseDTO(estimates);
    }

    // 견적서 작성
    @Transactional
    public EstimateResponseDTO createEstimate(EstimateRequestDTO estimateRequestDTO) {
        EstimateResponseDTO estimateResponseDTO = new EstimateResponseDTO();
//        Estimate estimate = estimateDTOToEntity(estimateRequestDTO);
        estimateResponseDTO.setCost(Estimates.getCost);
        estimateResponseDTO.setStatus(Estimates.getStatus);
//        private String created_at;
//        private String updated_at;
//        private boolean active;
//        private int proposal;
//        private int companyInfo;
//        return estimateRepository.save(estimate);
    }

    // 견적서 삭제
    @Transactional
    public void deleteEstimate(int estimateId) {
        estimateRepository.deleteById(estimateId);

        if (!estimateRepository.existsById(estimateId)) {
            throw new IllegalArgumentException("해당 " + estimateId + " 번 견적서를 찾을 수 없습니다.");
        }
        return estimateRepository.save(estimateId);
    }

    // 견적서 수락
    @Transactional
    public EstimateResponseDTO acceptEstimate(int estimateId) {
        Estimates estimates = estimateRepository.findById(estimateId)
                .orElseThrow(() -> new IllegalArgumentException("해당 " + estimateId + " 번 견적서를 찾을 수 없습니다."));
        estimates.accept();
        estimateRepository.save(estimates);
        return entityToResponseDTO(estimates);
    }

    // 견적서 거절
    @Transactional
    public EstimateResponseDTO rejectEstimate(int estimateId) {
        Estimates estimates = estimateRepository.findById(estimateId)
                .orElseThrow(() -> new IllegalArgumentException("해당 " + estimateId + " 번 견적서를 찾을 수 없습니다."));
        estimates.reject();
        estimateRepository.save(estimates);
        return entityToResponseDTO(estimates);
    }

    private Estimates estimateDTOToEntity(EstimateRequestDTO estimateRequestDTO) {
        return Estimates.builder()
                .cost(estimateRequestDTO.getCost())
                .status(SENT)
                .created_at(LocalDateTime.now().format(FORMATTER))
                .updated_at(LocalDateTime.now().format(FORMATTER))
                .active(true)
                .proposal_id(estimateRequestDTO.getProposal_id())
                .companyInfo(estimateRequestDTO.getCompanyInfo())
                .build();
    }

    private EstimateResponseDTO entityToResponseDTO(Estimates estimates) {
        return EstimateResponseDTO.builder()
                .id(estimates.getId())
                .cost(estimates.getCost())
                .status(estimates.getStatus())
                .created_at(estimates.getCreated_at())
                .updated_at(estimates.getUpdated_at())
                .active(estimates.isActive())
                .proposal(estimates.getProposal_id())
                .companyInfo(estimates.getCompanyInfo())
                .build();
    }
}


