package com.lostsidewalk.buffy.post;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.rometools.modules.mediarss.types.Expression;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;
import static com.rometools.modules.mediarss.types.Expression.*;

@Data
@JsonInclude(NON_EMPTY)
public class PostMediaExpression implements Serializable {

    @Serial
    private static final long serialVersionUID = 1223982304982233L;

    private String expression;

    PostMediaExpression(String expression) {
        this.expression = expression;
    }

    public static PostMediaExpression from(Expression expression) {
        return new PostMediaExpression(expression.toString());
    }

    public Expression toModule() {
        return  switch (this.expression) {
            case "FULL" -> FULL;
            case "NONSTOP" -> NONSTOP;
            case "SAMPLE" -> SAMPLE;
            default -> null;
        };
    }
}
