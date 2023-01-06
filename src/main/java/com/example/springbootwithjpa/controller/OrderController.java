package com.example.springbootwithjpa.controller;

import com.example.springbootwithjpa.controller.dto.OrderCreateDto;
import com.example.springbootwithjpa.controller.dto.OrderSearchDto;
import com.example.springbootwithjpa.domain.Item;
import com.example.springbootwithjpa.domain.Member;
import com.example.springbootwithjpa.domain.Order;
import com.example.springbootwithjpa.service.ItemService;
import com.example.springbootwithjpa.service.MemberService;
import com.example.springbootwithjpa.service.OrderService;
import java.util.List;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;

    private final MemberService memberService;

    private final ItemService itemService;

    @GetMapping(value = "/new")
    public String createForm(Model model) {
        List<Member> members = memberService.findAll(PageRequest.of(0, 10)).getContent();
        List<Item> items = itemService.findAll(PageRequest.of(0, 10)).getContent();

        model.addAttribute("members", members);
        model.addAttribute("items", items);

        return "orders/createOrderForm";
    }

    @PostMapping(value = "/new")
    public String createOrder(@Valid OrderCreateDto orderCreateDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "orders/createOrderForm";
        }

        orderService.takeOrder(orderCreateDto.getMemberId(), orderCreateDto.getItemId(), orderCreateDto.getCount());
        return "redirect:/orders";
    }

    @GetMapping
    public String orderList(OrderSearchDto orderSearchDto, Model model) {
        List<Order> orders = orderService.findOrders(orderSearchDto);

        model.addAttribute("orders", orders);

        return "orders/orderList";
    }

    @PostMapping(value = "/{orderId}/cancel")
    public String cancelOrder(@PathVariable Long orderId) {
        orderService.cancelOrder(orderId);

        return "redirect:/orders";
    }
}
