package com.mkolongo.grocery_store.service.implementation;

import com.mkolongo.grocery_store.domain.entities.Merchant;
import com.mkolongo.grocery_store.domain.models.binding.MerchantBindingModel;
import com.mkolongo.grocery_store.repository.MerchantRepository;
import com.mkolongo.grocery_store.service.abstraction.MerchantService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MerchantServiceImpl implements MerchantService {

    private final MerchantRepository merchantRepository;
    private final ModelMapper mapper;

    @Override
    public Merchant saveMerchant(MerchantBindingModel merchantBindingModel) {
        String merchantName = merchantBindingModel.getName();

        if (doesMerchantExist(merchantName)) {
            return getMerchantByName(merchantName);
        }

        return merchantRepository.save(mapper.map(merchantBindingModel, Merchant.class));
    }

    @Override
    public boolean doesMerchantExist(String name) {
        return merchantRepository.existsByName(name);
    }

    @Override
    public Merchant getMerchantByName(String name) {
        return merchantRepository.findByName(name).orElseThrow();
    }

}
