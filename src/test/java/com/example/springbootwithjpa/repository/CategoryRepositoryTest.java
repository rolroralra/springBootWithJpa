package com.example.springbootwithjpa.repository;

import static org.junit.jupiter.api.Assertions.*;

import com.example.springbootwithjpa.domain.Category;
import com.example.springbootwithjpa.repository.common.JpaRepositoryTest;
import com.example.springbootwithjpa.repository.data.CategoryDataSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

class CategoryRepositoryTest extends JpaRepositoryTest<Category, Long> {

    @Autowired
    private CategoryRepository repository;

    @Override
    protected JpaRepository<Category, Long> repository() {
        return repository;
    }

    @Override
    protected Category createTestInstance() {
        return CategoryDataSet.testData();
    }
}
