package com.kn.booking.service;

import com.kn.booking.domain.dto.BaseResponse;
import com.kn.booking.domain.dto.CostCalculationPayload;
import com.kn.booking.exception.KNException;
import com.kn.booking.manager.CostManager;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@WebMvcTest
class CostServiceImplTest {

    @MockBean
    private CostManager costManager;

    @Test
    void calculateCost() throws KNException {
        when(costManager.getDistanceByKm(any(), any()))
                .thenReturn(150);

        CostServiceImpl costService = new CostServiceImpl();
        BaseResponse baseResponse = costService.calculateCost(getCostCalculationPayload());

        assertEquals(baseResponse.getStatus(), "300");
    }

    private CostCalculationPayload getCostCalculationPayload() {
        CostCalculationPayload payload = new CostCalculationPayload();
        payload.setFromPostCode("124");
        payload.setFromAddress("address1");
        payload.setFromCountry("fromCountry");
        payload.setHeight(12);
        payload.setLength(23);
        payload.setLength(34);
        payload.setToAddress("toAddress");
        payload.setToCountry("ToCountry");
        payload.setToPostCode("34343");

        return payload;
    }
}