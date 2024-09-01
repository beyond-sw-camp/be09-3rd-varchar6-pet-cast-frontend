package com.varchar6.petcast.domain.gather.command.domain.aggregate.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GatherMemberPK implements Serializable {

    @Column(name = "member_group_member_id")
    private int memberId;

    @Column(name = "gather_group_member_id")
    private int gatherId;

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        GatherMemberPK that = (GatherMemberPK) object;
        return memberId == that.memberId && gatherId == that.gatherId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(memberId, gatherId);
    }
}
