package com.utama.deden.reza.entity.constant.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EOfferType {
    BUY("BUY", "buy"),
    RENT("RENT", "rent");

    private final String code;

    private final String name;
}
