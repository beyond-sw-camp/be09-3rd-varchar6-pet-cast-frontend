package com.varchar6.petcast.domain.request.command.domain.aggregate;

public enum RequestsStatus {
      SENT("SENT", 0)
    , REJECTED("REJECTED", 1)
    , CONFIRMED("CONFIRMED", 2)
    ;
      private final String status;
      private final int index;

      RequestsStatus(String status, int index) {
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
