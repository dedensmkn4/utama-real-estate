package com.utama.deden.reza.libraries.utility;

import com.utama.deden.reza.rest.web.model.response.BaseResponse;
import java.util.Date;
import java.util.List;

public class BaseResponseHelper {
    public static <T> BaseResponse<T> constructResponse(String code, String message, List<String> errors, T data) {
        return BaseResponse.<T>builder()
                .code(code)
                .message(message)
                .errors(errors)
                .serverTime(new Date())
                .data(data)
                .build();
    }
    private BaseResponseHelper(){}
}
