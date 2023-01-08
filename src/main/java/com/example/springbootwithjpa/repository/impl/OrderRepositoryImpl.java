package com.example.springbootwithjpa.repository.impl;

import com.example.springbootwithjpa.api.dto.SimpleOrderDto;
import com.example.springbootwithjpa.controller.dto.OrderSearchDto;
import com.example.springbootwithjpa.domain.Order;
import com.example.springbootwithjpa.repository.custom.CustomOrderRepository;
import java.util.List;
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
    public List<SimpleOrderDto> findAllOrderDtos() {
        return em.createQuery("select new com.example.springbootwithjpa.api.dto.SimpleOrderDto("
            + "o.id, m.name, o.orderDate, o.status, d.address)" +
            " from Order o left join o.member m left join o.delivery d", SimpleOrderDto.class).getResultList();
    }
}
