package com.application.kreditimpian.Api.api_v2;



import androidx.viewpager.widget.PagerAdapter;


import com.application.kreditimpian.Model.ModelProduct.ResponseProduct;
import com.application.kreditimpian.Model.ModelProductNew.ProductResponse;
import com.application.kreditimpian.Model.ModelUser.UserResponse;
import com.application.kreditimpian.Model.ModelUserDetail.ResponseUserDetail;
import com.application.kreditimpian.Model.ModelUserDetail.ResultItem;
import com.application.kreditimpian.Model.UserModel.User;
import com.application.kreditimpian.ResponseMessage.ResponseLoginSucces;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;


public interface BaseApiService {

    // Fungsi ini untuk memanggil
    @FormUrlEncoded
    @POST("system/users/authenticate")
    Call<ResponseLoginSucces> loginRequest(@Field("username") String username,
                                           @Field("password") String password);

    //getAllProduct
    @GET("products")
    Call<ResponseProduct> getResult(@Header("Authorization") String result);

    //getAllProductFashion
    @GET("products?id_product_category=2")
    Call<ResponseProduct> getResultFashion();

    //getAllProductForniture
    @GET("products?id_product_category=3")
    Call<ResponseProduct> getResultForniture();

    //getAllProductOtomotif
    @GET("products?id_product_category=4")
    Call<ResponseProduct> getResultOtomotif();

    //getAllProductMultiproduct
    @GET("products?id_product_category=5")
    Call<ResponseProduct> getResultMultiproduct();

    //getAllProductKomputer
    @GET("products?id_product_category=11")
    Call<ResponseProduct> getResultKomputer();

    //getAllProductGadget
    @GET("products?id_product_category=12")
    Call<ResponseProduct> getResultGadget();

    //getAllProductElektronik
    @GET("products?id_product_category=15")
    Call<ResponseProduct> getResultElektronik();

    //getAllProductHobi
    @GET("products?id_product_category=16")
    Call<ResponseProduct> getResultHobi();

    //getAllProductCoorporate
    @GET("products?id_product_category=17")
    Call<ResponseProduct> getResultCoorporate();

    //getAllProductHobi
    @GET("products?id_product_category=18")
    Call<ResponseProduct> getResultProperty();

    //getUserMember
    @Headers({ "Content-Type: application/x-www-form-urlencoded"})
    @GET("system/users")
    Call<ResponseLoginSucces> getUsermember(@Header("Authorization") String result);




}
