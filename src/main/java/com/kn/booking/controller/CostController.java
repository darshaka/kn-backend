package com.kn.booking.controller;

import com.kn.booking.domain.dto.BaseResponse;
import com.kn.booking.domain.dto.CostCalculationPayload;
import com.kn.booking.exception.KNException;
import com.kn.booking.service.CostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("api/cost")
public class CostController {

    @Autowired
    private CostService costService;

    @PostMapping
    @CrossOrigin(origins = "${kn.cors.url}")
    public ResponseEntity<BaseResponse> calculateCost(@Valid @RequestBody CostCalculationPayload payload) throws KNException {
        return ResponseEntity.ok(costService.calculateCost(payload));
    }
}
