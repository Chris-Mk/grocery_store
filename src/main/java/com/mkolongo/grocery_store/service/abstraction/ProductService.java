package com.mkolongo.grocery_store.service.abstraction;

import com.mkolongo.grocery_store.domain.entities.Product;
import com.mkolongo.grocery_store.domain.models.binding.ProductBindingModel;

import java.util.List;
import java.util.Set;

public interface ProductService {

    List<Product> saveProducts(Set<ProductBindingModel> productBindingModels);

}
