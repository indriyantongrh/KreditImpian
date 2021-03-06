package com.application.kreditimpian.Api;


import com.application.kreditimpian.Model.RegisterModel.RegisterResponse;
import com.application.kreditimpian.ResponseMessage.ResponseLoginSucces;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface RequestInterface {

    /*API KreditImpian*/
    //Insert User Baru

      @FormUrlEncoded
    @POST("members/create")
    Call<RegisterResponse> create_member (
                                          @Field("username") String username,
                                          @Field("email") String email,
                                          @Field("phone") String phone,
                                          @Field("password") String password,
                                          @Field("password_confirm") String password_confirm);
    @FormUrlEncoded
    @POST("system/users/authenticate")
    Call<ResponseLoginSucces> login_member(@Field("username") String username,
                                           @Field("password") String password);




//    @FormUrlEncoded
//    @POST("registrasi_member.php")
//    Call<SuccessMessage> registrasi_member(@Field("id") String id,
//                                         @Field("email") String email,
//                                         @Field("msisdn") String msisdn,
//                                         @Field("username") String username,
//                                         @Field("password") String password);
//
//
//
//
//    @FormUrlEncoded
//    @POST("register_member.php")
//    Call<SuccessMessage> registrasi(@Field("id") String id,
//                                    @Field("namalengkap") String namalengkap,
//                                    @Field("email") String email,
//                                    @Field("nomortelepon") String nomortelepon,
//                                    @Field("password") String password);
//
//
//    @FormUrlEncoded
//    @POST("register_member.php")
//    Call<AllProductResponse> Allproduct();
//
//    //tanpa limit
//    @GET("api/companies")
//    Call<List<ModelMitra>> getMitra();
//
//
//    //tanpa limit
//    @GET("products")
//    Call<JSONResponse> getResult();



}
