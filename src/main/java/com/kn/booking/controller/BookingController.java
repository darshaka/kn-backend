package com.kn.booking.controller;

import com.kn.booking.domain.dto.BaseResponse;
import com.kn.booking.domain.dto.CostCalculationPayload;
import com.kn.booking.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/bookings")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @GetMapping
    @CrossOrigin(origins = "${kn.cors.url}")
    private ResponseEntity<BaseResponse> getAllBookings() {
        return ResponseEntity.ok(bookingService.getAllBookings());
    }

    @PostMapping
    @CrossOrigin(origins = "http://localhost:3000")
    private ResponseEntity<BaseResponse> createBookings(@RequestBody CostCalculationPayload payload) {
        return ResponseEntity.ok(bookingService.createBooking(payload));
    }
}
