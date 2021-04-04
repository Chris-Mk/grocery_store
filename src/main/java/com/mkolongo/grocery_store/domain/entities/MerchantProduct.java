package com.mkolongo.grocery_store.domain.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "merchants_products")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class MerchantProduct {

    @EmbeddedId
    private MerchantProductId merchantProductId;

//    @ManyToOne
//    @MapsId("merchantId")
//    @EqualsAndHashCode.Include
//    private Merchant merchant;
//
//    @ManyToOne
//    @MapsId("productId")
//    @EqualsAndHashCode.Include
//    private Product product;

    @Column(nullable = false, precision = 6, scale = 2)
    private BigDecimal price;

    @Column(nullable = false)
    private String units;

    @Column(nullable = false)
    private boolean membership;
}
