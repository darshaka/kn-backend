package com.kn.booking.service;

import com.kn.booking.domain.dto.BaseResponse;
import com.kn.booking.domain.dto.BookingDto;
import com.kn.booking.domain.dto.CostCalculationPayload;

import java.util.List;

public interface BookingService {
    BaseResponse createBooking(CostCalculationPayload payload);
    BaseResponse getAllBookings();
}
