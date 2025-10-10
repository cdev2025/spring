package com.example.cat_wiki;

import com.example.cat_wiki.dto.CatRequestDto;
import com.example.cat_wiki.model.Cat;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.assertj.core.api.Assertions.assertThat;


/*
* CatController 통합 테스트
* 실제 HTTP 요청 없이 Controller 로직을 테스트
* MockMvc : Spring MVC 동작을 실제 서버 실행 없이 테스트 할 수 있게 해주는 객체
* */
@SpringBootTest // 통합 테스트 : Spring Boot 컨텍스트 전체 로드
@AutoConfigureMockMvc // MockMvc 빈 자동 구성해서 주입
class CatWikiApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

	@Test
    @DisplayName("모든 고양이 조회 테스트")
    void testGetAllCats() throws Exception{
        mockMvc.perform(get("/api/cats"))
                .andDo(print())  // 요청/응답 내용을 콘솔에 출력
                .andExpect(status().isOk()) // HTTP 200 OK 검증
                .andExpect(jsonPath("$").isArray()) // 응답이 배열인지 검증
                .andExpect(jsonPath("$.length()").value(3)) // 초기 데이터 3개 검증
                .andExpect(jsonPath("$[0].name").value("Milo")); // 첫번째 고양이 이름 검증
    }

    @Test
    @DisplayName("특정 고양이 조회 성공 테스트")
    void testGetCatById_Success() throws Exception{
        mockMvc.perform(get("/api/cats/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Milo"))
                .andExpect(jsonPath("$.breed").value("Siamese"))
                .andExpect(jsonPath("$.age").value(3));
    }

    @Test
    @DisplayName("존재하지 않는 고양이 조회 테스트")
    void testGetCatById_NotFound() throws Exception{
        mockMvc.perform(get("/api/cats/999"))
                .andDo(print())
                .andExpect(status().isNotFound()); // 404 Not Found 검증
    }

    @Test
    @DisplayName("새로운 고양이 추가 테스트")
    void testAddCat() throws Exception{
        Cat newCat = new Cat(null, "Whiskers", "Korean short", 1);
        String catJson = objectMapper.writeValueAsString(newCat);

        mockMvc.perform(post("/api/cats").contentType(MediaType.APPLICATION_JSON).content(catJson))
                .andDo(print())
                .andExpect(status().isCreated())  // 201 Created 검증
                .andExpect(jsonPath("$.id").exists()) // ID 자동 생성 검증
                .andExpect(jsonPath("$.name").value("Whiskers"))
                .andExpect(jsonPath("$.breed").value("Korean short"))
                .andExpect(jsonPath("$.age").value(1));
    }
    
    @Test
    @DisplayName("고양이 삭제 테스트")
    void testDeleteCat() throws Exception{
        mockMvc.perform(delete("/api/cats/2"))
                .andDo(print())
                .andExpect(status().isOk()) // 200 OK 검증
                .andExpect(content().string("Cat removed")); // 삭제 성공
    }

    @Test
    @DisplayName("잘못된 데이터로 고양이 추가 테스트 - 이름 누락")
    void testAddCat_BadRequest_NameMissing() throws Exception{
        CatRequestDto invalidCatDto = new CatRequestDto("", "Unkown", 0);
        String catJson = objectMapper.writeValueAsString(invalidCatDto);

        MvcResult res = mockMvc.perform(post("/api/cats").contentType(MediaType.APPLICATION_JSON).content(catJson))
                .andDo(print())
                .andExpect(status().isBadRequest()) // 400 Bad Request 검증
                .andReturn();

        assertThat(res.getResponse().getErrorMessage()).isEqualTo("Invalid request content.");
    }

    @Test
    @DisplayName("잘못된 데이터로 고양이 추가 테스트 - 나이 음수")
    void testAddCat_BadReqeust_NegativeAge() throws Exception{
        CatRequestDto invalidCatDto = new CatRequestDto("BadCat", "Unkown", -1);
        String catJson = objectMapper.writeValueAsString(invalidCatDto);

        MvcResult res = mockMvc.perform(post("/api/cats").contentType(MediaType.APPLICATION_JSON).content(catJson))
                .andDo(print())
                .andExpect(status().isBadRequest()) // 400 Bad Request 검증
                .andReturn();

        assertThat(res.getResponse().getErrorMessage()).isEqualTo("Invalid request content.");
    }

}
