package com.varchar6.petcast.domain.gather.command.domain.aggregate.entity;

import com.varchar6.petcast.domain.gather.command.domain.aggregate.GatherRole;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tbl_group_member")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class GatherMember {

    @Column(name = "role", nullable = false)
    @Enumerated(EnumType.STRING)
    private GatherRole role = GatherRole.LEADER;

    @Id
    @Column(name = "member_group_member_id", nullable = false)
    private int memberId;

    @Column(name = "gather_group_member_id", nullable = false)
    private int gatherId;
}
