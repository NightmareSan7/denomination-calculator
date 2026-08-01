package com.example.denomination.model.enums;


import lombok.Getter;

@Getter
public enum ErrorCodesEnum {

    E00000000001("ERROR", "General error.");

    private final String tag;
    private final String description;

    ErrorCodesEnum(String tag, String description) {
        this.tag = tag;
        this.description = description;
    }

    public String getName() {
        return name();
    }
}