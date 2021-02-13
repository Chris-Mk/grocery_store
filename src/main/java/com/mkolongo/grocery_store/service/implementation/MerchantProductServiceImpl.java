package com.mkolongo.grocery_store.service.implementation;

import com.mkolongo.grocery_store.domain.entities.Merchant;
import com.mkolongo.grocery_store.domain.entities.MerchantProduct;
import com.mkolongo.grocery_store.domain.entities.Product;
import com.mkolongo.grocery_store.domain.models.binding.MerchantBindingModel;
import com.mkolongo.grocery_store.domain.models.binding.ProductBindingModel;
import com.mkolongo.grocery_store.domain.models.service.ProductServiceModel;
import com.mkolongo.grocery_store.repository.MerchantProductRepository;
import com.mkolongo.grocery_store.repository.MerchantRepository;
import com.mkolongo.grocery_store.repository.ProductRepository;
import com.mkolongo.grocery_store.service.abstraction.MerchantProductService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class MerchantProductServiceImpl implements MerchantProductService {

    private final MerchantProductRepository merchantProductRepository;
    private final MerchantRepository merchantRepository;
    private final ProductRepository productRepository;
    private final ModelMapper mapper;

    @Override
    public void registerMerchantAndProducts(MerchantBindingModel merchantBindingModel,
                                            Set<ProductBindingModel> productBindingModels) {

        Set<ProductServiceModel> productServiceModels = mapper.map(productBindingModels,
                new TypeToken<Set<ProductServiceModel>>(){}.getType());

        Merchant merchant = mapper.map(merchantBindingModel, Merchant.class);

        productServiceModels.forEach(p -> {
            Product product = mapper.map(p, Product.class);
            MerchantProduct merchantProduct = new MerchantProduct();

            merchantProduct.setProduct(product);
            merchantProduct.setMerchant(merchant);
            merchantProduct.setPrice(p.getPrice());
            merchantProduct.setUnits(p.getUnits());

            productRepository.save(product);
            merchantProductRepository.save(merchantProduct);
        });

        merchantRepository.save(merchant);
    }
}
