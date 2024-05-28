package org.springproject.controller.dto;


import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ErrorResponse {

    String errorCode;
    String errorMessage;


}
