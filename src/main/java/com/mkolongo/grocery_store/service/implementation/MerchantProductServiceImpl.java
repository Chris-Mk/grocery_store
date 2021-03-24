package com.mkolongo.grocery_store.service.implementation;

import com.mkolongo.grocery_store.domain.entities.Merchant;
import com.mkolongo.grocery_store.domain.entities.MerchantProduct;
import com.mkolongo.grocery_store.domain.entities.MerchantProductId;
import com.mkolongo.grocery_store.domain.entities.Product;
import com.mkolongo.grocery_store.domain.models.binding.MerchantBindingModel;
import com.mkolongo.grocery_store.domain.models.binding.ProductBindingModel;
import com.mkolongo.grocery_store.domain.models.service.ProductServiceModel;
import com.mkolongo.grocery_store.repository.MerchantProductRepository;
import com.mkolongo.grocery_store.service.abstraction.MerchantProductService;
import com.mkolongo.grocery_store.service.abstraction.MerchantService;
import com.mkolongo.grocery_store.service.abstraction.ProductService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class MerchantProductServiceImpl implements MerchantProductService {

    private final MerchantProductRepository merchantProductRepository;
    private final MerchantService merchantService;
    private final ProductService productService;
    private final ModelMapper mapper;

    @Override
    public void registerMerchantAndProducts(MerchantBindingModel merchantBindingModel,
                                            Set<ProductBindingModel> productBindingModels) {

        Merchant merchant = merchantService.saveMerchant(merchantBindingModel);

        Set<ProductServiceModel> productServiceModels = mapper.map(productBindingModels,
                new TypeToken<Set<ProductServiceModel>>(){}.getType());

        List<Product> products = productService.saveProducts(productServiceModels);

        for (ProductServiceModel productServiceModel : productServiceModels) {

            Product product = products.stream()
                    .filter(p -> p.getName().equals(productServiceModel.getName()))
                    .findFirst()
                    .orElseThrow();

            MerchantProductId merchantProductId = new MerchantProductId(merchant.getId(), product.getId());
            MerchantProduct merchantProduct = new MerchantProduct();

            merchantProduct.setMerchantProductId(merchantProductId);
            merchantProduct.setPrice(productServiceModel.getPrice());
            merchantProduct.setUnits(productServiceModel.getUnits());

            merchantProductRepository.save(merchantProduct);
        }
    }
}
