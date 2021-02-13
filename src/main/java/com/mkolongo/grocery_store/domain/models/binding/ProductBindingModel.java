package com.mkolongo.grocery_store.domain.models.binding;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ProductBindingModel {

    private BigDecimal price;
    private String units;
    private String name;
    private String description;
    private String category;
    private String imageUrl;

}
