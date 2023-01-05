package com.example.springbootwithjpa.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import lombok.Getter;

@Entity
@DiscriminatorValue(value = "ALBUM")
@Getter
public class Album extends Item {
    private String artist;

    private String etc;
}
