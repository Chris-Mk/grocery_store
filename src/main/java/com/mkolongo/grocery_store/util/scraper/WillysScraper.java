package com.mkolongo.grocery_store.util.scraper;

import com.mkolongo.grocery_store.domain.models.binding.MerchantBindingModel;
import com.mkolongo.grocery_store.domain.models.binding.ProductBindingModel;
import com.mkolongo.grocery_store.service.abstraction.MerchantProductService;
import com.mkolongo.grocery_store.util.parser.ProductParser;
import org.openqa.selenium.By;
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
public class WillysScraper implements BaseScraper {
    private static final String ROOT_URL = "https://www.willys.se/";
    private static final String MERCHANT_NAME = "Willys";

    private final ProductParser parser;
    private final WebDriver driver;
    private final FluentWait<WebDriver> waiter;
    private final MerchantProductService merchantProductService;

    public WillysScraper(@Qualifier("willysParser") ProductParser parser,
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
        String logoUrl = driver.findElement(By.cssSelector("img[title=Willys]"))
                .getAttribute("src");

        MerchantBindingModel merchantBindingModel = new MerchantBindingModel(MERCHANT_NAME, logoUrl);

        getCategoryLinks().forEach(link -> {
            driver.navigate().to(link);
//            loadAllProducts();

            String category = waiter.until(ExpectedConditions.presenceOfElementLocated(
                    By.id("selenium--product-grid-header"))).getText();
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
            WebElement button = waiter.until(ExpectedConditions
                    .presenceOfElementLocated(By.className("ax-has-more-btn")))
                    .findElement(By.tagName("button"));
            button.click();
//            retryingFindClick();
            currCount = driver.findElements(By.className("ax-product-grid-tile")).size();
        }
    }

    private List<String> getCategoryLinks() {
//        Map<String, String> categoryLinksAndNames = new HashMap<>();
        List<String> links = new ArrayList<>();

        List<WebElement> sidebar = driver
//                waiter.until(ExpectedConditions
//                .presenceOfElementLocated(By.cssSelector("ax-navigation[root=category]")))
                .findElement(By.cssSelector("ax-navigation[root=category]"))
                .findElement(By.tagName("ul"))
                .findElements(By.tagName("li"));

        sidebar.stream()
                .limit(11)
                .forEach(e -> {
            String link = e.findElement(By.tagName("a")).getAttribute("href");
            links.add(link);
//            String name = link.substring(link.lastIndexOf("/") + 1);
//            categoryLinksAndNames.put(link, name);
        });
//        return categoryLinksAndNames;
        return links.stream().limit(1).collect(Collectors.toList());
    }

//    private void retryingFindClick() {
//        WebElement button = waiter.until(ExpectedConditions
//                .presenceOfElementLocated(By.className("ax-has-more-btn")))
//                .findElement(By.tagName("button"));
//        button.click();
//
//        ((JavascriptExecutor) driver)
//                .executeScript("window.scrollTo(0, document.body.scrollHeight)");
//        Actions actions = new Actions(driver);
//        actions.moveToElement(button).click().build().perform();

//        int attempts = 0;
//        while (attempts < 100) {
//            try {
//                WebElement button = wait.until(d -> d.findElement(By.className("ax-has-more-btn"))
//                        .findElement(By.tagName("button")));
//                button.click();
//                break;
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//            attempts++;
//        }
//    }
}
