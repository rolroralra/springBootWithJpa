package com.example.springbootwithjpa.repository;

import com.example.springbootwithjpa.domain.CategoryItem;
import com.example.springbootwithjpa.repository.common.JpaRepositoryTest;
import com.example.springbootwithjpa.repository.data.CategoryItemDataSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

class CategoryItemRepositoryTest extends JpaRepositoryTest<CategoryItem, Long> {

    @Autowired
    private CategoryItemRepository repository;

    @Override
    protected JpaRepository<CategoryItem, Long> repository() {
        return repository;
    }

    @Override
    protected CategoryItem createTestInstance() {
        return CategoryItemDataSet.testData();
    }
}
