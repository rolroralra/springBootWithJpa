package com.example.springbootwithjpa.service;

import com.example.springbootwithjpa.controller.dto.OrderSearchDto;
import com.example.springbootwithjpa.domain.Delivery;
import com.example.springbootwithjpa.domain.Item;
import com.example.springbootwithjpa.domain.Member;
import com.example.springbootwithjpa.domain.Order;
import com.example.springbootwithjpa.domain.OrderItem;
import com.example.springbootwithjpa.exception.NotFoundItemException;
import com.example.springbootwithjpa.exception.NotFoundMemberException;
import com.example.springbootwithjpa.exception.NotFoundOrderException;
import com.example.springbootwithjpa.repository.DeliveryRepository;
import com.example.springbootwithjpa.repository.ItemRepository;
import com.example.springbootwithjpa.repository.MemberRepository;
import com.example.springbootwithjpa.repository.OrderItemRepository;
import com.example.springbootwithjpa.repository.OrderRepository;
import com.google.common.base.Preconditions;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository;
    private final ItemRepository itemRepository;

    private final DeliveryRepository deliveryRepository;
    private final OrderItemRepository orderItemRepository;

//    private final OrderRepositoryImpl orderRepositoryImpl;

    @Transactional
    public Long takeOrder(Long memberId, Long itemId, long count) {
        Member member = memberRepository.findById(memberId)
            .orElseThrow(NotFoundMemberException::new);

        Item item = itemRepository.findById(itemId).orElseThrow(NotFoundItemException::new);

        Delivery delivery = Delivery.createDelivery(member.getAddress());

        OrderItem orderItem = OrderItem.createOrderItem(item, item.getPrice(), count);

        Order order = Order.createOrder(member, delivery, orderItem);

        deliveryRepository.save(delivery);

        orderItemRepository.save(orderItem);

        orderRepository.save(order);

        return order.getId();
    }

    @Transactional
    public void cancelOrder(Long orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow(NotFoundOrderException::new);

        order.cancel();
    }

    public Page<Order> findAll(Pageable pageable) {
        return orderRepository.findAll(pageable);
    }

    public List<Order> findOrders(OrderSearchDto orderSearchDto) {
        Preconditions.checkArgument(orderSearchDto != null);

        return orderRepository.findAll(orderSearchDto);
    }
}
