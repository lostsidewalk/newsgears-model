package com.lostsidewalk.buffy.auth;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * Represents a user role in the application.
 */
@Slf4j
@Data
@AllArgsConstructor
@SuppressWarnings("unused")
public class Role {

    /**
     * Default constructor; initializes the object.
     */
    Role() {
    }

    /**
     * The unique identifier of the role.
     */
    private Long id;

    /**
     * The name of the role.
     */
    private String name;
}
