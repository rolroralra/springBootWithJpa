package com.example.springbootwithjpa.service;

import com.example.springbootwithjpa.domain.Book;
import com.example.springbootwithjpa.domain.Item;
import com.example.springbootwithjpa.exception.NotFoundItemException;
import com.example.springbootwithjpa.repository.ItemRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {
    private final ItemRepository itemRepository;

    @Transactional
    public Long saveItem(Item item) {
        itemRepository.save(item);

        return item.getId();
    }

    public Page<Item> findAll(Pageable pageable) {
        return itemRepository.findAll(pageable);
    }

    public Optional<Item> findById(Long itemId) {
        return itemRepository.findById(itemId);
    }

    @Transactional
    public void updateItem(Long id, String name, Long price) {
        Book book = (Book) itemRepository.findById(id).orElseThrow(NotFoundItemException::new);

        book.setName(name);
        book.setPrice(price);
    }
}
