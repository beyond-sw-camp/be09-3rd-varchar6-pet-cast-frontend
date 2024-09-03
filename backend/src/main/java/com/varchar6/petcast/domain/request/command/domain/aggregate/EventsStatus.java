package com.varchar6.petcast.domain.request.command.domain.aggregate;

public enum EventsStatus {
      READY("SENT", 0)
    , INPROGRESSING("REJECTED", 1)
    , DONE("CONFIRMED", 2)
    ;
      private final String status;
      private final int index;

      EventsStatus(String status, int index) {
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
