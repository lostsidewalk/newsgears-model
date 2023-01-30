package com.lostsidewalk.buffy.post;

import com.rometools.modules.mediarss.types.Category;
import lombok.Data;

@Data
public class PostMediaCategory {

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
