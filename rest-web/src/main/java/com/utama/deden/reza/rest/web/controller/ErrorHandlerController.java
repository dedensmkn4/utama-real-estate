package com.utama.deden.reza.rest.web.controller;

import com.utama.deden.reza.entity.constant.enums.EResponseCode;
import com.utama.deden.reza.libraries.exception.BusinessLogicException;
import com.utama.deden.reza.libraries.utility.BaseResponseHelper;
import com.utama.deden.reza.rest.web.model.response.BaseResponse;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import springfox.documentation.annotations.ApiIgnore;

@ApiIgnore
@RestControllerAdvice
@Slf4j
public class ErrorHandlerController {

  @ExceptionHandler(BindException.class)
  @ResponseStatus(value = HttpStatus.BAD_REQUEST)
  public BaseResponse bindException(BindException be) {
    log.info("BindException = {}", be);
    List<FieldError> bindErrors = be.getFieldErrors();
    List<String> errors = new ArrayList<>();
    for (FieldError fieldError : bindErrors) {
      errors.add(fieldError.getField() + " " + fieldError.getDefaultMessage());
    }

    return BaseResponseHelper.constructResponse(EResponseCode.BIND_ERROR.getCode(),
        EResponseCode.BIND_ERROR.getMessage(),
        errors, null);
  }

  @ExceptionHandler(Exception.class)
  @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
  public BaseResponse exception(Exception e) {
    log.warn("Exception = {}", e);
    return BaseResponseHelper.constructResponse(EResponseCode.SYSTEM_ERROR.getCode(),
        EResponseCode.SYSTEM_ERROR.getMessage(),
        null, null);
  }

  @ExceptionHandler(RuntimeException.class)
  @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
  public BaseResponse runTimeException(RuntimeException re) {
    log.info("Runtime Error = {}", re);
    return BaseResponseHelper.constructResponse(EResponseCode.RUNTIME_ERROR.getCode(),
        EResponseCode.RUNTIME_ERROR.getMessage(),
        null, null);
  }

  @ExceptionHandler(BusinessLogicException.class)
  public BaseResponse businessLogicException(BusinessLogicException ble) {
    log.info("BusinessLogicException = {}", ble);
    return BaseResponseHelper.constructResponse(ble.getCode(), ble.getMessage(), null, null);
  }

}
