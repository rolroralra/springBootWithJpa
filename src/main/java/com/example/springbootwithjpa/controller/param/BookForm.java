package com.example.springbootwithjpa.controller.param;

import com.example.springbootwithjpa.domain.Book;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookForm {
    private Long id;

    private String name;

    private Long price;

    private Long stockQuantity;

    private String author;

    private String isbn;

    public Book createBook() {
        Book book = new Book();

        BeanUtils.copyProperties(this, book);

        return book;
    }

    public static BookForm of(Book book) {
        BookForm bookForm = new BookForm();
        BeanUtils.copyProperties(book, bookForm);

        return bookForm;
    }
}
