package com.lostsidewalk.buffy.rule;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * Represents the definition of a rule condition.
 * <p>
 * A rule condition defines a comparison operation in three parts:
 *   1. a source field given by the FieldName enumeration (i.e., title, description, contents, etc.)
 *   2. a target literal value ('jim', 'j.*')
 *   3. and a comparison of ComparisonType (equals, regexp-match, contains, starts-with, ends-with, etc.).
 * <p>
 * The comparison operation is applied to the source and target values to determine the overall result of the rule
 * condition.
 */
@Slf4j
@Data
public class RuleCondition {

    /**
     * Enumeration representing the field names for rule conditions.
     */
    @SuppressWarnings("unused")
    enum FieldName {
        TITLE, DESCRIPTION, CONTENTS
    }

    /**
     * Enumeration representing the comparison types for rule conditions.
     */
    @SuppressWarnings("unused")
    enum ComparisonType {
        EQ_LITERAL, EQ_REGEXP, CONTAINS, STARTS_WITH, ENDS_WITH
    }

    FieldName fieldName;

    ComparisonType comparisonType;

    Object fieldValue;

    /**
     * Creates a new RuleCondition with the specified properties.
     *
     * @param fieldName      The field name for the rule condition (e.g., TITLE, DESCRIPTION).
     * @param comparisonType The comparison type for the rule condition (e.g., EQ_LITERAL, CONTAINS).
     * @param fieldValue     The value to be compared in the rule condition.
     */
    RuleCondition(FieldName fieldName, ComparisonType comparisonType, Object fieldValue) {
        this.fieldName = fieldName;
        this.comparisonType = comparisonType;
        this.fieldValue = fieldValue;
    }

    /**
     * Static factory method to create a RuleCondition object with the specified properties.
     *
     * @param fieldName      The field name for the rule condition (e.g., TITLE, DESCRIPTION).
     * @param comparisonType The comparison type for the rule condition (e.g., EQ_LITERAL, CONTAINS).
     * @param fieldValue     The value to be compared in the rule condition.
     * @return A new RuleCondition instance with the specified properties.
     */
    public static RuleCondition from(FieldName fieldName, ComparisonType comparisonType, Object fieldValue) {
        return new RuleCondition(fieldName, comparisonType, fieldValue);
    }
}
