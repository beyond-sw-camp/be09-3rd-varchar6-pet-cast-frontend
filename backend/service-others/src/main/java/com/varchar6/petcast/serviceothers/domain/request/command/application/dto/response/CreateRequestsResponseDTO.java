package com.varchar6.petcast.serviceothers.domain.request.command.application.dto.response;


import com.varchar6.petcast.serviceothers.domain.request.command.domain.aggregate.RequestsStatus;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@Builder
public class CreateRequestsResponseDTO {
    private int id;
    private String content;
    private int hopeCost;
    private String hopeLocation;
    private String hopeTime;
    private RequestsStatus status;
    private String createdAt;
    private String updatedAt;
    private boolean active;


}
