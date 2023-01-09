package com.example.springbootwithjpa.repository.impl;

import com.example.springbootwithjpa.api.dto.OrderDto;
import com.example.springbootwithjpa.api.dto.OrderItemDto;
import com.example.springbootwithjpa.api.dto.SimpleOrderDto;
import com.example.springbootwithjpa.controller.dto.OrderSearchDto;
import com.example.springbootwithjpa.domain.Order;
import com.example.springbootwithjpa.repository.custom.CustomOrderRepository;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

@Repository("orderRepositoryImpl")
public class OrderRepositoryImpl implements CustomOrderRepository {

    private final EntityManager em;

    public OrderRepositoryImpl(EntityManager em) {
        this.em = em;
    }

    public List<Order> findAll(OrderSearchDto orderSearchDto) {
        StringBuilder queryBuilder = new StringBuilder("select o from Order o join o.member m");

        boolean isFirstCondition = true;

        if (orderSearchDto.getOrderStatus() != null) {
            if (isFirstCondition) {
                queryBuilder.append(" where");
                isFirstCondition = false;
            } else {
                queryBuilder.append(" and");
            }

            queryBuilder.append(" o.status = :status");
        }

        if (orderSearchDto.getMemberName() != null) {
            if (isFirstCondition) {
                queryBuilder.append(" where");
                isFirstCondition = false;
            } else {
                queryBuilder.append(" and");
            }

            queryBuilder.append(" m.name like concat('%', concat(:memberName, '%'))");
        }

        TypedQuery<Order> query = em.createQuery(queryBuilder.toString(), Order.class)
            .setMaxResults(1000);

        if (orderSearchDto.getOrderStatus() != null) {
            query.setParameter("status", orderSearchDto.getOrderStatus());
        }

        if (orderSearchDto.getMemberName() != null) {
            query.setParameter("memberName", orderSearchDto.getMemberName());
        }

        return query.getResultList();
    }

    @Override
    public List<SimpleOrderDto> findAllSimpleOrderDtos() {
        return em.createQuery("select new com.example.springbootwithjpa.api.dto.SimpleOrderDto("
            + "o.id, m.name, o.orderDate, o.status, d.address)" +
            " from Order o left join o.member m left join o.delivery d", SimpleOrderDto.class)
            .getResultList();
    }

    @Override
    public List<OrderDto> findAllOrderDtos() {
        List<OrderDto> resultList = findAllOrderDtosWithoutOrderItems();

        lazyLoadOrderItemDto(resultList);

        return resultList;
    }

    @Override
    public List<OrderDto> findAllOrderDtosByOptimization() {
        List<OrderDto> result = findAllOrderDtosWithoutOrderItems();

        List<Long> orderIds = result.stream().map(OrderDto::getOrderId).collect(Collectors.toList());

        Map<Long, List<OrderItemDto>> orderItemDtoMap = findOrderItemDtoMap(orderIds);

        result.forEach(orderDto ->
            orderDto.setOrderItems(
                orderItemDtoMap.get(orderDto.getOrderId())
            )
        );

        return result;
    }

    private List<OrderDto> findAllOrderDtosWithoutOrderItems() {
        return em.createQuery(
                "select new com.example.springbootwithjpa.api.dto.OrderDto("
                    + "o.id, m.name, o.orderDate, o.status, d.address)" +
                    " from Order o left join o.member m left join o.delivery d", OrderDto.class)
            .getResultList();
    }

    private void lazyLoadOrderItemDto(List<OrderDto> orders) {
        orders.forEach(orderDto -> {
            List<OrderItemDto> orderItems = findAllOrderItemDtos(orderDto.getOrderId());
            orderDto.setOrderItems(orderItems);
        });
    }

    private List<OrderItemDto> findAllOrderItemDtos(Long orderId) {
        return em.createQuery("select new com.example.springbootwithjpa.api.dto.OrderItemDto(oi.order.id, i.name, oi.orderPrice, oi.count)" +
            " from OrderItem oi left join oi.item i where oi.order.id = :orderId", OrderItemDto.class)
            .setParameter("orderId", orderId)
            .getResultList();
    }

    private Map<Long, List<OrderItemDto>> findOrderItemDtoMap(List<Long> orderIds) {
        List<OrderItemDto> orderItems = em.createQuery(
                "select new com.example.springbootwithjpa.api.dto.OrderItemDto(oi.order.id, i.name, oi.orderPrice, oi.count)"
                    +
                    " from OrderItem oi left join oi.item i where oi.order.id in :orderIds",
                OrderItemDto.class)
            .setParameter("orderIds", orderIds)
            .getResultList();

        return orderItems.stream()
            .collect(Collectors.groupingBy(OrderItemDto::getOrderId));
    }
}
