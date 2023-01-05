package com.example.springbootwithjpa.repository;

import com.example.springbootwithjpa.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}
