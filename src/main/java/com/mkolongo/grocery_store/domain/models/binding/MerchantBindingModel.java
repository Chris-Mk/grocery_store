package com.mkolongo.grocery_store.domain.models.binding;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class MerchantBindingModel {

    private String name;
    private String logoUrl;

    public MerchantBindingModel(String name, String logoUrl) {
        this.name = name;
        this.logoUrl = logoUrl;
    }
}
