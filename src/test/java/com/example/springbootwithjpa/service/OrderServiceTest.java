package com.example.springbootwithjpa.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import com.example.springbootwithjpa.domain.Item;
import com.example.springbootwithjpa.domain.Member;
import com.example.springbootwithjpa.domain.Order;
import com.example.springbootwithjpa.domain.OrderStatus;
import com.example.springbootwithjpa.exception.NotEnoughStockException;
import com.example.springbootwithjpa.repository.ItemRepository;
import com.example.springbootwithjpa.repository.MemberRepository;
import com.example.springbootwithjpa.repository.OrderRepository;
import com.example.springbootwithjpa.repository.data.ItemDataSet;
import com.example.springbootwithjpa.repository.data.MemberDataSet;
import java.util.Optional;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
@Rollback
class OrderServiceTest {
    @Autowired
    private EntityManager entityManager;

    @Autowired
    private OrderService orderService;
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private OrderRepository orderRepository;

    @DisplayName("상품 주문이 정상적으로 작동한다")
    @Test
    void 상품_주문() {
        // Given
        Member member = MemberDataSet.testData("rolroralra");
        memberRepository.save(member);

        Long givenItemPrice = 10000L;
        Long givenStockQuantity = 10L;

        Item item = ItemDataSet.testData("Kotlin in Action", givenItemPrice, 10L);
        itemRepository.save(item);

        long givenOrderCount = 2L;

        assertThat(givenOrderCount).isBetween(1L, givenStockQuantity);

        // When
        Long orderId = orderService.takeOrder(member.getId(), item.getId(), givenOrderCount);

        // Then
        Optional<Order> findById = orderRepository.findById(orderId);
        assertThat(findById).isPresent();

        Order order = findById.get();

        assertThat(order.getStatus()).isEqualTo(OrderStatus.ORDER);
        assertThat(order.getOrderItems()).hasSize(1);
        assertThat(order.getTotalPrice()).isEqualTo(givenItemPrice * givenOrderCount);
    }

    @DisplayName("상품을 주문할 때 재고수량이 초과할 경우, NotEnoughStockException 발생")
    @Test
    void 상품_주문시_재고_초과() {
        // Given
        Member member = MemberDataSet.testData("rolroralra");
        memberRepository.save(member);
        Long memberId = member.getId();

        Long givenItemPrice = 10000L;
        Long givenStockQuantity = 10L;

        Item item = ItemDataSet.testData("Kotlin in Action", givenItemPrice, 10L);
        itemRepository.save(item);
        Long itemId = item.getId();

        long givenOrderCount = 11L;

        assertThat(givenOrderCount).isGreaterThan(givenStockQuantity);

        // Expected
        assertThatExceptionOfType(NotEnoughStockException.class).isThrownBy(() ->
            orderService.takeOrder(memberId, itemId, givenOrderCount)
        );

    }

    @DisplayName("주문 취소가 정상적으로 작동한다")
    @Test
    void 주문_취소() {
        // Given
        Member member = MemberDataSet.testData("rolroralra");
        memberRepository.save(member);

        Long givenItemPrice = 10000L;
        Long givenStockQuantity = 10L;

        Item item = ItemDataSet.testData("Kotlin in Action", givenItemPrice, 10L);
        itemRepository.save(item);

        long givenOrderCount = 2L;

        assertThat(givenOrderCount).isBetween(1L, givenStockQuantity);

        Long orderId = orderService.takeOrder(member.getId(), item.getId(), givenOrderCount);

        // When
        orderService.cancelOrder(orderId);

        // Then
        Optional<Order> findById = orderRepository.findById(orderId);

        assertThat(findById).isPresent();
        Order order = findById.get();

        assertThat(order.getStatus()).isEqualTo(OrderStatus.CANCEL);

        assertThat(item.getStockQuantity()).isEqualTo(givenStockQuantity);
    }
}
