package com.lostsidewalk.buffy.auth;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Represents a user role in the application.
 */
@Data
@AllArgsConstructor
@SuppressWarnings("unused")
public class Role {

    /**
     * The unique identifier of the role.
     */
    private Long id;

    /**
     * The name of the role.
     */
    private String name;
}
