package com.kn.booking.service;

import com.kn.booking.domain.dto.BaseResponse;
import com.kn.booking.domain.dto.CostCalculationPayload;

public interface BookingService {
    BaseResponse createBooking(CostCalculationPayload payload);
    BaseResponse getAllBookings();
}
