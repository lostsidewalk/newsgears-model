package com.lostsidewalk.buffy.post;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.rometools.modules.mediarss.types.Expression;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.Serial;
import java.io.Serializable;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;
import static com.rometools.modules.mediarss.types.Expression.*;

/**
 * Represents media expression associated with a post's media.
 */
@Slf4j
@Data
@EqualsAndHashCode
@JsonInclude(NON_EMPTY)
@NoArgsConstructor
public class PostMediaExpression implements Serializable {

    @Serial
    private static final long serialVersionUID = 1223982304982233L;

    /**
     * The expression describing media content.
     */
    String expression;

    /**
     * Constructor to create a PostMediaExpression object.
     *
     * @param expression The expression describing media content.
     */
    PostMediaExpression(String expression) {
        this.expression = expression;
    }

    /**
     * Creates a PostMediaExpression object from an Expression instance.
     *
     * @param expression The Expression instance to convert from.
     * @return A PostMediaExpression object with data from the Expression instance.
     */
    public static PostMediaExpression from(Expression expression) {
        return new PostMediaExpression(expression.toString());
    }

    /**
     * Converts the PostMediaExpression object to an Expression instance.
     *
     * @return An Expression instance representing the PostMediaExpression data.
     */
    public final Expression toModule() {
        return switch (expression) {
            case "FULL" -> FULL;
            case "NONSTOP" -> NONSTOP;
            case "SAMPLE" -> SAMPLE;
            default -> null;
        };
    }
}
