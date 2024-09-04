package com.varchar6.petcast.domain.proposalandestimate.command.application.dto;

import lombok.Builder;
import org.springframework.stereotype.Component;


@Component
@Builder
public class ProposalsResponseDTO {
    private String content;
    private String hopeLocation;
    private String hopeTime;
    private int hopeCost;

    public ProposalsResponseDTO () {
    }

    public ProposalsResponseDTO ( String content, String hopeLocation, String hopeTime, int hopeCost ) {
        this.content = content;
        this.hopeLocation = hopeLocation;
        this.hopeTime = hopeTime;
        this.hopeCost = hopeCost;
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

    public void setContent ( String content ) {
        this.content = content;
    }

    public void setHopeLocation ( String hopeLocation ) {
        this.hopeLocation = hopeLocation;
    }

    public void setHopeCost ( int hopeCost ) {
        this.hopeCost = hopeCost;
    }

    public void setHopeTime ( String hopeTime ) {
        this.hopeTime = hopeTime;
    }

    @Override
    public String toString () {
        return "ProposalsResponseDTO{" +
                "content='" + content + '\'' +
                ", hopeLocation='" + hopeLocation + '\'' +
                ", hopeTime='" + hopeTime + '\'' +
                ", hopeCost=" + hopeCost +
                '}';
    }
}
