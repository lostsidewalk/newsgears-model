package com.lostsidewalk.buffy.post;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.rometools.modules.mediarss.types.Category;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;

@Data
@JsonInclude(NON_EMPTY)
public class PostMediaCategory implements Serializable {

    @Serial
    private static final long serialVersionUID = 209384029384L;

    private String label;

    private String scheme;

    private String value;

    PostMediaCategory(String label, String scheme, String value) {
        this.label = label;
        this.scheme = scheme;
        this.value = value;
    }

    public static PostMediaCategory from(Category t) {
        return new PostMediaCategory(t.getLabel(), t.getScheme(), t.getValue());
    }

    public Category toModule() {
        return new Category(scheme, label, value);
    }
}
