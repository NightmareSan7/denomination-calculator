package com.example.denomination.model;

import java.math.BigDecimal;

public record DenominationEntry(BigDecimal denomination, long count) {
}
