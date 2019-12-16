package com.application.kreditimpian.Api.api_v2;



import com.application.kreditimpian.Model.ModelAllProduct.AllProductResponse;
import com.application.kreditimpian.Model.ModelProduct.ResponseProduct;
import com.application.kreditimpian.ResponseMessage.ResponseLoginSucces;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;


public interface BaseApiService {

    // Fungsi ini untuk memanggil
    @FormUrlEncoded
    @POST("system/users/authenticate")
    Call<ResponseLoginSucces> loginRequest(@Field("username") String email,
                                           @Field("password") String password);
//
//    // Fungsi ini untuk memanggil API
//    @FormUrlEncoded
//    @POST("register.php")
//    Call<ResponseBody> registerRequest(@Field("nama") String nama,
//                                       @Field("email") String email,
//                                       @Field("password") String password);
    //getAllProduct
    @GET("products")
    Call<ResponseProduct> getResult();

    //getAllProductGadget
    @GET("products?id_product_category=1")
    Call<AllProductResponse> getResultGadget();

    //getAllProductElektronik
    @GET("products?id_product_category=2")
    Call<AllProductResponse> getResultElektronik();

    //getAllProductForniture
    @GET("products?id_product_category=3")
    Call<AllProductResponse> getResultForniture();

    //getAllProductOtomotif
    @GET("products?id_product_category=4")
    Call<AllProductResponse> getResultOtomotif();

    //getAllProductFashion
    @GET("products?id_product_category=5")
    Call<AllProductResponse> getResultFashion();

    //getAllProductHobi
    @GET("products?id_product_category=6")
    Call<AllProductResponse> getResultHobi();

    //getAllProductHobi
    @GET("products?id_product_category=7")
    Call<AllProductResponse> getResultProperty();

    //getAllProductGadget
    @GET("products?id_product_category=")
    Call<AllProductResponse> getResultPropert();


//
//    @GET("dosen/{namadosen}")
//    Call<ResponseDosenDetail> getDetailDosen(@Path("namadosen") String namadosen);
//
//    @GET("matkul")
//    Call<ResponseMatkul> getSemuaMatkul();

//    @FormUrlEncoded
//    @POST("matkul")
//    Call<ResponseBody> simpanMatkulRequest(@Field("nama_dosen") String namadosen,
//                                           @Field("matkul") String namamatkul);
//
//    @DELETE("matkul/{idmatkul}")
//    Call<ResponseBody> deteleMatkul(@Path("idmatkul") String idmatkul);
}
