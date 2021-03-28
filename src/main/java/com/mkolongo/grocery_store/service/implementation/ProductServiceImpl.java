package com.mkolongo.grocery_store.service.implementation;

import com.mkolongo.grocery_store.domain.entities.Product;
import com.mkolongo.grocery_store.domain.models.binding.ProductBindingModel;
import com.mkolongo.grocery_store.repository.ProductRepository;
import com.mkolongo.grocery_store.service.abstraction.ProductService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ModelMapper mapper;

    @Override
    public List<Product> saveProducts(Set<ProductBindingModel> productBindingModels) {
        List<Product> products = mapper
                .map(productBindingModels, new TypeToken<List<Product>>() {}.getType());

        List<Product> savedProducts = new ArrayList<>();

        for (Product product : products) {
            Optional<Product> optionalProduct = productRepository.findByName(product.getName());

            if (optionalProduct.isPresent()) {
                savedProducts.add(optionalProduct.get());
            } else {
                savedProducts.add(productRepository.save(product));
            }
        }

        return savedProducts;
    }

}
