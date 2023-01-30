package com.lostsidewalk.buffy.post;

import com.rometools.modules.mediarss.types.Price;
import com.rometools.modules.mediarss.types.Price.Type;
import lombok.Data;

import java.net.URL;

@Data
public class PostMediaPrice {

    private Type type;

    private Double price;

    private String currency;

    private URL info;

    PostMediaPrice(Type type, Double price, String currency, URL info) {
        this.type = type;
        this.price = price;
        this.currency = currency;
        this.info = info;
    }

    public static PostMediaPrice from(Price t) {
        return new PostMediaPrice(t.getType(), t.getPrice(), t.getCurrency(), t.getInfo());
    }

    public Price toModule() {
        Price p = new Price();
        p.setType(type);
        p.setPrice(price);
        p.setCurrency(currency);
        p.setInfo(info);

        return p;
    }
}
