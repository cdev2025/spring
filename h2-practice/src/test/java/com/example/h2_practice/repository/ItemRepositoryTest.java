package com.example.h2_practice.repository;

import com.example.h2_practice.entity.Item;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

@DataJpaTest
public class ItemRepositoryTest {

    // 테스트 대상 Reposotiry
    @Autowired
    private ItemRepository itemRepository;

    // 테스트용 EntityManager
    private TestEntityManager entityManager;

    // 각 테스트 실행 전 공통 테스트 데이터 준비
    // @BeforeEach : 각 @Test 메서드 실행 전마다 호출
    // persistAndFlush() : 엔티티를 DB에 저장하고 즉시 flush
    @BeforeEach
    void setup(){
        // 다양한 검색 테스트를 위한 테스트 데이터 준비
        Item laptop = Item.builder().name("게이밍 노트북").price(1500000).stock(5).build();
        Item mouse = Item.builder().name("무선 마우스").price(45000).stock(30).build();
        Item keyboard = Item.builder().name("기계식 키보드").price(120000).stock(15).build();

        // 테스트 데이터를 DB에 저장
        entityManager.persistAndFlush(laptop);
        entityManager.persistAndFlush(mouse);
        entityManager.persistAndFlush(keyboard);
    }
}
