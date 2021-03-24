package com.mkolongo.grocery_store.domain.entities;

import lombok.*;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Getter
@Setter
@Embeddable
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class MerchantProductId implements Serializable {

    private String merchantId;

    private String productId;
}
