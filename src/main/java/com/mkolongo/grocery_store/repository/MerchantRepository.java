package com.mkolongo.grocery_store.repository;

import com.mkolongo.grocery_store.domain.entities.Merchant;
import org.springframework.stereotype.Repository;

@Repository
public interface MerchantRepository extends BaseRepository<Merchant> {

    Merchant findByName(String name);
}
