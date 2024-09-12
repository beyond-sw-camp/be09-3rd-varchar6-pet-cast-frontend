package com.varchar6.petcast.serviceothers.domain.request.command.application.dto.request;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@Builder
public class RequestsCategoryRequestDTO {
    private int categoryId;
    private int requestId;
}
