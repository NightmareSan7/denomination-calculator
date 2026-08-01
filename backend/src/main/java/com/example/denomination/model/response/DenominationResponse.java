package com.example.denomination.model.response;

import com.example.denomination.model.DenominationDifferenceEntry;
import com.example.denomination.model.DenominationEntry;

import java.util.List;

public record DenominationResponse(List<DenominationEntry> denominations,
                                   List<DenominationDifferenceEntry> differences) {
}
