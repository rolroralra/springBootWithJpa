package com.example.springbootwithjpa.repository.data;

import com.example.springbootwithjpa.domain.Category;

public class CategoryDataSet {
    public static Category testData() {
        Category category = new Category();
        category.setName("category01");

        return category;
    }
}
