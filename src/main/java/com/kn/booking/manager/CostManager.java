package com.kn.booking.manager;

import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class CostManager {

    /*
    For this PoC application, we don't get the actual distance.
    Just generating the random number less than 1000.
    Later we need to call actual API to get the distance.
     */
    public int getDistanceByKm(String fromCountry, String toCountry) {
        return new Random().nextInt(1000);
    }
}