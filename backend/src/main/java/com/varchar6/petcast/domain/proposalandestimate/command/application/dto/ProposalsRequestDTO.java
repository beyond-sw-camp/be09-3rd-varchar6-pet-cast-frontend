package com.varchar6.petcast.domain.proposalandestimate.command.application.dto;


import com.varchar6.petcast.domain.proposalandestimate.command.domain.aggregate.ProposalsStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.stereotype.Component;

//@Data
@Builder
@AllArgsConstructor
@Component
public class ProposalsRequestDTO {
    private int id;
    private String content;
    private String hopeLocation;
    private String hopeTime;
    private int hopeCost;
    private ProposalsStatus status;
    private String createdAt;
    private String updatedAt;
    private boolean active;
    private int memberId;

    public ProposalsRequestDTO () {
    }

    public ProposalsRequestDTO ( int id, int memberId, boolean active, String updatedAt, String createdAt, ProposalsStatus status, String hopeTime, String hopeLocation, String content, int hopeCost ) {
        this.id = id;
        this.memberId = memberId;
        this.active = active;
        this.updatedAt = updatedAt;
        this.createdAt = createdAt;
        this.status = status;
        this.hopeTime = hopeTime;
        this.hopeLocation = hopeLocation;
        this.content = content;
        this.hopeCost = hopeCost;
    }

    public boolean isActive () {
        return active;
    }

    public int getId () {
        return id;
    }

    public String getContent () {
        return content;
    }

    public String getHopeLocation () {
        return hopeLocation;
    }

    public String getHopeTime () {
        return hopeTime;
    }

    public int getHopeCost () {
        return hopeCost;
    }

    public ProposalsStatus getStatus () {
        return status;
    }

    public String getCreatedAt () {
        return createdAt;
    }

    public String getUpdatedAt () {
        return updatedAt;
    }

    public int getMemberId () {
        return memberId;
    }

    public void setId ( int id ) {
        this.id = id;
    }

    public void setContent ( String content ) {
        this.content = content;
    }

    public void setHopeLocation ( String hopeLocation ) {
        this.hopeLocation = hopeLocation;
    }

    public void setHopeTime ( String hopeTime ) {
        this.hopeTime = hopeTime;
    }

    public void setHopeCost ( int hopeCost ) {
        this.hopeCost = hopeCost;
    }

    public void setStatus ( ProposalsStatus status ) {
        this.status = status;
    }

    public void setCreatedAt ( String createdAt ) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt ( String updatedAt ) {
        this.updatedAt = updatedAt;
    }

    public void setActive ( boolean active ) {
        this.active = active;
    }

    public void setMemberId ( int memberId ) {
        this.memberId = memberId;
    }

    @Override
    public String toString () {
        return "ProposalsRequestDTO{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", hopeLocation='" + hopeLocation + '\'' +
                ", hopeTime='" + hopeTime + '\'' +
                ", hopeCost=" + hopeCost +
                ", status=" + status +
                ", createdAt='" + createdAt + '\'' +
                ", updatedAt='" + updatedAt + '\'' +
                ", active=" + active +
                ", memberId=" + memberId +
                '}';
    }
}
