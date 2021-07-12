package com.utama.deden.reza.rest.web.model.response;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BaseResponse<T> implements Serializable {
    private String code;
    private String message;
    private List<String> errors;
    private T data;
    private Date serverTime;
}
