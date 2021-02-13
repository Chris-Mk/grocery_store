package com.mkolongo.grocery_store.repository;

import com.mkolongo.grocery_store.domain.entities.Product;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends BaseRepository<Product> {

    Optional<Product> findByName(String name);
}
