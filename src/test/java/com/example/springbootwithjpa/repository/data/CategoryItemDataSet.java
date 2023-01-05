package com.example.springbootwithjpa.repository.data;

import com.example.springbootwithjpa.domain.CategoryItem;

public class CategoryItemDataSet {
    public static CategoryItem testData() {
        CategoryItem categoryItem = new CategoryItem();
        categoryItem.setCategory(CategoryDataSet.testData());
        categoryItem.setItem(ItemDataSet.testData());

        return categoryItem;
    }
}
