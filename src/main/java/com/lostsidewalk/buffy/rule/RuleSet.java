package com.lostsidewalk.buffy.rule;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.Collection;
import java.util.Set;

import static java.util.Set.copyOf;

/**
 * Represents the definition of a rule set.
 */
@Slf4j
@Data
public class RuleSet {

    Long id;

    String username;

    String name;

    Set<Rule> rules;

    /**
     * Creates a new RuleSet with the specified properties.
     *
     * @param id        The unique identifier for the rule set.
     * @param username  The username associated with the rule set.
     * @param name      The name of the rule set.
     * @param rules     The set of rules associated with the rule set.
     */
    RuleSet(Long id, String username, String name, Collection<? extends Rule> rules) {
        this.id = id;
        this.username = username;
        this.name = name;
        this.rules = rules == null ? null : copyOf(rules);
    }

    /**
     * Static factory method to create a RuleSet object with the specified properties.
     *
     * @param id        The unique identifier for the rule set.
     * @param username  The username associated with the rule set.
     * @param name      The name of the rule set.
     * @param rules     The set of rules associated with the rule set.
     * @return A new RuleSet instance with the specified properties.
     */
    public static RuleSet from(Long id, String username, String name, Set<? extends Rule> rules) {
        return new RuleSet(id, username, name, rules);
    }
}
