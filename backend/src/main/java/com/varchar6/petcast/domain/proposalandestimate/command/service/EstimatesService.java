package com.varchar6.petcast.domain.proposalandestimate.command.service;

import com.varchar6.petcast.domain.proposalandestimate.command.application.dto.EstimatesRequestDTO;
import com.varchar6.petcast.domain.proposalandestimate.command.application.dto.EstimatesResponseDTO;
import org.springframework.stereotype.Service;

import java.util.List;
public interface EstimatesService {

    int createEstimate(EstimatesRequestDTO estimatesRequestDTO);

    void deleteEstimate(int estimateId);

    EstimatesResponseDTO acceptEstimate(int estimateId);

    EstimatesResponseDTO rejectEstimate(int estimateId);

}
