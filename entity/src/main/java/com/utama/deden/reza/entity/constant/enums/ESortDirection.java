package com.utama.deden.reza.entity.constant.enums;

import lombok.Getter;

@Getter
public enum ESortDirection {
  ASC("ASC", "ASC"),
  DESC("DESC", "DESC");

  private final String name;
  private final String value;

  ESortDirection(String name, String value) {
    this.name = name;
    this.value = value;
  }
}
