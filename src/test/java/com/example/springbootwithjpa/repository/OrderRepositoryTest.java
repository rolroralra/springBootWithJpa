package com.example.springbootwithjpa.repository;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.springbootwithjpa.controller.dto.OrderSearchDto;
import com.example.springbootwithjpa.domain.Delivery;
import com.example.springbootwithjpa.domain.Item;
import com.example.springbootwithjpa.domain.Member;
import com.example.springbootwithjpa.domain.Order;
import com.example.springbootwithjpa.domain.OrderItem;
import com.example.springbootwithjpa.domain.OrderStatus;
import com.example.springbootwithjpa.repository.common.JpaRepositoryTest;
import com.example.springbootwithjpa.repository.data.ItemDataSet;
import com.example.springbootwithjpa.repository.data.MemberDataSet;
import com.example.springbootwithjpa.repository.data.OrderDataSet;
import com.example.springbootwithjpa.repository.impl.OrderRepositoryImpl;
import java.util.List;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;

class OrderRepositoryTest extends JpaRepositoryTest<Order, Long> {

    @Autowired
    private EntityManager em;

    @Autowired
    private OrderRepository repository;

    @Autowired
    @Qualifier("orderRepositoryImpl")
    private OrderRepositoryImpl customRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private DeliveryRepository deliveryRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Override
    protected JpaRepository<Order, Long> repository() {
        return repository;
    }

    @Override
    protected Order createTestInstance() {
        return OrderDataSet.testData();
    }

    @DisplayName("주문 검색이 정상적으로 작동한다")
    @Test
    void test() {
        // Given
        Member member = MemberDataSet.testData("rolroralra");
        memberRepository.save(member);

        Long givenItemPrice = 10000L;
        Long givenStockQuantity = 10L;

        Item item = ItemDataSet.testData("Kotlin in Action", givenItemPrice, 10L);
        itemRepository.save(item);

        long givenOrderCount = 2L;

        assertThat(givenOrderCount).isBetween(1L, givenStockQuantity);

        Delivery delivery = Delivery.createDelivery(member.getAddress());

        OrderItem orderItem = OrderItem.createOrderItem(item, item.getPrice(), givenOrderCount);

        Order order = Order.createOrder(member, delivery, orderItem);

        deliveryRepository.save(delivery);

        orderItemRepository.save(orderItem);

        repository.save(order);

        OrderSearchDto orderSearchDto = OrderSearchDto.builder()
            .orderStatus(OrderStatus.ORDER)
            .memberName("ral")
            .build();

        // When
        List<Order> orders = customRepository.findAll(orderSearchDto);

        // Then
        assertThat(orders).hasSize(1);
        orders.forEach(o -> {
            assertThat(o.getMemberName()).isEqualToIgnoringCase("rolroralra");
            assertThat(o.getStatus()).isEqualTo(OrderStatus.ORDER);
        });
    }

    private static String getName(Order order) {
        return order.getMember().getName();
    }
}
