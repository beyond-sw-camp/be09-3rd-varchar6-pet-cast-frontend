package com.varchar6.petcast.domain.proposalandestimate.aggregate;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.Id;

@Entity
@Table(name = "tbl_estimate")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Estimate {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "cost")
    private int cost;

    @Enumerated(EnumType.STRING)
    private EstimateStatus status;

    public void accept() {
        this.status = EstimateStatus.CONFIRMED;
    }

    public void reject() {
        this.status = EstimateStatus.REJECTED;
    }


    @Column(name = "created_at")
    private String created_at;

    @Column(name = "updated_at")
    private String updated_at;

    @Column(name = "active")
    private boolean active;

    @Column(name = "company_id")
    private int companyInfo;

    @Column(name = "proposal_id")
    private int proposal_id;
}