package com.varchar6.petcast.common.response;


import lombok.*;
import org.springframework.http.HttpStatus;

import java.util.Map;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class ResponseMessage {
    private HttpStatus httpStatus;
    private String message;
    private Object result;
}
