package com.mkolongo.grocery_store.repository;

import com.mkolongo.grocery_store.domain.entities.MerchantProduct;
import com.mkolongo.grocery_store.domain.entities.MerchantProductId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MerchantProductRepository extends JpaRepository<MerchantProduct, MerchantProductId> {

//    Optional<MerchantProductRepository> findByMerchantAndProduct(Merchant merchant, Product product);

}
