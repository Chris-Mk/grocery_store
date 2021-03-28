package com.mkolongo.grocery_store.util.parser;

import com.mkolongo.grocery_store.domain.models.binding.ProductBindingModel;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component("willysParser")
public class WillysProductParser implements ProductParser {

    @Override
    public Set<ProductBindingModel> parse(String category, List<WebElement> products) {
        Set<ProductBindingModel> productBindingModels = new HashSet<>();

        products.forEach(p -> {
            String imageUrl = p.findElement(By.tagName("img")).getAttribute("src");
            ProductBindingModel bindingModel = new ProductBindingModel();
            String[] data = p.getText().split("\n");

            // 2 for
            if (data[0].contains(" ") && Character.isDigit(data[0].charAt(0))) {
                double price = Double.parseDouble(data[1] + "." + data[2]);
                bindingModel.setPrice(BigDecimal.valueOf(price));
                bindingModel.setUnits(data[0]);

                if ("VÄLJ & BLANDA".equals(data[3])) {
                    bindingModel.setName(data[4] + " " + data[5]);
                    bindingModel.setDescription(buildDescription(data, 6).toString());
                } else {
                    bindingModel.setName(data[3] + " " + data[4]);
                    bindingModel.setDescription(buildDescription(data, 5).toString());
                }

                // spara
            } else if (Character.isLetter(data[0].charAt(0))) {
                double price = Double.parseDouble(data[1] + "." + data[2]);
                bindingModel.setPrice(BigDecimal.valueOf(price));
                bindingModel.setUnits(data[3]);
                bindingModel.setName(data[4] + " " + data[5]);
                bindingModel.setDescription(buildDescription(data, 6).toString());
            } else {
                double price = Double.parseDouble(data[0] + "." + data[1]);
                bindingModel.setPrice(BigDecimal.valueOf(price));
                bindingModel.setUnits(data[2]);
                bindingModel.setName(data[3] + " " + data[4]);
                bindingModel.setDescription(buildDescription(data, 5).toString());
            }

            bindingModel.setImageUrl(imageUrl);
            bindingModel.setCategory(category);
            productBindingModels.add(bindingModel);
        });
        return productBindingModels;
    }

    private StringBuilder buildDescription(String[] data, int index) {
        StringBuilder builder = new StringBuilder();
        for (int i = index; i < data.length - 3; i++) {
            builder.append(data[i]).append("#");
        }
        return builder;
    }
}
