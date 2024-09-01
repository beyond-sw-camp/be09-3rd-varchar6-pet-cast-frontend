package com.varchar6.petcast.domain.request;

import com.varchar6.petcast.domain.member.command.domain.aggregate.Member;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tbl_request")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Request {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "content")
    private String content;

    @Column(name = "cost")
    private int cost;

    @Column(name = "location")
    private String location;

    @Column(name = "time")
    private String time;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(name = "created_at")
    private String created_at;

    @Column(name = "updated_at")
    private String updated_at;

    @Column(name = "active")
    private boolean active;


    @ManyToOne
    @JoinColumn(name = "company_info_request_id")
    private CompanyInfo companyInfo;

    @ManyToOne
    @JoinColumn(name = "member_request_id")
    private Member member;

}
