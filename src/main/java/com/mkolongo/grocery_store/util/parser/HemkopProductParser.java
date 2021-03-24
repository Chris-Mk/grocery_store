package com.mkolongo.grocery_store.util.parser;

import com.mkolongo.grocery_store.domain.models.binding.ProductBindingModel;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component("hemkopParser")
public class HemkopProductParser implements ProductParser {

    @Override
    public Set<ProductBindingModel> parse(String category, List<WebElement> products) {
        Set<ProductBindingModel> productBindingModels = new HashSet<>();

        products.forEach(p -> {
            String imageUrl = p.findElement(By.tagName("img")).getAttribute("src");
            ProductBindingModel bindingModel = new ProductBindingModel();


        });

        return productBindingModels;
    }
}
