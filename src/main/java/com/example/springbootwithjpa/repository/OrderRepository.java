package com.example.springbootwithjpa.repository;

import com.example.springbootwithjpa.domain.Order;
import com.example.springbootwithjpa.repository.custom.CustomOrderRepository;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository("orderRepository")
public interface OrderRepository extends JpaRepository<Order, Long>, CustomOrderRepository {
    @Query(
        value = "select o from Order o left join fetch o.member left join fetch o.delivery",
        countQuery = "select count(o) from Order o"
    )
    Page<Order> findAllWithMemberDelivery(Pageable pageable);

    @Query(
        value = "select distinct o from Order o left join fetch o.member left join fetch o.delivery"
            + " left join fetch o.orderItems oi left join fetch oi.item i",
        countQuery = "select count(o) from Order o"
    )
    Page<Order> findAllWithItemByPaging(Pageable pageable);


    @Query(
        value = "select distinct o from Order o left join fetch o.member left join fetch o.delivery"
            + " left join fetch o.orderItems oi left join fetch oi.item i"
    )
    List<Order> findAllWithItem();
}
