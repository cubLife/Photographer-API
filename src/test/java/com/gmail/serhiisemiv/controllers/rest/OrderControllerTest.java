package com.gmail.serhiisemiv.controllers.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gmail.serhiisemiv.dto.OrderDto;
import com.gmail.serhiisemiv.dto.mappers.CostumerFeedbackMapper;
import com.gmail.serhiisemiv.dto.mappers.OrderMapper;
import com.gmail.serhiisemiv.modelAsemblers.CostumerFeedbackDtoModelAssembler;
import com.gmail.serhiisemiv.modelAsemblers.OrderModelAssembler;
import com.gmail.serhiisemiv.modeles.Order;
import com.gmail.serhiisemiv.service.CostumerFeedbackService;
import com.gmail.serhiisemiv.service.OrderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@WebMvcTest
@ContextConfiguration(classes = {OrderController.class})
class OrderControllerTest {
    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;
    @MockBean
    private OrderService orderService;
    @MockBean
    private OrderMapper mapper;
    @MockBean
    private OrderModelAssembler modelAssembler;
    private static final String API_ORDERS_URL= "/api/orders";
    private static final OrderDto ORDER = OrderDto.builder().costumerFirstName("Jon").costumerLastName("Doe")
            .costumerPhone("+48123456789").costumerEmail("jon_doe@gmail.com").photoSessionName("My Photo session").build();

    @Test
    void saveOrder() throws Exception {
        mockMvc.perform(post(API_ORDERS_URL).content(objectMapper.writeValueAsString(ORDER))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andDo(print());
    }

    @Test
    void getOrderById() throws Exception {
        mockMvc.perform(get(API_ORDERS_URL+"/{order-id}",1).
                contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void getAllOrders() throws Exception {
        mockMvc.perform(get(API_ORDERS_URL+"/list")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void getByOrderStatus() throws Exception {
        mockMvc.perform(get(API_ORDERS_URL+"/order-status/{status}/list", "new")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void deleteOrderById() throws Exception {
        mockMvc.perform(delete(API_ORDERS_URL+"/{order-id}", 1)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent())
                .andDo(print());
    }

    @Test
    void editOrder() throws Exception {
        mockMvc.perform(put(API_ORDERS_URL+"/{id}",1)
                        .content(objectMapper.writeValueAsString(ORDER))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }

}