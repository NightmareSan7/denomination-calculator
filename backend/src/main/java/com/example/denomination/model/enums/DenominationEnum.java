package com.example.denomination.model.enums;

import lombok.Getter;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

@Getter
public enum DenominationEnum {
    EUR_200("200"),
    EUR_100("100"),
    EUR_50("50"),
    EUR_20("20"),
    EUR_10("10"),
    EUR_5("5"),
    EUR_2("2"),
    EUR_1("1"),
    CENT_50("0.50"),
    CENT_20("0.20"),
    CENT_10("0.10"),
    CENT_5("0.05"),
    CENT_2("0.02"),
    CENT_1("0.01");

    private final BigDecimal value;

    DenominationEnum(String value) {
        this.value = new BigDecimal(value);
    }

    /**
     * Retrieves the current Values of {@link DenominationEnum} and returns a List ordered by descending value
     *
     * @return List<{@link DenominationEnum}> by descending value
     */
    public static List<DenominationEnum> orderedByDescending() {
        return Arrays.stream(DenominationEnum.values()).sorted(Comparator.comparing(DenominationEnum::getValue).reversed()).toList();
    }
}
