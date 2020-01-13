package com.application.kreditimpian.Api.api_v2;



import androidx.viewpager.widget.PagerAdapter;


import com.application.kreditimpian.Api.SharedPrefManager;
import com.application.kreditimpian.Model.ModelMember.ResponseMember;
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
import retrofit2.http.HEAD;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

import static com.application.kreditimpian.Api.SharedPrefManager.SP_TOKEN;


public interface BaseApiService {

    // Fungsi ini untuk memanggil
    @Headers({
            "Accept: application/json",
            "x-rainbow-client: android",
            "x-rainbow-client-version: 1.10.7"
    })
    @FormUrlEncoded
    @POST("system/users/authenticate")
    Call<ResponseLoginSucces> loginRequest(@Field("username") String username,
                                           @Field("password") String password);

    //getAllProduct
    @GET("products?status=PUBLISH")
    Call<ProductResponse> getResult();

    //getAllProductFashion
    @GET("products?id_product_category=2&status=PUBLISH")
    Call<ProductResponse> getResultFashion();

    //getAllProductForniture
    @GET("products?id_product_category=3&status=PUBLISH")
    Call<ProductResponse> getResultForniture();

    //getAllProductOtomotif
    @GET("products?id_product_category=4&status=PUBLISH")
    Call<ProductResponse> getResultOtomotif();

    //getAllProductMultiproduct
    @GET("products?id_product_category=5&status=PUBLISH")
    Call<ResponseProduct> getResultMultiproduct();

    //getAllProductKomputer
    @GET("products?id_product_category=11&status=PUBLISH")
    Call<ProductResponse> getResultKomputer();

    //getAllProductGadget
    @GET("products?id_product_category=12&status=PUBLISH")
    Call<ProductResponse> getResultGadget();

    //getAllProductElektronik
    @GET("products?id_product_category=15&status=PUBLISH")
    Call<ProductResponse> getResultElektronik();

    //getAllProductHobi
    @GET("products?id_product_category=16&status=PUBLISH")
    Call<ProductResponse> getResultHobi();

    //getAllProductCoorporate
    @GET("products?id_product_category=17&status=PUBLISH")
    Call<ResponseProduct> getResultCoorporate();

    //getAllProductHobi
    @GET("products?id_product_category=18&status=PUBLISH")
    Call<ProductResponse> getResultProperty();

//    //getUserMember
//    @Headers({ "Content-Type: application/x-www-form-urlencoded", "Authorization: Bearer"+ SharedPrefManager.SP_TOKEN})
//    @POST("members")
//    Call<ResponseMember> postDataDiri(@Field("id") String id,
//                                      @Field("fullname") String fullname,
//                                      @Field("birthplace") String birthplace,
//                                      @Field("job") String job,
//                                      @Field("income") String income,
//                                      @Field("family_dependent") String family_dependent,
//                                      @Field("installment") String installment,
//                                      @Field("residence_status") String residence_status,
//                                      @Field("parent_name") String parent_name,
//                                      @Field("contact_office") String contact_office,
//                                      @Field("facebook") String facebook,
//                                      @Field("twitter") String twitter,
//                                      @Field("instagram") String instagram,
//                                      @Field("gender") String gender,
//                                      @Field("marital") String marital,
//                                      @Field("religion") String religion,
//                                      @Field("number_citizen") String number_citizen,
//                                      @Field("number_taxpayer") String number_taxpayer);

    //getUserMember
    ///@Headers({ "Content-Type: application/x-www-form-urlencoded", "Authorization: Bearer"+ SP_TOKEN})
    @Headers({
            "Accept: application/json"
    })
    ///@Header(@Header("Authorization") String "Bearer " + SP_TOKEN)
    @POST("members/update")
    @FormUrlEncoded
    Call<ResponseMember> postDataDiri(@Header("Authorization") String SP_TOKEN,
                                      @Query("fullname") String fullname,
                                      @Query("birthplace") String birthplace,
                                      @Query("birthday") String birthday,
                                      @Query("job") String job,
                                      @Query("income") String income,
                                      @Query("family_dependent") String family_dependent,
                                      @Query("installment") String installment,
                                      @Query("residence_status") String residence_status,
                                      @Query("parent_name") String parent_name,
                                      @Query("contact_office") String contact_office,
                                      @Query("facebook") String facebook,
                                      @Query("twitter") String twitter,
                                      @Query("instagram") String instagram,
                                      @Query("gender") String gender,
                                      @Query("marital") String marital,
                                      @Query("religion") String religion,
                                      @Query("number_citizen") String number_citizen,
                                      @Query("number_taxpayer") String number_taxpayer);


//    Call<ResponseMember> postDataDiri(@Field("fullname") String fullname,
//                                      @Field("birthplace") String birthplace,
//                                      @Field("birthday") String birthday,
//                                      @Field("job") String job,
//                                      @Field("income") String income,
//                                      @Field("family_dependent") String family_dependent,
//                                      @Field("installment") String installment,
//                                      @Field("residence_status") String residence_status,
//                                      @Field("parent_name") String parent_name,
//                                      @Field("contact_office") String contact_office,
//                                      @Field("facebook") String facebook,
//                                      @Field("twitter") String twitter,
//                                      @Field("instagram") String instagram,
//                                      @Field("gender") String gender,
//                                      @Field("marital") String marital,
//                                      @Field("religion") String religion,
//                                      @Field("number_citizen") String number_citizen,
//                                      @Field("number_taxpayer") String number_taxpayer);


    @GET("members?id={id}")
    Call<ResponseMember> getMember(@Path("id") String id,
                                    @Header("Authorization") String result);


}
