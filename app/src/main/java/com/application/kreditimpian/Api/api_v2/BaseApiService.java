package com.application.kreditimpian.Api.api_v2;



import com.application.kreditimpian.Model.ModelAddress.ResponseAddress;
import com.application.kreditimpian.Model.ModelGeodirectories.ResponseGeodirectories;
import com.application.kreditimpian.Model.ModelGeodirectory.ResponseGeodirectory;
import com.application.kreditimpian.Model.ModelListAlamat.ResponseListAlamat;
import com.application.kreditimpian.Model.ModelLogin.ResponseLogin;
import com.application.kreditimpian.Model.ModelMember.ResponseMember;
import com.application.kreditimpian.Model.ModelMemberInsert.ResponseMemberInsert;
import com.application.kreditimpian.Model.ModelMerchant.ResponseMerchant;
import com.application.kreditimpian.Model.ModelOnShoppingCart.ResponseOnShoppingCart;
import com.application.kreditimpian.Model.ModelProduct.ResponseProduct;
import com.application.kreditimpian.Model.ModelProductBaru.ResponseProductBaru;
import com.application.kreditimpian.Model.ModelProductNew.ProductResponse;


import com.application.kreditimpian.Model.ModelTransaction.ResponseTransaction;
import com.application.kreditimpian.Model.ModelUserDetail.ResponseMembers;
import com.application.kreditimpian.Model.ModelValidationSMS.ResponseSmsOTP;
import com.application.kreditimpian.ResponseMessage.ResponseLoginSucces;

import java.util.HashMap;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;


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


    /*API Login and Validation OTP*/
    @GET("ApiMobile/validasiotp")
    Call<ResponseSmsOTP> getValidation(@QueryMap HashMap<String, String> params);

    @GET("ApiMobile/loginproses")
    Call<ResponseLogin> getLogin(@QueryMap HashMap<String, String> params);

    /*Alamat APi*/
//    @GET("master/geodirectories?TYPE=DISTRICT")
//    Call<ResponseGeodirectories> getGeoDistrict();

//    @GET("master/geodirectories?TYPE=CITY")
//    Call<ResponseGeodirectories> getGeoCity();
    @GET("ApiMobile/getdistrict")
    Call<ResponseGeodirectory> getDistrict();

    @GET("ApiMobile/getcity")
    Call<ResponseGeodirectory> getCity();

    @GET("ApiMobile/isrtmbradrs")
    Call<ResponseAddress> InsertAddress(@QueryMap HashMap<String, String> params);

    @GET("ApiMobile/membersaddresses")
    Call<ResponseListAlamat> getAllAddesses(@Query("id_member") String id_member);

    @GET("ApiMobile/delmbradrs")
    Call<ResponseAddress> deleteAddreses(@Query("id") String id,
                                         @Query("id_member") String id_member);

    @GET("ApiMobile/updtmbradrs")
    Call<ResponseAddress> updateAddreses(@Query("id") String id,
                                         @QueryMap HashMap<String, String> params);

    @GET("ApiMobile/gantipassword")
    Call<ResponseAddress> ResetPassword(@Query("id_sysuser") String id_sysuser,@QueryMap HashMap<String, String> params);

    @GET("ApiMobile/insertprofile")
    Call<ResponseMemberInsert> InsertMember(@QueryMap HashMap<String, String> params);

    /*API Transaction*/
    @GET("ApiMobile/insrtonshoppingcart")
    Call<ResponseTransaction> InsertShoppingCart(@QueryMap HashMap<String, String> params);

    @GET("ApiMobile/getonshoppingcart")
    Call<ResponseOnShoppingCart> getOnShoppingCart(@Query("id_member") String id_member);
    //getMember
    ///@FormUrlEncoded
    @GET("members")
    Call<ResponseMembers> getMemberDetail();

    ///getMerchant
    //getAllProduct
    @GET("companies?id_company_category=1")
    Call<ResponseMerchant> getMerchnat();

    //getAllProduct
    @GET("products?status=PUBLISH")
    Call<ResponseProductBaru> getResult();

    //getAllProductFashion
    @GET("products?id_product_category=2&status=PUBLISH")
    Call<ResponseProductBaru> getResultFashion();

    //getAllProductForniture
    @GET("products?id_product_category=3&status=PUBLISH")
    Call<ResponseProductBaru> getResultForniture();

    //getAllProductOtomotif
    @GET("products?id_product_category=4&status=PUBLISH")
    Call<ResponseProductBaru> getResultOtomotif();

    //getAllProductMultiproduct
    @GET("products?id_product_category=5&status=PUBLISH")
    Call<ResponseProductBaru> getResultMultiproduct();

    //getAllProductKomputer
    @GET("products?id_product_category=11&status=PUBLISH")
    Call<ResponseProductBaru> getResultKomputer();

    //getAllProductGadget
    @GET("products?id_product_category=12&status=PUBLISH")
    Call<ResponseProductBaru> getResultGadget();

    //getAllProductElektronik
    @GET("products?id_product_category=15&status=PUBLISH")
    Call<ResponseProductBaru> getResultElektronik();

    //getAllProductHobi
    @GET("products?id_product_category=16&status=PUBLISH")
    Call<ResponseProductBaru> getResultHobi();

    //getAllProductCoorporate
    @GET("products?id_product_category=17&status=PUBLISH")
    Call<ResponseProductBaru> getResultCoorporate();

    //getAllProductHobi
    @GET("products?id_product_category=18&status=PUBLISH")
    Call<ResponseProductBaru> getResultProperty();

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

    @GET("getcompaniesmeta")
    Call<ResponseBody> getMitraUpgradeImpian();

    @FormUrlEncoded
    @POST("inserttransmultimotor")
    Call<ResponseBody> pengajuanMotor(
            @Field("id_member") String id_member,
            @Field("jml_pinjaman") String jml_pinjaman,
            @Field("hrg_kendaraan") String hrg_kendaraan,
            @Field("merk_kendaraan") String merk_kendaraan,
            @Field("tipe_kendaraan") String tipe_kendaraan,
            @Field("tahun_kendaraan") String tahun_kendaraan,
            @Field("lokasi") String lokasi,
            @Field("mitra_kredit") String mitra_kredit,
            @Field("img_bpkb") String img_bpkb
    );

    @FormUrlEncoded
    @POST("")
    Call<ResponseBody> pengajuanMobil(
            @Field("id_member") String id_member,
            @Field("jml_pinjaman") String jml_pinjaman,
            @Field("hrg_kendaraan") String hrg_kendaraan,
            @Field("merk_kendaraan") String merk_kendaraan,
            @Field("tipe_kendaraan") String tipe_kendaraan,
            @Field("tahun_kendaraan") String tahun_kendaraan,
            @Field("lokasi") String lokasi,
            @Field("mitra_kredit") String mitra_kredit,
            @Field("img_bpkb") String img_bpkb
    );

}
