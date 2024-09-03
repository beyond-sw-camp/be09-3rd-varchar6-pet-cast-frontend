package com.varchar6.petcast.domain.proposalandestimate.command.domain.aggregate;

public enum EstimatesStatus {
    SENT("SENT", 0),
    CONFIRMED("CONFIRMED", 1),
    REJECTED("REJECTED",2);

    private final String status;
    private final int index;

    EstimatesStatus(String status, int index) {
        this.status = status;
        this.index = index;
    }

    public String getStatus() {
        return status;
    }

    public int getIndex() {
        return index;
    }
}
