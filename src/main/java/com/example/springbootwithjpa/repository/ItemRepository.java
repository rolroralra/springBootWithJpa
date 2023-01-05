package com.example.springbootwithjpa.repository;

import com.example.springbootwithjpa.domain.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {

}
