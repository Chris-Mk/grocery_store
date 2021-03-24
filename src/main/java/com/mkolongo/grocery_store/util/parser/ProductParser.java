package com.mkolongo.grocery_store.util.parser;

import com.mkolongo.grocery_store.domain.models.binding.ProductBindingModel;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Set;

public interface ProductParser {

    Set<ProductBindingModel> parse(String category, List<WebElement> products);
}
