package com.mkolongo.grocery_store.repository;

import com.mkolongo.grocery_store.domain.entities.MerchantProduct;
import com.mkolongo.grocery_store.domain.entities.MerchantProductId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MerchantProductRepository extends JpaRepository<MerchantProduct, MerchantProductId> {

    @Query(value = "select * from grocery_store.merchants_products where product_id = ?",
            nativeQuery = true)
    MerchantProduct findByProductId(String productId);

//    Optional<MerchantProductRepository> findByMerchantAndProduct(Merchant merchant, Product product);

}
