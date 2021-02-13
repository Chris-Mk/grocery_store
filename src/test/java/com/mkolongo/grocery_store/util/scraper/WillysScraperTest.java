package com.mkolongo.grocery_store.util.scraper;

import com.mkolongo.grocery_store.util.parser.ProductParser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

class WillysScraperTest {

    BaseScraper scraper;

    @Mock
    ProductParser parser;

    @BeforeEach
    void setUp() {
//        scraper = new WillysScraper(parser);
    }

    @Test
    void shouldReturnAllCategoriesFromTheSidebar() {
        scraper.scrape();
    }
}