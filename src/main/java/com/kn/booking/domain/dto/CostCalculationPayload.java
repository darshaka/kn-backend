package com.kn.booking.domain.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Pattern;

@Setter
@Getter
public class CostCalculationPayload {
    private String fromCountry;
    private String fromAddress;
    @Pattern(regexp = "^(|.{1,35}$)")
    private String fromPostCode;
    private String toCountry;
    private String toAddress;
    @Pattern(regexp = "^(|.{1,35}$)")
    private String toPostCode;
    private int weight;
    private int length;
    private int width;
    private int height;
    private String charges;
}
