package com.varchar6.petcast.domain.request.query.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Generated
@ToString
@Builder
public class RequestDetailDTO {
    private int id;
    private String content;
    private int hopeCost;
    private String hopeLocation;
    private String hopeTime;
    private RequestStatus status;
    private String createdAt;
    private String updatedAt;
    private Boolean active;
    private int companyId;
    private int memberId;
}
