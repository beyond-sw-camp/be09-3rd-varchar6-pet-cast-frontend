package com.varchar6.petcast.domain.company.command.domain.aggregate;

import com.varchar6.petcast.domain.member.command.domain.aggregate.RoleMemberPk;
import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "tbl_company_catgory")
@IdClass(CompanyCategoryPK.class)
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CompanyCategory {

    @Id
    @Column(name = "category_id")
    private int categoryId;

    @Id
    @Column(name = "company_id")
    private int companyId;
}
