package com.varchar6.petcast.domain.notice.command.application.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class NoticeResponseDTO {
    private int id;
    private String title;
    private String createdAt;
    private String updatedAt;
    private boolean activeYn;
    private String description;
    private int view;
    private int memberNoticeId;
    private boolean topFix;
}
