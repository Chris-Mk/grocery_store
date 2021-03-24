package com.mkolongo.grocery_store.config;

import org.modelmapper.ModelMapper;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.FluentWait;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

@Configuration
public class AppBeanConfig {
    private static final String DRIVER_NAME = "webdriver.chrome.driver";
    private static final String DRIVER_PATH = "C:/Users/User/OneDrive/Desktop/chromedriver.exe";

    @Bean
    public WebDriver driver() {
        System.setProperty(DRIVER_NAME, DRIVER_PATH);
        ChromeOptions options = new ChromeOptions();
        options.setHeadless(true);
//        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS); //implicit wait
        return new ChromeDriver(options);
    }

    @Bean
    public FluentWait<WebDriver> waiter() {
        return new FluentWait<>(driver())
                .withTimeout(Duration.ofSeconds(30))
                .pollingEvery(Duration.ofSeconds(5))
                .ignoring(StaleElementReferenceException.class,
                        NoSuchElementException.class);
    }

    @Bean
    public ModelMapper mapper() {
        ModelMapper mapper = new ModelMapper();
        return mapper;
    }
}
