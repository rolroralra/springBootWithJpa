package com.example.springbootwithjpa.service;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import com.example.springbootwithjpa.domain.Item;
import com.example.springbootwithjpa.repository.ItemRepository;
import com.example.springbootwithjpa.repository.data.ItemDataSet;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
@Rollback
class ItemServiceTest {

    @Autowired
    private ItemService itemService;
    @Autowired
    private ItemRepository itemRepository;

    @DisplayName("상품 저장 정상 작동")
    @Test
    void 상품_저장() {
        // Given
        String givenItemName = "Kotlin in Action";
        Item item = ItemDataSet.testData(givenItemName);

        // When
        Long savedId = itemService.saveItem(item);

        // Then
        assertThat(itemRepository.findById(savedId)).isPresent()
            .get()
            .isNotNull()
            .hasFieldOrPropertyWithValue("name", givenItemName);
    }

}
