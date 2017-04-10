package com.eretailservice.products.repositories;

import org.springframework.data.repository.CrudRepository;

import com.eretailservice.products.domain.Product;

public interface ProductRepository extends CrudRepository<Product, Integer>{
}
