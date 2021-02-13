package com.mkolongo.grocery_store.repository;

import com.mkolongo.grocery_store.domain.entities.Merchant;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
class MerchantRepositoryTest {

    @Autowired
    MerchantRepository merchantRepository;

    @Test
    void findByName_shouldBeValid() {
        merchantRepository.save(new Merchant(){{
            setId("dsa");
            setName("lidl");
            setLogoUrl("asd");
        }});

        assertNotNull(merchantRepository.findByName("lidl"));
    }
}