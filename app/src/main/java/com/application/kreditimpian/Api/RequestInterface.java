package com.application.kreditimpian.Api;

import com.application.kreditimpian.Model.ModelAllProduct.AllProductResponse;
import com.application.kreditimpian.Model.ModelMitra;
import com.application.kreditimpian.ResponseMessage.ResponseLogin;
import com.application.kreditimpian.ResponseMessage.ResponseLoginSucces;
import com.application.kreditimpian.ResponseMessage.ResponseRegister;

import java.util.List;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface RequestInterface {

    /*API KreditImpian*/
    //Insert User Baru
    @FormUrlEncoded
    @POST("members/create")
    Call<ResponseRegister> create_member (@Field("id") String id,
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
