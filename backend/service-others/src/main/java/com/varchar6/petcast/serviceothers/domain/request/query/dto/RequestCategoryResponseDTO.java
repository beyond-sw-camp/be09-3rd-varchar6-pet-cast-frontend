package com.varchar6.petcast.serviceothers.domain.request.query.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class RequestCategoryResponseDTO {
    private int categoryId;
    private int requestId;
}
