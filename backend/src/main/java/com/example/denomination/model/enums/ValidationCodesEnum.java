package com.example.denomination.model.enums;


import lombok.Getter;

@Getter
public enum ValidationCodesEnum {

    V000000001("TOOLING.DENOMINATION.REQUEST.INVALID");

    private final String tag;

    ValidationCodesEnum(String tag) {
        this.tag = tag;
    }

    public String getName() {
        return name();
    }
}