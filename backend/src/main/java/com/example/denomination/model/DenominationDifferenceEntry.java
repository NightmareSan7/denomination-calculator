package com.example.denomination.model;

import java.math.BigDecimal;

public record DenominationDifferenceEntry(BigDecimal denomination, long differenceCount) {
}