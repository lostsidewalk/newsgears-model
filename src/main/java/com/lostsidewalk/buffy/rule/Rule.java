package com.lostsidewalk.buffy.rule;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.Collection;
import java.util.Set;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;
import static java.util.Set.copyOf;

/**
 * Represents the definition of a rule. A rule consists primarily of a set of conditions, a set of actions, and a matchType.
 * <p>
 * A rule condition defines the comparison in three parts:
 *   1. between a field given by the FieldName enumeration (i.e., title, description, contents, etc.)
 *   2. and a literal value ('jim', 'j.*', etc.)
 *   3. using a comparison of ComparisonType (equals, regexp-match, contains, starts-with, ends-with, etc.).
 * <p>
 * The rule actions define the actions to take when the comparison conditions are satisfied; specifically:
 *   1. the action type (web hook, mark post as read, mark post as read-later, etc.)
 *   2. a list of parameters (specific to each action, e.g., web hook requires URL and authentication parameters)
 *   3. the action sequence, a number that is used to identify the order in which a specific action will be invoked
 * <p>
 * If the matchType is ANY, then the rule fires all of the actions if any one of the supplied conditions are met;
 * if the matchType is ALL, then the rule fires all of the actions ONLY if all of the supplied conditions are met.
 */
@Slf4j
@Data
@JsonInclude(NON_EMPTY)
public class Rule {

    Long id;

    String name;

    Set<RuleCondition> conditions;

    Set<RuleAction> actions;

    MatchType matchType;

    /**
     * Enumeration representing the match type for rule conditions.
     */
    @SuppressWarnings("unused")
    enum MatchType {
        ANY, ALL
    }

    /**
     * Creates a new Rule with the specified properties.
     *
     * @param id        The unique identifier for the rule.
     * @param name      The name of the rule.
     * @param conditions The set of conditions associated with the rule.
     * @param actions   The set of actions to be performed if the rule conditions are met.
     * @param matchType The match type for the rule conditions (ANY or ALL).
     */
    Rule(Long id, String name, Collection<? extends RuleCondition> conditions, Collection<? extends RuleAction> actions, MatchType matchType) {
        this.id = id;
        this.name = name;
        this.conditions = conditions == null ? null : copyOf(conditions);
        this.actions = actions == null ? null : copyOf(actions);
        this.matchType = matchType;
    }

    /**
     * Static factory method to create a Rule object with the specified properties.
     *
     * @param id        The unique identifier for the rule.
     * @param name      The name of the rule.
     * @param conditions The set of conditions associated with the rule.
     * @param actions   The set of actions to be performed if the rule conditions are met.
     * @param matchType The match type for the rule conditions (ANY or ALL).
     * @return A new Rule instance with the specified properties.
     */
    public static Rule from(Long id, String name, Set<? extends RuleCondition> conditions, Set<? extends RuleAction> actions, MatchType matchType) {
        return new Rule(id, name, conditions, actions, matchType);
    }
}
