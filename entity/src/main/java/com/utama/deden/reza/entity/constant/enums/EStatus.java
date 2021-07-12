package com.utama.deden.reza.entity.constant.enums;

import lombok.Getter;

@Getter
public enum EStatus {
  ACTIVE("ACTIVE", "active"),
  INACTIVE("INACTIVE", "inactive")
  ;

  private final String code;

  private final String name;

  EStatus(String code, String name) {
    this.code = code;
    this.name = name;
  }
}
