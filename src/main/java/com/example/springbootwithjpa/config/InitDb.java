package com.example.springbootwithjpa.config;

import com.example.springbootwithjpa.domain.Book;
import com.example.springbootwithjpa.domain.Delivery;
import com.example.springbootwithjpa.domain.Member;
import com.example.springbootwithjpa.domain.Order;
import com.example.springbootwithjpa.domain.OrderItem;
import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

//@Component
@RequiredArgsConstructor
public class InitDb {
    private final InitService initService;

    @PostConstruct
    public void init() {
        initService.dbInit1();
        initService.dbInit2();
    }

//    @Component
    @RequiredArgsConstructor
    @Transactional
    public static class InitService {
        private final EntityManager em;

        public void dbInit1() {
            Member member = Member.createMember("userA", "서울", "송파", "1001");
            em.persist(member);

            Book book1 = Book.createBook("TDD in Action", 15000L, 50L, "JUnit5", "2005");
            Book book2 = Book.createBook("JPA", 30000L, 100L, "Hibernate", "2006");
            em.persist(book1);
            em.persist(book2);

            OrderItem orderItem1 = OrderItem.createOrderItem(book1, book1.getPrice(), 1L);
            OrderItem orderItem2 = OrderItem.createOrderItem(book2, book2.getPrice(), 2L);
            em.persist(orderItem1);
            em.persist(orderItem2);

            Delivery delivery = Delivery.createDelivery(member.getAddress());
            em.persist(delivery);

            Order order = Order.createOrder(member, delivery, orderItem1, orderItem2);
            em.persist(order);
        }

        public void dbInit2() {
            Member member = Member.createMember("userB", "서울", "강남", "1002");
            em.persist(member);

            Book book1 = Book.createBook("MSA in action", 22000L, 25L, "Cloud", "2007");
            Book book2 = Book.createBook("Devops in action", 18000L, 30L, "Devops", "2008");

            em.persist(book1);
            em.persist(book2);

            OrderItem orderItem1 = OrderItem.createOrderItem(book1, book1.getPrice(), 3L);
            OrderItem orderItem2 = OrderItem.createOrderItem(book2, book2.getPrice(), 4L);
            em.persist(orderItem1);
            em.persist(orderItem2);

            Delivery delivery = Delivery.createDelivery(member.getAddress());
            em.persist(delivery);

            Order order = Order.createOrder(member, delivery, orderItem1, orderItem2);
            em.persist(order);
        }

    }
}
