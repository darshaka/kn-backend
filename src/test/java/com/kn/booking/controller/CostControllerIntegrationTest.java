package com.kn.booking.controller;

import com.google.gson.Gson;
import com.kn.booking.domain.dto.BaseResponse;
import com.kn.booking.manager.CostManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class CostControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CostManager costManager;

    @Test
    void calculateCost() throws Exception {

        when(costManager.getDistanceByKm(any(), any()))
                .thenReturn(10);

        mockMvc.perform(
                post("/api/cost")
                        .contentType(MediaType.APPLICATION_JSON).content(getPayload())
                        .characterEncoding("utf-8")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

    @Test
    void calculateCost2() throws Exception {

        when(costManager.getDistanceByKm(any(), any()))
                .thenReturn(10);

        MvcResult mvcResult = mockMvc.perform(
                post("/api/cost")
                        .contentType(MediaType.APPLICATION_JSON).content(getPayload())
                        .characterEncoding("utf-8")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn();

        String contentAsString = mvcResult.getResponse().getContentAsString();
        BaseResponse baseResponse = new Gson().fromJson(contentAsString, BaseResponse.class);
        assertTrue(new BigDecimal(baseResponse.getData().toString()).intValue() > 0);

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
}