package com.varchar6.petcast.domain.gather.command.domain.aggregate.entity;

import com.varchar6.petcast.domain.gather.command.domain.aggregate.GatherRole;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tbl_group_member")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@ToString
public class GatherMember {

    @Column(name = "role", nullable = false)
    @Enumerated(EnumType.STRING)
    private GatherRole role = GatherRole.LEADER;

    @EmbeddedId
    private GatherMemberPK gatherMemberPK;
}
