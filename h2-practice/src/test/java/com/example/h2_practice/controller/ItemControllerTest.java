package com.example.h2_practice.controller;

import com.example.h2_practice.entity.Item;
import com.example.h2_practice.service.ItemService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ItemController.class) // Controller 계층만 로드
public class ItemControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper; // JSON <-> java 객체 간 매핑

    @MockitoBean
    private ItemService itemService;

    @Test
    @DisplayName("GET 전체 상품 조회 : JSON 배열 응답")
    void getAllItems_Success() throws Exception{
        // Mock 데이터 준비
        List<Item> mockItems = Arrays.asList(
                Item.builder().id(1L).name("Laptop").price(9990000).stock(10).build(),
                Item.builder().id(2L).name("Mouse").price(29900).stock(50).build());

        given(itemService.getAllItems()).willReturn(mockItems);

        // GET 요청 수행 및 응답 검증
        mockMvc.perform(get("/api/items"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].name").value("Laptop"));
    }
}
