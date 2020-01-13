package com.application.kreditimpian.Api.ApiHeader;

/**
 * Created by indriyanto Nugroho on 7 Jan 2020.
 */
public class AuthorizationResponse {

    private LoggedInUser loggedInUser;
    private String token;

    public LoggedInUser getLoggedInUser() {
        return loggedInUser;
    }

    public String getToken() {
        return token;
    }
}
