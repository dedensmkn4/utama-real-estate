package com.utama.deden.reza.entity.constant.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EApartmentStatus {
    OPEN("OPEN", "open"),
    PENDING("PENDING", "pending"),
    CLOSE("CLOSE", "close")
    ;

    private final String code;

    private final String name;


}
