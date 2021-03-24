package com.mkolongo.grocery_store.repository;

import com.mkolongo.grocery_store.domain.entities.Merchant;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MerchantRepository extends BaseRepository<Merchant> {

    Optional<Merchant> findByName(String name);

    boolean existsByName(String name);
}
