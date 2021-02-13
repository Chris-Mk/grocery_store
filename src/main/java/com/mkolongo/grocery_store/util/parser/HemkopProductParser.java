package com.mkolongo.grocery_store.util.parser;

import com.mkolongo.grocery_store.domain.models.binding.ProductBindingModel;
import org.openqa.selenium.WebDriver;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class HemkopProductParser implements ProductParser {

    @Override
    public Set<ProductBindingModel> parse(WebDriver driver) {
        return null;
    }
}
