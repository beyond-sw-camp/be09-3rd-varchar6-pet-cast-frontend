package com.varchar6.petcast.domain.request.quary.dto;

import com.varchar6.petcast.domain.member.command.domain.aggregate.Member;
import com.varchar6.petcast.domain.request.command.entity.Status;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

@Data
public class RequestDTO {

    private int id;
    private String content;
    private int cost;
    private String time;
    private String location;
    private Status statusEnum;
    private String created_at;
    private String updated_at;
    private boolean active;
    private int company_info_request_id;


    private Member member;
    private CompanyInfo companyInfo;

}
