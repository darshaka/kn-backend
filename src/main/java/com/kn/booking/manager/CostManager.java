package com.kn.booking.manager;

import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class CostManager {

    public int getDistanceByKm(String fromCountry, String toCountry) {
        return new Random().nextInt(1000);
    }
}