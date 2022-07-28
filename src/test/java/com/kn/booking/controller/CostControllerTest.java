package com.kn.booking.controller;

import com.google.gson.Gson;
import com.kn.booking.domain.dto.BaseResponse;
import com.kn.booking.exception.KNException;
import com.kn.booking.service.CostService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CostController.class)
@AutoConfigureMockMvc(addFilters = false)
@Import(CostController.class)
class CostControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CostService costService;

    @Test
    void calculateCost_status() throws Exception {
        when(costService.calculateCost(any())).thenReturn(getBaseResponse());

        mockMvc.perform(
                post("/api/cost")
                        .contentType(MediaType.APPLICATION_JSON).content(getPayload())
                        .characterEncoding("utf-8")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void calculateCost_message() throws Exception {
        when(costService.calculateCost(any())).thenReturn(getBaseResponse());

        mockMvc.perform(
                post("/api/cost")
                        .contentType(MediaType.APPLICATION_JSON).content(getPayload())
                        .characterEncoding("utf-8")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.message").value("test"));
    }

    @Test
    void calculateCost_invalid_weight() throws Exception {
        when(costService.calculateCost(any())).thenReturn(getBaseResponse());

        mockMvc.perform(
                post("/api/cost")
                        .contentType(MediaType.APPLICATION_JSON).content(getErrorPayload())
                        .characterEncoding("utf-8")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void calculateCost_exception() throws Exception {

        when(costService.calculateCost(any())).thenThrow(new KNException("Invalid"));

        MvcResult mvcResult = mockMvc.perform(
                post("/api/cost")
                        .contentType(MediaType.APPLICATION_JSON).content(getPayload())
                        .characterEncoding("utf-8")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn();

        String contentAsString = mvcResult.getResponse().getContentAsString();
        BaseResponse baseResponse = new Gson().fromJson(contentAsString, BaseResponse.class);
        assertEquals("500", baseResponse.getStatus());

    }

    private BaseResponse getBaseResponse() {
        return BaseResponse.builder()
                .status("200")
                .message("test")
                .data("120")
                .build();
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

    private String getErrorPayload() {
        return "{" +
                "\"fromPostCode\":\"234\"," +
                "\"toPostCode\":\"234\"," +
                "\"fromCountry\":\"Sri Lanka\"," +
                "\"toCountry\":\"Sri Lanka\"," +
                "\"fromAddress\":\"Sri Lanka\"," +
                "\"toAddress\":\"Sri Lanka\"," +
                "\"weight\":\"ab\"," +
                "\"length\":12," +
                "\"width\":12," +
                "\"height\":12," +
                "\"charges\":\"120.3\"" +
                "}";
    }
}