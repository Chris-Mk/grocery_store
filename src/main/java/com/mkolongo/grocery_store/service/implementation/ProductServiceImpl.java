package com.mkolongo.grocery_store.service.implementation;

import com.mkolongo.grocery_store.domain.entities.Product;
import com.mkolongo.grocery_store.domain.models.service.ProductServiceModel;
import com.mkolongo.grocery_store.repository.ProductRepository;
import com.mkolongo.grocery_store.service.abstraction.ProductService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ModelMapper mapper;

    @Override
    public List<Product> saveProducts(Set<ProductServiceModel> productServiceModels) {
        Set<Product> products = mapper.map(productServiceModels, new TypeToken<Set<Product>>(){}.getType());
        return productRepository.saveAll(products);
    }

}
