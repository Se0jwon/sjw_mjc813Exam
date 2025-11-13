package com.mjc813.band_jpa.common;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ResponseDto {
    private Integer code;
    private String message;
    private Object resData;
}
