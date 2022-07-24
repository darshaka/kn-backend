package com.kn.booking.service;

import com.kn.booking.domain.dto.BaseResponse;
import com.kn.booking.domain.dto.CostCalculationPayload;
import com.kn.booking.exception.KNException;

public interface CostService {
    BaseResponse calculateCost(CostCalculationPayload payload) throws KNException;
}
