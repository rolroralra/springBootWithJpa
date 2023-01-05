package com.example.springbootwithjpa.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import lombok.Getter;

@Entity
@DiscriminatorValue(value = "MOVIE")
@Getter
public class Movie extends Item {

    private String director;

    private String actor;
}
