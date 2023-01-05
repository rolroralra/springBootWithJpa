package com.example.springbootwithjpa.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import lombok.Getter;

@Entity
@DiscriminatorValue(value = "BOOK")
@Getter
public class Book extends Item {
    private String author;

    private String isbn;
}
