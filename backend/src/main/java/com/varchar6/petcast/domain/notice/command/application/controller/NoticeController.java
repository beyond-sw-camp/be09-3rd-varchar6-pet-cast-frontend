package com.varchar6.petcast.domain.notice.command.application.controller;

import com.varchar6.petcast.common.response.ResponseMessage;
import com.varchar6.petcast.domain.notice.command.application.dto.request.NoticeUpdateRequestDTO;
import com.varchar6.petcast.domain.notice.command.application.dto.request.NoticeWriteRequestDTO;
import com.varchar6.petcast.domain.notice.command.application.dto.response.NoticeResponseDTO;
import com.varchar6.petcast.domain.notice.command.application.service.NoticeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Slf4j
@RestController(value = "commandNoticeController")
@RequestMapping("/api/v1/notice")
public class NoticeController {
    private final NoticeService noticeService;

    @Autowired
    public NoticeController(NoticeService noticeService) {
        this.noticeService = noticeService;
    }

    @PostMapping("")
    private ResponseEntity<ResponseMessage> createNotice(@RequestBody NoticeWriteRequestDTO noticeWriteRequestDTO){

        int result = noticeService.insertNotice(noticeWriteRequestDTO);

        if(result == 1)
            return ResponseEntity.ok(new ResponseMessage(201, "Notice 생성 성공", null));
        else
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseMessage(500, "Notice 생성 실패", null));
    }

    @PutMapping("")
    private ResponseEntity<ResponseMessage> updateNotice(@RequestBody NoticeUpdateRequestDTO noticeUpdateRequestDTO){

        NoticeResponseDTO noticeResponseDTO = noticeService.updateNotice(noticeUpdateRequestDTO);

        return ResponseEntity.ok(new ResponseMessage(201, "Notice 수정 성공"
                , noticeResponseDTO));
    }

    @DeleteMapping("")
    private ResponseEntity<ResponseMessage> deleteNotice(@RequestBody Map<String, Integer> request){
        int id = request.get("id");
        int result = noticeService.deleteNotice(id);

        if(result == 1)
            return ResponseEntity.ok(new ResponseMessage(201, "Notice 삭제 성공", null));
        else
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseMessage(500, "Notice 삭제 실패", null));
    }
}
