package com.lostsidewalk.buffy.post;

import com.rometools.modules.mediarss.types.Expression;
import lombok.Data;

import static com.rometools.modules.mediarss.types.Expression.*;

@Data
public class PostMediaExpression {

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
