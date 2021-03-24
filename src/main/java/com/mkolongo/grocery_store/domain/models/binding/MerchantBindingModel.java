package com.mkolongo.grocery_store.domain.models.binding;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MerchantBindingModel {

    private final String name;
    private final String logoUrl;

}
