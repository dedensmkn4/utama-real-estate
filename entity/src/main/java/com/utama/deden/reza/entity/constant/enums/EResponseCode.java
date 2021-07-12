package com.utama.deden.reza.entity.constant.enums;

import lombok.Getter;

@Getter
public enum EResponseCode {
  SUCCESS("SUCCESS", "SUCCESS"),
  SYSTEM_ERROR("SYSTEM_ERROR", "Contact our team"),
  DUPLICATE_DATA("DUPLICATE_DATA", "Duplicate data"),
  DATA_NOT_EXIST("DATA_NOT_EXIST", "No data exist"),
  RUNTIME_ERROR("RUNTIME_ERROR", "Runtime Error"),
  BIND_ERROR("BIND_ERROR", "Please fill in mandatory parameter");

  private final String code;

  private final String message;

  EResponseCode(String code, String message) {
    this.code = code;
    this.message = message;
  }
}
