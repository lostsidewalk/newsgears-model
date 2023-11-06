package com.lostsidewalk.buffy.post;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.rometools.modules.mediarss.types.Price;
import com.rometools.modules.mediarss.types.Price.Type;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.net.URL;
import java.util.Currency;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;

/**
 * Represents a price associated with media in a post.
 */
@Slf4j
@Data
@JsonInclude(NON_EMPTY)
public class PostMediaPrice implements Serializable {

    @Serial
    private static final long serialVersionUID = 23412156442343L;

    /**
     * Type of the price.
     */
    Type type;

    /**
     * Price value.
     */
    BigDecimal price;

    /**
     * Currency of the price.
     */
    Currency currency;

    /**
     * URL with more information about the price.
     */
    URL info;

    /**
     * Creates a new instance of PostMediaPrice.
     *
     * @param type     Type of the price.
     * @param price    Price value.
     * @param currency Currency of the price.
     * @param info     URL with more information about the price.
     */
    PostMediaPrice(Type type, BigDecimal price, Currency currency, URL info) {
        this.type = type;
        this.price = price;
        this.currency = currency;
        this.info = info;
    }

    /**
     * Creates a new instance of PostMediaPrice from a Price object.
     *
     * @param t The Price object to convert.
     * @return A new PostMediaPrice instance.
     */
    public static PostMediaPrice from(Price t) {
        return new PostMediaPrice(t.getType(), t.getPrice(), t.getCurrency(), t.getInfo());
    }

    /**
     * Converts the PostMediaPrice instance to a Price object.
     *
     * @return A Price object.
     */
    public final Price toModule() {
        Price p = new Price();
        p.setType(type);
        p.setPrice(price);
        p.setCurrency(currency);
        p.setInfo(info);

        return p;
    }
}
