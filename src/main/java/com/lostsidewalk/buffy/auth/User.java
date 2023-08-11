package com.lostsidewalk.buffy.auth;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

import static com.lostsidewalk.buffy.auth.AuthProvider.LOCAL;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphanumeric;

@Data
public class User {

    private Long id;

    @NotBlank
    private String username;

    @NotBlank
    private String password;

    @NotBlank
    private String emailAddress;

    private String authClaim;

    private String pwResetClaim;

    private String pwResetAuthClaim;

    private String verificationClaim;

    private boolean isVerified;

    private String subscriptionStatus;

    private Date subscriptionExpDate;

    private String customerId;

    @NotNull
    private AuthProvider authProvider;

    private String authProviderId; // TODO: should be authProviderUserId

    private String authProviderProfileImgUrl;

    private String authProviderUsername;

    @SuppressWarnings("unused")
    public User(String username, String password, String emailAddress) {
        this.username = username;
        this.password = password;
        this.emailAddress = emailAddress;
        this.authProvider = LOCAL;
        this.authProviderId = null;
        this.authProviderProfileImgUrl = null;
        this.authProviderUsername = null;
    }

    @SuppressWarnings("unused")
    public User(Long id, String username, String password, String emailAddress) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.emailAddress = emailAddress;
        this.authProvider = LOCAL;
        this.authProviderId = null;
        this.authProviderProfileImgUrl = null;
        this.authProviderUsername = null;
    }

    @SuppressWarnings("unused")
    public User(String username, String emailAddress, AuthProvider authProvider, String authProviderId, String authProviderProfileImgUrl, String authProviderUsername) {
        this.username = username;
        this.password = randomAlphanumeric(32);
        this.emailAddress = emailAddress;
        this.authProvider = authProvider;
        this.authProviderId = authProviderId;
        this.authProviderProfileImgUrl = authProviderProfileImgUrl;
        this.authProviderUsername = authProviderUsername;
    }
}
