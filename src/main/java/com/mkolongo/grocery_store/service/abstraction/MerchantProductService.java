package com.mkolongo.grocery_store.service.abstraction;

import com.mkolongo.grocery_store.domain.models.binding.MerchantBindingModel;
import com.mkolongo.grocery_store.domain.models.binding.ProductBindingModel;

import java.util.Set;

public interface MerchantProductService {

    void registerMerchantAndProducts(MerchantBindingModel merchantBindingModel,
                                     Set<ProductBindingModel> productBindingModels);
}
