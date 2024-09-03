package com.varchar6.petcast.domain.request.command.domain.aggregate;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tbl_request")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Requests {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "content")
    private String content;

    @Column(name = "hope_cost")
    private int hopeCost;

    @Column(name = "hope_location")
    private String hopeLocation;

    @Column(name = "hope_time")
    private String hopeTime;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(name = "created_at")
    private String createdAt;

    @Column(name = "updated_at")
    private String updatedAt;

    @Column(name = "active")
    private boolean active;

    @Column(name = "company_id")
    private int companyId;

    @Column(name = "member_id")
    private int memberId;


}
