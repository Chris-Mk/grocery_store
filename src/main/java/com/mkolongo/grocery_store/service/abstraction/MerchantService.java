package com.mkolongo.grocery_store.service.abstraction;

import com.mkolongo.grocery_store.domain.entities.Merchant;
import com.mkolongo.grocery_store.domain.models.binding.MerchantBindingModel;

public interface MerchantService {

    Merchant saveMerchant(MerchantBindingModel merchantBindingModel);

    boolean doesMerchantExist(String name);

    Merchant getMerchantByName(String name);
}
