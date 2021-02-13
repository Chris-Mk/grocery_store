package com.mkolongo.grocery_store.repository;

import com.mkolongo.grocery_store.domain.entities.Merchant;
import com.mkolongo.grocery_store.domain.entities.MerchantProduct;
import com.mkolongo.grocery_store.domain.entities.Product;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MerchantProductRepository extends BaseRepository<MerchantProduct> {

    Optional<MerchantProductRepository> findByMerchantAndProduct(Merchant merchant, Product product);

}
