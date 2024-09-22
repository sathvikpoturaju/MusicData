package com.sathvik.MusicData.util;

import lombok.Data;

@Data
public class ErrorInfo {
    private String errorMessage;
    private Integer httpStatus;
}