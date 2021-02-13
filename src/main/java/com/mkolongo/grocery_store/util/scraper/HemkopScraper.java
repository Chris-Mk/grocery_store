package com.mkolongo.grocery_store.util.scraper;

import lombok.RequiredArgsConstructor;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class HemkopScraper implements BaseScraper {
    private static final String ROOT_URL = "https://www.hemkop.se/handla";

    private final WebDriver driver;

    @Override
    public void scrape() {
//        WebDriver driver = AppBeanConfig.driver();
        driver.get(ROOT_URL);

        List<WebElement> categories = driver
                .findElement(By.cssSelector("ax-navigation[root=category]"))
                .findElement(By.tagName("ul"))
                .findElements(By.tagName("li"));

            /*
            TODO
                loop through all categories a navigate to their respective links
            */

        String link = categories.get(0)
                .findElement(By.tagName("a"))
                .getAttribute("href");

        driver.navigate().to(link);

        int currCount = 0;
        int totalCount = Integer.parseInt(driver.findElement(By.cssSelector("div.ax-product-results"))
                .getText()
                .split("\\s+")[0]);

        while (totalCount != currCount) {
            ((JavascriptExecutor) driver)
                    .executeScript("window.scrollTo(0, document.body.scrollHeight)");
            currCount = driver.findElements(By.cssSelector("div.ax-product-grid-tile")).size();
        }

        int size = driver.findElement(By.className("ax-product-grid-content"))
                .findElements(By.cssSelector("div.selenium--product-puff")).size();
        System.out.println(size);

        driver.quit();
    }
}
