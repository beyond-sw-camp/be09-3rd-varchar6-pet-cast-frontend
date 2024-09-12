package com.varchar6.petcast.serviceothers.domain.request.command.application.dto.request;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@Builder
public class CreateRequestsRequestDTO {
    private String content;
    private int hopeCost;
    private String hopeLocation;
    private String hopeTime;
    private int companyId;
    private List<Integer> categoryId;

}
