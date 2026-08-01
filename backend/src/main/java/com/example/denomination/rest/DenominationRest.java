package com.example.denomination.rest;

import com.example.denomination.exceptions.DefaultRestException;
import com.example.denomination.exceptions.ValidationException;
import com.example.denomination.model.enums.ErrorCodesEnum;
import com.example.denomination.model.request.DenominationRequest;
import com.example.denomination.model.response.DenominationResponse;
import com.example.denomination.service.DenominationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/tooling")
@Slf4j
public class DenominationRest {
    @Autowired
    private DenominationService denominationService;

    @PostMapping("/denomination/v1")
    public DenominationResponse calculateDenomination(@RequestBody DenominationRequest denominationRequest) throws DefaultRestException {
        try {
            return this.denominationService.processDenomination(denominationRequest);
        } catch (ValidationException ve) {
            throw new DefaultRestException(ve);
        } catch (Exception e) {
            log.error("General unknown error while trying to calculate Denomination. Error {}", e.getMessage(), e);
            throw new DefaultRestException(ErrorCodesEnum.E00000000001);
        }
    }
}
