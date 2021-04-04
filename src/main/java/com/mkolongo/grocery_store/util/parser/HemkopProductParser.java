package com.mkolongo.grocery_store.util.parser;

import com.mkolongo.grocery_store.domain.models.binding.ProductBindingModel;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
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
            String[] data = p.getText().split("\n");

            // 2 for
            if (data[0].contains(" ") && Character.isDigit(data[0].charAt(0))) {
                String[] line = data[0].split(" ");

                double price = Double.parseDouble(line[2]);
                bindingModel.setPrice(BigDecimal.valueOf(price));
                bindingModel.setUnits(line[0] + " " + line[1]);

                if ("Alltid bra pris!".equals(data[1])) {
                    bindingModel.setName(data[2] + " " + data[3]);
                    bindingModel.setDescription(buildDescription(data, 4).toString());
                } else {
                    bindingModel.setName(data[1] + " " + data[2]);
                    bindingModel.setDescription(buildDescription(data, 3).toString());
                }

                // in promo (starts with price)
            } else if (Character.isDigit(data[0].charAt(0))) {
                String[] priceAndCents = data[0].split(",");
                double price = Double.parseDouble(priceAndCents[0] + "." + priceAndCents[1]);
                bindingModel.setPrice(BigDecimal.valueOf(price));
                bindingModel.setUnits(data[1]);

                if ("Alltid bra pris!".equals(data[2])) {
                    bindingModel.setName(data[3] + " " + data[4]);
                    bindingModel.setDescription(buildDescription(data, 5).toString());
                } else {
                    bindingModel.setName(data[2] + " " + data[3]);
                    bindingModel.setDescription(buildDescription(data, 4).toString());
                }
            } else {
                String[] line = data[3].split(" ");
                String[] priceAndCents = line[0].split(",");
                double price = Double.parseDouble(priceAndCents[0] + "." + priceAndCents[1]);
                bindingModel.setPrice(BigDecimal.valueOf(price));
                bindingModel.setUnits(line[1]);

                bindingModel.setName(data[0] + " " + data[1]);
                bindingModel.setDescription(data[2]);
            }

            bindingModel.setImageUrl(imageUrl);
            bindingModel.setCategory(category);
            productBindingModels.add(bindingModel);

        });

        return productBindingModels;
    }

    private StringBuilder buildDescription(String[] data, int index) {
        StringBuilder builder = new StringBuilder();

        for (int i = index; i < data.length; i++) {
            if (Character.isDigit(data[i].charAt(0))) break;
            builder.append(data[i]).append("#");
        }
        return builder;
    }
}
