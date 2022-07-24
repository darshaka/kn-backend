package com.kn.booking.service;

import com.kn.booking.domain.dto.BaseResponse;
import com.kn.booking.domain.dto.BookingDto;
import com.kn.booking.domain.dto.CostCalculationPayload;
import com.kn.booking.domain.entity.Booking;
import com.kn.booking.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookingServiceImpl implements BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Override
    public BaseResponse createBooking(CostCalculationPayload payload) {
        bookingRepository.save(new Booking(payload));
        return BaseResponse.builder()
                .status("200")
                .build();
    }

    @Override
    public BaseResponse getAllBookings() {
        return BaseResponse.builder()
                .status("200")
                .dataList(bookingRepository.findAll()
                        .stream()
                        .map(booking -> new BookingDto(booking))
                        .collect(Collectors.toList()))
                .build();
    }
}
