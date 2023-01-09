package com.example.springbootwithjpa.api;

import com.example.springbootwithjpa.api.dto.OrderDto;
import com.example.springbootwithjpa.api.dto.SimpleOrderDto;
import com.example.springbootwithjpa.controller.dto.OrderSearchDto;
import com.example.springbootwithjpa.domain.Order;
import com.example.springbootwithjpa.repository.OrderRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Description;
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
    @Description("엔티티 직접 노출")
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
    @Description("엔티티를 DTO로 변환")
    public Page<SimpleOrderDto> simpleOrdersV2(Pageable pageable) {
        return orderRepository.findAll(pageable).map(SimpleOrderDto::of);
    }

    @GetMapping("/api/v3/simple-orders")
    @Description("엔티티를 DTO로 변환 - 페치 조인 최적화")
    public Page<SimpleOrderDto> simpleOrdersV3(Pageable pageable) {
        return orderRepository.findAllWithMemberDelivery(pageable).map(SimpleOrderDto::of);
    }

    @GetMapping("/api/v4/simple-orders")
    @Description("JPA에서 DTO 직접 조회")
    public List<SimpleOrderDto> simpleOrdersV4() {
        return orderRepository.findAllSimpleOrderDtos();
    }

    @Deprecated(since = "Entity is exposed")
    @GetMapping("/api/v1/orders")
    @Description("엔티티 직접 노출 - Entity에 @JsonIgnore -> 무한루프 방지, Hibernate5Module -> JSON Serialization 시 LazyLoad 자동 실행")
    public List<Order> ordersV1() {
        return orderRepository.findAll();
    }

    @GetMapping("/api/v2/orders")
    @Description("엔티티를 DTO로 변환 - 1+N Query 발생")
    public List<OrderDto> ordersV2() {
        return orderRepository.findAll().stream().map(OrderDto::of).collect(Collectors.toList());
    }

    @Deprecated(since = "firstResult/maxResults specified with collection fetch; applying in memory!")
    @GetMapping("/api/v3/orders")
    @Description("엔티티를 DTO로 변환 - 페치 조인 최적화 - 컬렉션을 페치조인하면 페이징이 불가능하다")
    public Page<OrderDto> ordersV3(Pageable pageable) {
        // o.h.h.internal.ast.QueryTranslatorImpl
        // HHH000104: firstResult/maxResults specified with collection fetch; applying in memory!
          return orderRepository.findAllWithItemByPaging(pageable).map(OrderDto::of);
    }

    @GetMapping("/api/v3.0/orders")
    @Description("엔티티를 DTO로 변환 - 페치 조인 최적화 - 페이징 미사용")
    public List<OrderDto> ordersV3_0() {
        return orderRepository.findAllWithItem().stream().map(OrderDto::of).collect(Collectors.toList());
    }

    @GetMapping("/api/v3.1/orders")
    @Description("엔티티를 DTO로 변환 - 컬렉션 페치 조인 하지 않고, BatchSize 와 LazyLoading을 통해 한계점 해결")
    public Page<OrderDto> ordersV3_1(Pageable pageable) {
        // @BatchSize 활용
        // spring.jpa.properties.hibernate.default_batch_fetch_size
        return orderRepository.findAllWithMemberDelivery(pageable).map(OrderDto::of);
    }

    @GetMapping("/api/v4/orders")
    @Description("JPA에서 DTO 직접 조회")
    public List<OrderDto> ordersV4() {
        return orderRepository.findAllOrderDtos();
    }

    @GetMapping("/api/v5/orders")
    @Description("JPA에서 DTO 직접 조회")
    public List<OrderDto> ordersV5() {
        return orderRepository.findAllOrderDtosByOptimization();
    }
}
