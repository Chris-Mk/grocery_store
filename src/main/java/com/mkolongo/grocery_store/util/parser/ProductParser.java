package com.mkolongo.grocery_store.util.parser;

import com.mkolongo.grocery_store.domain.models.binding.ProductBindingModel;
import org.openqa.selenium.WebDriver;

import java.util.Set;

public interface ProductParser {

    Set<ProductBindingModel> parse(WebDriver driver);
}
