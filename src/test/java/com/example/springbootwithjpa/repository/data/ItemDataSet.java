package com.example.springbootwithjpa.repository.data;

import com.example.springbootwithjpa.domain.Album;
import com.example.springbootwithjpa.domain.Book;
import com.example.springbootwithjpa.domain.Item;
import com.example.springbootwithjpa.domain.Movie;
import org.apache.commons.lang3.StringUtils;

public class ItemDataSet {
    public static Item testData() {
        return testData("Book");
    }

    public static Item testData(String type) {
        Item item;
        if (StringUtils.equalsIgnoreCase(type, Book.class.getSimpleName())) {
            item = new Book();
            item.setName("book01");

        } else if (StringUtils.equalsIgnoreCase(type, Album.class.getSimpleName())) {
            item = new Album();
            item.setName("album01");
        } else if (StringUtils.equalsIgnoreCase(type, Movie.class.getSimpleName())) {
            item = new Movie();
            item.setName("movie01");
        } else {
            throw new IllegalArgumentException("type should be one of Book, Album, Movie");
        }

        return item;
    }
}
