package com.example.springbootwithjpa.domain;

import static com.google.common.base.Preconditions.checkArgument;

import com.example.springbootwithjpa.exception.NotEnoughStockException;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "ITEM")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "TYPE")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public abstract class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Long price;

    private Long stockQuantity;

    @OneToMany(mappedBy = "item")
    @JsonIgnore
    private List<CategoryItem> categoryItems = new ArrayList<>();

    public void addStock(long quantity) {
        checkArgument(quantity > 0, "quantity should be greater than 0");

        stockQuantity += quantity;
    }

    public void removeStock(long quantity) {
        checkArgument(quantity > 0, "quantity should be greater than 0");
        if (quantity > stockQuantity) {
            throw new NotEnoughStockException(String.format("quantity should be less or than %d", stockQuantity));
        }

        stockQuantity -= quantity;
    }
}
