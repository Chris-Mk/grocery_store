package com.mkolongo.grocery_store.domain.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Getter
@Setter
@Embeddable
@EqualsAndHashCode
public class MerchantProductId implements Serializable {

    @Column(name = "merchant_id")
    private String merchantId;

    @Column(name = "product_id")
    private String productId;
}
