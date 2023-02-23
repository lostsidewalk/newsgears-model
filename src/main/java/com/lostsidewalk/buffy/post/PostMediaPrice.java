package com.lostsidewalk.buffy.post;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.rometools.modules.mediarss.types.Price;
import com.rometools.modules.mediarss.types.Price.Type;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.net.URL;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;

@Data
@JsonInclude(NON_EMPTY)
public class PostMediaPrice implements Serializable {

    @Serial
    private static final long serialVersionUID = 23412156442343L;

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
