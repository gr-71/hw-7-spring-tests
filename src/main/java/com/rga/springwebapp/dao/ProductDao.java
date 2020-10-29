package com.rga.springwebapp.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.rga.springwebapp.domain.Product;

public interface ProductDao extends JpaRepository<Product, Long> {
}
