package com.kn.booking.domain.supportive;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Field;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Customer {
    @Field(name = "name")
    private String name;
    @Field(name = "country")
    private String country;
    @Field(name = "postCode")
    private String postCode;
    @Field(name = "address")
    private String address;
    @Field(name = "phone")
    private String phone;
}
