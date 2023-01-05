package com.example.springbootwithjpa.repository;

import com.example.springbootwithjpa.domain.CategoryItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryItemRepository extends JpaRepository<CategoryItem, Long> {

}
