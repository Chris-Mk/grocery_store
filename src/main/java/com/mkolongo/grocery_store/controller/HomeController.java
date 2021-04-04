package com.mkolongo.grocery_store.controller;

import com.mkolongo.grocery_store.util.scraper.HemkopScraper;
import com.mkolongo.grocery_store.util.scraper.WillysScraper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class HomeController {

    private final WillysScraper willysScraper;
    private final HemkopScraper hemkopScraper;

    @GetMapping("/")
    public String home() {
//        willysScraper.scrape();
        hemkopScraper.scrape();
        return "Successfully scraped all data!!!";
    }
}
