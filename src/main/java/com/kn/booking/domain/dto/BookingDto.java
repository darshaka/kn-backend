package com.kn.booking.domain.dto;

import com.kn.booking.domain.entity.Booking;
import com.kn.booking.domain.supportive.Box;
import com.kn.booking.domain.supportive.Customer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookingDto {
    private String id;
    private String name;
    private Customer sender;
    private Customer receiver;
    private int weight;
    private Box box;
    private String charges;

    public BookingDto(Booking booking) {
        this.id = booking.getId();
        this.name = booking.getSender().getName() + " " + booking.getReceiver().getName();
        this.sender = booking.getSender();
        this.receiver = booking.getReceiver();
        this.weight = booking.getWeight();
        this.box = booking.getBox();
        this.charges = String.valueOf(booking.getCharges());
    }
}
