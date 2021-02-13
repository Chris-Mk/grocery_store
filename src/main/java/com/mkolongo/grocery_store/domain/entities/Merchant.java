package com.mkolongo.grocery_store.domain.entities;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.NaturalId;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "merchants")
public class Merchant extends BaseEntity {

    @NaturalId
    @Column(unique = true, nullable = false)
    private String name;

    @Column(name = "logo_url", unique = true, nullable = false)
    private String logoUrl;

    @OneToMany(mappedBy = "merchant")
    private Set<MerchantProduct> merchantsProducts;
}
