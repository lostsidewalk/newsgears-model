package com.lostsidewalk.buffy.auth;

import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("unused")
public enum  AuthProvider {
    LOCAL,
    GOOGLE("google"),
    GITHUB("github");

    public final String registrationId;

    AuthProvider(String registrationId) {
        this.registrationId = registrationId;
    }

    AuthProvider() {
        this.registrationId = null;
    }

    static final Map<String, AuthProvider> AUTH_PROVIDERS_BY_REGISTRATION_ID = new HashMap<>();
    static {
        for (AuthProvider a : values()) {
            AUTH_PROVIDERS_BY_REGISTRATION_ID.put(a.registrationId, a);
        }
    }

    public static AuthProvider byRegistrationId(String registrationId) {
        return AUTH_PROVIDERS_BY_REGISTRATION_ID.get(registrationId);
    }
}
