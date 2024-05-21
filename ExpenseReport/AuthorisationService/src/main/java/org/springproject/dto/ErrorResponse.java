package org.springproject.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Getter
@Builder
public class ErrorResponse
{
    public String errorCode;
    public String errorMessage;
}
