package com.mkolongo.grocery_store.util.parser;

import com.mkolongo.grocery_store.domain.models.binding.ProductBindingModel;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component("coopParser")
public class CoopParser implements ProductParser {

    @Override
    public Set<ProductBindingModel> parse(String category, List<WebElement> products) {
        Set<ProductBindingModel> productBindingModels = new HashSet<>();

        products.forEach(p -> {
            String imageUrl = p.findElement(By.cssSelector("div[class='ItemTeaser-image']"))
                    .findElement(By.tagName("img"))
                    .getAttribute("src");

            ProductBindingModel bindingModel = new ProductBindingModel();
            String[] data = p.getText().split("\n");

            // 2 for
            if (data[0].contains(" ") && Character.isDigit(data[0].charAt(0))) {
                int descriptionIndex = data[3].indexOf(".");
                double price = Double.parseDouble(data[1].substring(0, data[1].indexOf(":")));

                bindingModel.setPrice(BigDecimal.valueOf(price));
                bindingModel.setUnits(data[0]);
                bindingModel.setName(data[2] + " " + data[3].substring(0, descriptionIndex));
                bindingModel.setDescription(data[3].substring(descriptionIndex + 1));

//                if ("Alltid bra pris!".equals(data[1])) {
//                    bindingModel.setName(data[2] + " " + data[3]);
//                    bindingModel.setDescription(buildDescription(data, 4).toString());
//                } else {
//                    bindingModel.setName(data[1] + " " + data[2]);
//                    bindingModel.setDescription(buildDescription(data, 3).toString());
//                }

                // in promo (starts with price)
//            } else if (Character.isDigit(data[0].charAt(0))) {
//                String[] priceAndCents = data[0].split(",");
//                double price = Double.parseDouble(priceAndCents[0] + "." + priceAndCents[1]);
//                bindingModel.setPrice(BigDecimal.valueOf(price));
//                bindingModel.setUnits(data[1]);
//
//                if ("Alltid bra pris!".equals(data[2])) {
//                    bindingModel.setName(data[3] + " " + data[4]);
//                    bindingModel.setDescription(buildDescription(data, 5).toString());
//                } else {
//                    bindingModel.setName(data[2] + " " + data[3]);
//                    bindingModel.setDescription(buildDescription(data, 4).toString());
//                }
            } else {
                String[] line = data[data.length - 1].split(" ");
                String[] priceAndCents = line[0].split(":");
                double price = Double.parseDouble(priceAndCents[0] + "." + priceAndCents[1]);
                bindingModel.setPrice(BigDecimal.valueOf(price));
                bindingModel.setUnits(line[1]);

                bindingModel.setName(data[0] + " " + data[1].substring(0, data[1].indexOf(".")));
                bindingModel.setDescription(data[1].substring(data[1].indexOf(".") + 1));
            }

            bindingModel.setImageUrl(imageUrl);
            bindingModel.setCategory(category);
            productBindingModels.add(bindingModel);

        });

        return productBindingModels;
    }

//    private StringBuilder buildDescription(String[] data, int index) {
//        StringBuilder builder = new StringBuilder();
//
//        for (int i = index; i < data.length; i++) {
//            if (Character.isDigit(data[i].charAt(0))) break;
//            builder.append(data[i]).append("#");
//        }
//        return builder;
//    }
}
