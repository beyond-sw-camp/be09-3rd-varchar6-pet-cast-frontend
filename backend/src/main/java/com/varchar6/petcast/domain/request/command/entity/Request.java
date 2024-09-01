package com.varchar6.petcast.domain.request.command.entity;

import com.varchar6.petcast.domain.member.command.domain.aggregate.Member;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

@Entity
@Table(name = "tbl_request")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
public class Request {

    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "content")
    private String content;

    @Column(name = "cost")
    private int cost;

    @Column(name = "time")
    private String time;

    @Column(name = "location")
    private String location;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private Status statusEnum;

    // 작성일자와 수정일자 시각 타입 수정 예정
//    @Column(name = "created_at")
    @CreatedDate
    private String created_at;

//    @Column(name = "updated_at")
    @LastModifiedDate
    private String updated_at;

    @Column(name = "active", nullable = false)
    private boolean active;

    @Column(name = "company_info_request_id")
    private int company_info_request_id;


    @ManyToOne
    @JoinColumn(name = "member_request_id")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "company_info_request_id")
    private CompanyInfo companyInfo;

}
