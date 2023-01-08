package com.example.springbootwithjpa.api;

import com.example.springbootwithjpa.api.dto.OrderDto;
import com.example.springbootwithjpa.api.dto.SimpleOrderDto;
import com.example.springbootwithjpa.controller.dto.OrderSearchDto;
import com.example.springbootwithjpa.domain.Order;
import com.example.springbootwithjpa.repository.OrderRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OrderSimpleApiController {
    private final OrderRepository orderRepository;

    /**
     * @see com.example.springbootwithjpa.config.JpaConfig

    });
     */
    @Deprecated(since = "Entity is exposed")
    @GetMapping("/api/v1/simple-orders")
    public List<Order> simpleOrdersV1() {
        // Jackson의 Hibernate5Module 모듈 설정에 의해 LazyLoading 문제 해결하면서 JSON으로 Serialize 한다.
        // Entity를 이용해서 리턴하므로, 양방향 연관관계일 경우 @JsonIgnore를 추가해야 한다.

//        all.forEach(order -> {
//            order.getMember().getName();        // LazyLoad
//            order.getDelivery().getAddress();   // LazyLoad
//            order.getOrderItems().forEach(orderItem ->
//            orderItem.getItem().getName()   // LazyLoad
//        );
        return orderRepository.findAll(new OrderSearchDto());
    }

    @GetMapping("/api/v2/simple-orders")
    public Page<SimpleOrderDto> simpleOrdersV2(Pageable pageable) {
        return orderRepository.findAll(pageable).map(SimpleOrderDto::of);
    }

    @GetMapping("/api/v3/simple-orders")
    public Page<SimpleOrderDto> simpleOrdersV3(Pageable pageable) {
        return orderRepository.findAllWithMemberDelivery(pageable).map(SimpleOrderDto::of);
    }

    @GetMapping("/api/v4/simple-orders")
    public List<SimpleOrderDto> simpleOrdersV4() {
        return orderRepository.findAllOrderDtos();
    }

    @Deprecated(since = "Entity is exposed")
    @GetMapping("/api/v1/orders")
    public List<Order> ordersV1() {
        return orderRepository.findAll();
    }

    @GetMapping("/api/v2/orders")
    public List<OrderDto> ordersV2() {
        return orderRepository.findAll().stream().map(OrderDto::of).collect(Collectors.toList());
    }

    @Deprecated(since = "firstResult/maxResults specified with collection fetch; applying in memory!")
    @GetMapping("/api/v3/orders")
    public Page<OrderDto> ordersV3(Pageable pageable) {
        // o.h.h.internal.ast.QueryTranslatorImpl
        // HHH000104: firstResult/maxResults specified with collection fetch; applying in memory!
          return orderRepository.findAllWithItemByPaging(pageable).map(OrderDto::of);
    }

    @GetMapping("/api/v3.0/orders")
    public List<OrderDto> ordersV3_0() {
        return orderRepository.findAllWithItem().stream().map(OrderDto::of).collect(Collectors.toList());
    }

    @GetMapping("/api/v3.1/orders")
    public Page<OrderDto> ordersV3_1(Pageable pageable) {
        // @BatchSize 활용
        // spring.jpa.properties.hibernate.default_batch_fetch_size
        return orderRepository.findAllWithMemberDelivery(pageable).map(OrderDto::of);
    }
}
