package com.kn.booking.service;

import com.kn.booking.domain.dto.BaseResponse;
import com.kn.booking.domain.dto.CostCalculationPayload;
import com.kn.booking.exception.KNException;
import com.kn.booking.manager.CostManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Random;

@Service
public class CostServiceImpl implements CostService {

    @Autowired
    private CostManager costManager;

    @Value("${const.cost.perKm:0}")
    private BigDecimal costPerKm;

    @Value("${const.cost.perKg:0}")
    private BigDecimal costPerKg;

    @Value("${const.cost.container:0}")
    private BigDecimal costContainer;

    @Override
    public BaseResponse calculateCost(CostCalculationPayload payload) throws KNException {
        final int distanceByKm = costManager.getDistanceByKm(payload.getFromCountry(), payload.getToCountry());
        if(distanceByKm <= 0) {
            throw new KNException("Invalid from/to country");
        }
        return BaseResponse.builder()
                .status("200")
                .data(getTotalCost(distanceByKm, payload))
                .build();
    }

    /**
     * This is for the temporary, later we need to store this information
     * @param distanceByKm
     * @param payload
     * @return totalCost
     */
    private BigDecimal getTotalCost(int distanceByKm, CostCalculationPayload payload) {
        BigDecimal b1 = priceByKm(distanceByKm);
        BigDecimal b2 = priceByKg(payload.getWeight());
        BigDecimal b3 = getContainerCost();
        BigDecimal b4 = getDiscount();
        return b1.add(b2).add(b3).add(b4);
    }

    private BigDecimal priceByKm(int distance) {
        return costPerKm.multiply(new BigDecimal(distance));
    }

    private BigDecimal priceByKg(int weight) {
        return costPerKg.multiply(new BigDecimal(weight));
    }

    private BigDecimal getContainerCost() {
        return costContainer;
    }

    private BigDecimal getDiscount() {
        return new BigDecimal(new Random().nextInt(10) * (-1));
    }
}
