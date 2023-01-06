package com.example.springbootwithjpa.repository.data;

import com.example.springbootwithjpa.domain.Album;
import com.example.springbootwithjpa.domain.Book;
import com.example.springbootwithjpa.domain.Item;
import com.example.springbootwithjpa.domain.Movie;
import org.apache.commons.lang3.StringUtils;

public class ItemDataSet {
    public static Item testData() {
        return testData("book01");
    }

    public static Item testData(String name) {
        return testData(name, 0L, 0L);
    }

    public static Item testData(String name, Long price, Long stockQuantity) {
        return testData("Book", name, price, stockQuantity);
    }

    public static Item testData(String type, String name, Long price, Long stockQuantity) {
        Item item;
        if (StringUtils.equalsIgnoreCase(type, Book.class.getSimpleName())) {
            item = new Book();

        } else if (StringUtils.equalsIgnoreCase(type, Album.class.getSimpleName())) {
            item = new Album();
        } else if (StringUtils.equalsIgnoreCase(type, Movie.class.getSimpleName())) {
            item = new Movie();
        } else {
            throw new IllegalArgumentException("type should be one of Book, Album, Movie");
        }

        item.setName(name);
        item.setPrice(price);
        item.setStockQuantity(stockQuantity);

        return item;
    }
}
