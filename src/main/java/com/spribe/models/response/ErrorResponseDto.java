package com.spribe.models.response;

import lombok.Data;

@Data
public class ErrorResponseDto {
    private String timestamp;
    private int status;
    private String error;
    private String message;
    private String path;
}
