package com.example.springbootwithjpa.repository.impl;

import com.example.springbootwithjpa.controller.dto.OrderSearchDto;
import com.example.springbootwithjpa.domain.Order;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Repository;

@Repository("orderRepositoryImpl")
public class OrderRepositoryImpl extends SimpleJpaRepository<Order, Long> {

    private final EntityManager em;

    public OrderRepositoryImpl(EntityManager em) {
        super(Order.class, em);
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
}
