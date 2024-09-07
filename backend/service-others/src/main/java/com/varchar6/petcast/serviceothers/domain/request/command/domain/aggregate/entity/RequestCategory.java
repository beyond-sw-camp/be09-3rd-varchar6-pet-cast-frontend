package com.varchar6.petcast.serviceothers.domain.request.command.domain.aggregate.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "tbl_request_category")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RequestCategory {

    @Id
    @Column(name = "category_id")
    private int categoryId;

    @Id
    @Column(name = "request_id")
    private int requestId;
}
