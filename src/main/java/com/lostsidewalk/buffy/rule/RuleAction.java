package com.lostsidewalk.buffy.rule;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import static java.util.Arrays.copyOf;

/**
 * Represents the definition of a rule action.
 * <p>
 * Rule actions define the actions to take when the comparison conditions are satisfied; specifically:
 *   1. the action type (web hook, mark post as read, mark post as read-later, etc.)
 *   2. a list of parameters (specific to each action, e.g., web hook requires URL and authentication parameters)
 *   3. the action sequence, a number that is used to identify the order in which a specific action will be invoked
 */
@Slf4j
@Data
public class RuleAction {

    /**
     * Enumeration representing the type of rule action.
     */
    @SuppressWarnings("unused")
    enum ActionType {
        WEBHOOK,
        MARK_AS_READ,
        MARK_AS_READ_LATER,
    }

    ActionType actionType;

    Object[] parameters;

    Long sequence;

    /**
     * Creates a new RuleAction with the specified properties.
     *
     * @param actionType  The type of the rule action (e.g., WEBHOOK, MARK_AS_READ).
     * @param parameters  The parameters associated with the rule action.
     * @param sequence    The sequence number of the rule action.
     */
    RuleAction(ActionType actionType, Object[] parameters, Long sequence) {
        this.actionType = actionType;
        this.parameters = parameters == null ? null : copyOf(parameters, parameters.length);
        this.sequence = sequence;
    }

    /**
     * Static factory method to create a RuleAction object with the specified properties.
     *
     * @param actionType  The type of the rule action (e.g., WEBHOOK, MARK_AS_READ).
     * @param parameters  The parameters associated with the rule action.
     * @param sequence    The sequence number of the rule action.
     * @return A new RuleAction instance with the specified properties.
     */
    public static RuleAction from(ActionType actionType, Object[] parameters, Long sequence) {
        return new RuleAction(actionType, parameters, sequence);
    }
}
