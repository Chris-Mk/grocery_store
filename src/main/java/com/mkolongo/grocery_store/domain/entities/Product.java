package com.mkolongo.grocery_store.domain.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.NaturalId;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "products")
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public class Product extends BaseEntity {

    @NaturalId
    @Column(nullable = false, unique = true)
    @EqualsAndHashCode.Include
    private String name;

    @Column(name = "image_url", nullable = false)
    private String imageUrl;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String category;

//    @OneToMany(mappedBy = "product")
//    private Set<MerchantProduct> merchantsProducts;
}
