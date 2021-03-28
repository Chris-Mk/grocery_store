package com.mkolongo.grocery_store.service.implementation;

import com.mkolongo.grocery_store.domain.entities.Merchant;
import com.mkolongo.grocery_store.domain.entities.MerchantProduct;
import com.mkolongo.grocery_store.domain.entities.MerchantProductId;
import com.mkolongo.grocery_store.domain.entities.Product;
import com.mkolongo.grocery_store.domain.models.binding.MerchantBindingModel;
import com.mkolongo.grocery_store.domain.models.binding.ProductBindingModel;
import com.mkolongo.grocery_store.repository.MerchantProductRepository;
import com.mkolongo.grocery_store.service.abstraction.MerchantProductService;
import com.mkolongo.grocery_store.service.abstraction.MerchantService;
import com.mkolongo.grocery_store.service.abstraction.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class MerchantProductServiceImpl implements MerchantProductService {

    private final MerchantProductRepository merchantProductRepository;
    private final MerchantService merchantService;
    private final ProductService productService;

    @Override
    public void registerMerchantAndProducts(MerchantBindingModel merchantBindingModel,
                                            Set<ProductBindingModel> productBindingModels) {

        Merchant merchant = merchantService.saveMerchant(merchantBindingModel);
        List<Product> products = productService.saveProducts(productBindingModels);

        for (Product product : products) {
            MerchantProductId merchantProductId = new MerchantProductId(merchant.getId(), product.getId());

            ProductBindingModel bindingModel = productBindingModels.stream()
                    .filter(e -> e.getName().equals(product.getName()))
                    .findFirst()
                    .orElseThrow();

            merchantProductRepository.findById(merchantProductId)
                    .ifPresentOrElse(mp -> {
                        if (!mp.getPrice().equals(bindingModel.getPrice())) {
                            mp.setPrice(bindingModel.getPrice());
                        }

                        if (!mp.getUnits().equals(bindingModel.getUnits())) {
                            mp.setUnits(bindingModel.getUnits());
                        }

                        merchantProductRepository.save(mp);
                    },() -> {
                        MerchantProduct merchantProduct = new MerchantProduct();
                        merchantProduct.setMerchantProductId(merchantProductId);
                        merchantProduct.setPrice(bindingModel.getPrice());
                        merchantProduct.setUnits(bindingModel.getUnits());

                        merchantProductRepository.save(merchantProduct);
                    });
        }
    }
}
