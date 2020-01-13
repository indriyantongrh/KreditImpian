package com.application.kreditimpian.Api.ApiHeader;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;

/**
 * Created by indriyanto Nugroho on 7 Jan 2020.
 */
public interface ServiceRepository {

//    @Headers({
//            "Accept: application/json",
//            "x-rainbow-client: android",
//            "x-rainbow-client-version: 1.10.7"
//    })
    @GET("system/users/authenticate")
    Call<AuthorizationResponse> login(@Header("Authorization") String authorization);

}
