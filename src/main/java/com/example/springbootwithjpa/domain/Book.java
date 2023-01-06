package com.example.springbootwithjpa.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@DiscriminatorValue(value = "BOOK")
@Getter
@Setter
public class Book extends Item {
    private String author;

    private String isbn;
}
