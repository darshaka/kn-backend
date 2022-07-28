package com.kn.booking.controller;

import com.kn.booking.domain.dto.BaseResponse;
import com.kn.booking.service.BookingService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BookingController.class)
class BookingControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookingService bookingService;

    @Test
    void createBooking() throws Exception {
        when(bookingService.createBooking(any())).thenReturn(getBaseResponse());

        mockMvc.perform(
                post("/api/bookings")
                        .content(getPayload())
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("utf-8")
                        .accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk());
    }

    @Test
    void createBookingStatus() throws Exception {
        when(bookingService.createBooking(any())).thenReturn(getBaseResponse());

        mockMvc.perform(
                post("/api/bookings")
                        .contentType(MediaType.APPLICATION_JSON).content(getPayload())
                        .characterEncoding("utf-8")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.status").value("200"));
    }

    private String getPayload() {
        return "{" +
                "\"fromPostCode\":\"234\"," +
                "\"toPostCode\":\"234\"," +
                "\"fromCountry\":\"Sri Lanka\"," +
                "\"toCountry\":\"Sri Lanka\"," +
                "\"fromAddress\":\"Sri Lanka\"," +
                "\"toAddress\":\"Sri Lanka\"," +
                "\"weight\":12," +
                "\"length\":12," +
                "\"width\":12," +
                "\"height\":12," +
                "\"charges\":\"120.3\"" +
                "}";
    }

    private BaseResponse getBaseResponse() {
        return BaseResponse.builder()
                .status("200")
                .build();
    }
}