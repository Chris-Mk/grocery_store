package com.mkolongo.grocery_store.util.scraper;

import com.mkolongo.grocery_store.domain.models.binding.MerchantBindingModel;
import com.mkolongo.grocery_store.domain.models.binding.ProductBindingModel;
import com.mkolongo.grocery_store.service.abstraction.MerchantProductService;
import com.mkolongo.grocery_store.util.parser.ProductParser;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class HemkopScraper implements BaseScraper {
    private static final String ROOT_URL = "https://www.hemkop.se/handla";
    private static final String MERCHANT_NAME = "Hemköp";

    private final ProductParser parser;
    private final WebDriver driver;
    private final FluentWait<WebDriver> waiter;
    private final MerchantProductService merchantProductService;

    public HemkopScraper(@Qualifier("hemkopParser") ProductParser parser,
                         WebDriver driver, FluentWait<WebDriver> waiter,
                         MerchantProductService merchantProductService) {
        this.parser = parser;
        this.driver = driver;
        this.waiter = waiter;
        this.merchantProductService = merchantProductService;
    }

    @Override
    public void scrape() {
        driver.get(ROOT_URL);
        String logoUrl = driver.findElement(By.cssSelector("img[title=Hemköp]"))
                .getAttribute("src");

        MerchantBindingModel merchantBindingModel = new MerchantBindingModel(MERCHANT_NAME, logoUrl);

        getCategoryLinks().forEach(link -> {
            driver.navigate().to(link);
//            loadAllProducts();

            String category = waiter.until(ExpectedConditions.presenceOfElementLocated(
                    By.id("selenium-product-grid-header"))).getText();
            List<WebElement> products = driver.findElement(By.className("ax-product-grid-content"))
                    .findElements(By.cssSelector("div.selenium--product-puff"));

            Set<ProductBindingModel> productBindingModels = parser.parse(category, products);
            merchantProductService
                    .registerMerchantAndProducts(merchantBindingModel, productBindingModels);
        });

        driver.quit();
    }

    private void loadAllProducts() {
        int currCount = 0;
        int totalCount = Integer.parseInt(driver
                .findElement(By.cssSelector("div.ax-product-results"))
                .getText()
                .split("\\s+")[0]);

        while (totalCount != currCount) {
            ((JavascriptExecutor) driver)
                    .executeScript("window.scrollTo(0, document.body.scrollHeight)");
            currCount = driver.findElements(By.cssSelector("div.ax-product-grid-tile")).size();
        }
    }

    private List<String> getCategoryLinks() {
        List<String> links = new ArrayList<>();

        List<WebElement> sidebar = driver
                .findElement(By.cssSelector("ax-navigation[root=category]"))
                .findElement(By.tagName("ul"))
                .findElements(By.tagName("li"));

        sidebar.stream()
                .limit(12)
                .forEach(e -> links.add(e.findElement(By.tagName("a")).getAttribute("href")));

        return links.stream().limit(1).collect(Collectors.toList());
    }
}
