package com.kn.booking.domain.entity;

import com.kn.booking.domain.dto.CostCalculationPayload;
import com.kn.booking.domain.supportive.Box;
import com.kn.booking.domain.supportive.Customer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.math.BigDecimal;


@Getter
@Setter
@Document
@NoArgsConstructor
@AllArgsConstructor
public class Booking {
    @Id
    private String id;
    @Field(name = "sender")
    private Customer sender;
    @Field(name = "receiver")
    private Customer receiver;
    @Field(name = "weight")
    private Integer weight;
    @Field(name = "box")
    private Box box;
    @Field(name = "charges")
    private BigDecimal charges;

    public Booking(CostCalculationPayload payload) {
        this.sender = Customer.builder()
                .address(payload.getFromAddress())
                .country(payload.getFromCountry())
                .postCode(payload.getFromPostCode())
                .build();
        this.receiver = Customer.builder()
                .address(payload.getToAddress())
                .country(payload.getToCountry())
                .postCode(payload.getToPostCode())
                .build();
        this.box = Box.builder()
                .height(payload.getHeight())
                .weight(payload.getWeight())
                .length(payload.getLength())
                .build();
        this.weight = payload.getWeight();
        this.charges = new BigDecimal(payload.getCharges());
    }
}
