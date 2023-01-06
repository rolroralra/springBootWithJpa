package com.example.springbootwithjpa.controller;

import com.example.springbootwithjpa.controller.param.BookForm;
import com.example.springbootwithjpa.domain.Book;
import com.example.springbootwithjpa.domain.Item;
import com.example.springbootwithjpa.exception.NotFoundMemberException;
import com.example.springbootwithjpa.service.ItemService;
import com.google.common.base.Preconditions;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/items")
public class ItemController {
    private final ItemService itemService;

    @GetMapping
    public String bookList(Pageable pageable, Model model) {
        List<Item> items = itemService.findAll(pageable).getContent();

        model.addAttribute("items", items);

        return "items/itemList";
    }

    @GetMapping(value = "/new")
    public String createForm(Model model) {
        model.addAttribute("itemForm", new BookForm());
        return "items/createItemForm";
    }

    @PostMapping(value = "/new")
    public String createItem(BookForm itemFrom) {
        Book book = itemFrom.createBook();

        itemService.saveItem(book);

        return "redirect:/items";
    }

    @GetMapping(value = "/{itemId}/edit")
    public String updateItemFrom(@PathVariable Long itemId, Model model) {
        Book item = (Book) itemService.findById(itemId)
            .orElseThrow(NotFoundMemberException::new);

        BookForm itemForm = BookForm.of(item);

        model.addAttribute("itemForm", itemForm);

        return "items/updateItemForm";
    }

    @PostMapping(value = "/{itemId}/edit")
    public String updateItem(BookForm itemForm) {
        Preconditions.checkArgument(itemForm != null);
        Preconditions.checkArgument(itemForm.getId() != null);

        itemService.updateItem(itemForm.getId(), itemForm.getName(), itemForm.getPrice());


        return "redirect:/items";
    }
}
