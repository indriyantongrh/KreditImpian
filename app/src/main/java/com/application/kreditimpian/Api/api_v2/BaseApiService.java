package com.application.kreditimpian.Api.api_v2;


import com.application.kreditimpian.Model.ModelAddress.ResponseAddress;
import com.application.kreditimpian.Model.ModelDetailMember.ResponseDetailMember;
import com.application.kreditimpian.Model.ModelFOrgotPassword.ResponseForgotPassword;
import com.application.kreditimpian.Model.ModelGeodirectory.ResponseGeodirectory;
import com.application.kreditimpian.Model.ModelKecamatan.ResponseKecamatan;
import com.application.kreditimpian.Model.ModelKotaKecamatan.ResponseKotaKecamatan;
import com.application.kreditimpian.Model.ModelListAlamat.ResponseListAlamat;
import com.application.kreditimpian.Model.ModelLogin.ResponseLogin;
import com.application.kreditimpian.Model.ModelMember.ResponseMember;
import com.application.kreditimpian.Model.ModelMemberInsert.ResponseMemberInsert;
import com.application.kreditimpian.Model.ModelMerchant.ResponseMerchant;
import com.application.kreditimpian.Model.ModelMitraSelected.ResponseMitraSelected;
import com.application.kreditimpian.Model.ModelOnShoppingCart.ResponseOnShoppingCart;
import com.application.kreditimpian.Model.ModelOngkoskirim.ResponseOngkir;
import com.application.kreditimpian.Model.ModelPengajuanCatalog.ResponsePengajuanCatalog;
import com.application.kreditimpian.Model.ModelProductBaru.ResponseProductBaru;


import com.application.kreditimpian.Model.ModelProductRevisi.ResponseProductRevisi;
import com.application.kreditimpian.Model.ModelSelectedTenor.ResponseSelectedTenor;
import com.application.kreditimpian.Model.ModelTransaction.ResponseTransaction;
import com.application.kreditimpian.Model.ModelUploadImage.ResponseUploadImage;
import com.application.kreditimpian.Model.ModelUserDetail.ResponseMembers;
import com.application.kreditimpian.Model.ModelValidationSMS.ResponseSmsOTP;
import com.application.kreditimpian.Model.ResponseRegisterBaru.NewResponseRegister;
import com.application.kreditimpian.ResponseMessage.ResponseLoginSucces;
import com.application.kreditimpian.ResponseMessage.ResponseRegister;

import java.util.HashMap;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
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

    @GET("ApiMobile/getkecamatan")
    Call<ResponseKecamatan> getKecamatan(@QueryMap HashMap<String, String> params);


    @FormUrlEncoded
    @POST("forgot-password")
    Call<ResponseForgotPassword> ForgotPassword(@Field("username") String username);

    @GET("ApiMobile/isrtmbradrs")
    Call<ResponseAddress> InsertAddress(@QueryMap HashMap<String, String> params);

    @GET("ApiMobile/getgeodirectorymemberaddress")
    Call<ResponseKotaKecamatan> getKotaKecamatan(@QueryMap HashMap<String, String> params);

    @GET("ApiMobile/membersaddresses")
    Call<ResponseListAlamat> getAllAddesses(@Query("id_member") String id_member);

    @GET("ApiMobile/delmbradrs")
    Call<ResponseAddress> deleteAddreses(@Query("id") String id,
                                         @Query("id_member") String id_member);

    @GET("ApiMobile/updtmbradrs")
    Call<ResponseAddress> updateAddreses(@Query("id") String id,
                                         @QueryMap HashMap<String, String> params);

    @GET("ApiMobile/gantipassword")
    Call<ResponseAddress> ResetPassword(@Query("id_sysuser") String id_sysuser, @QueryMap HashMap<String, String> params);

     @FormUrlEncoded
    @POST("ApiMobile/insertprofile")
    Call<ResponseMemberInsert> InsertMember(@FieldMap HashMap<String, String> params);

    @FormUrlEncoded
    @POST("ApiMobile/uploadavatar")
    Call<ResponseUploadImage> UploadFoto(@FieldMap HashMap<String, String> params);

    @FormUrlEncoded
    @POST("ApiMobile/uploadcitizen")
    Call<ResponseUploadImage> UploadKtp(@FieldMap HashMap<String, String> params);

    @FormUrlEncoded
    @POST("ApiMobile/uploadtaxpayer")
    Call<ResponseUploadImage> UploadNpwp(@FieldMap HashMap<String, String> params);

    /*API Transaction*/
    @GET("ApiMobile/getdataprofile")
    Call<ResponseDetailMember> getDetailMember(@QueryMap HashMap<String, String> params);

    /*API Transaction*/
    @GET("ApiMobile/insrtonshoppingcart")
    Call<ResponseTransaction> InsertShoppingCart(@QueryMap HashMap<String, String> params);

    @GET("ApiMobile/getonshoppingcart")
    Call<ResponseOnShoppingCart> getOnShoppingCart(@Query("id_member") String id_member);

    @GET("ApiMobile/deleteonspcrt")
    Call<ResponseOnShoppingCart> deleteCart(@Query("number") String number);

    @GET("ApiMobile/tenor")
    Call<ResponseMitraSelected> getMitraSelected(@Query("id_product_category") String id_product_category);

    /*API GET ONGKOS KIRIM*/
    @GET("ApiMobile/apiheadRajaongkir")
    Call<ResponseOngkir> getOngkir(@QueryMap HashMap<String, String> params);

    @GET("ApiMobile/getpaymentdata")
    Call<ResponseSelectedTenor> getTenorSelected(@Query("id_product") String id_product,
                                                 @Query("id_product_category") String id_product_category,
                                                 @Query("id_company") String id_company);

    @FormUrlEncoded
    @POST("ApiMobile/getTenorByCompany")
    Call<ResponseBody> getCicilanProduct(@FieldMap HashMap<String, String> params);

    @FormUrlEncoded
    @POST("ApiMobile/insertMetaDataProductTransaction")
    Call<ResponsePengajuanCatalog> postPengajuan(@FieldMap HashMap<String, String> params);

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

/*    //getAllProduct
    @GET("products?status=PUBLISH")
    Call<ResponseProductRevisi> getResult();*/

    //getAllProductFashion
    @GET("products?id_product_category=19&status=PUBLISH")
    Call<ResponseProductBaru> getResultFashion();

    //getAllProductForniture
    @GET("products?id_product_category=20&status=PUBLISH")
    Call<ResponseProductBaru> getResultForniture();

    //getAllProductOtomotif
    @GET("products?id_product_category=27&status=PUBLISH")
    Call<ResponseProductBaru> getResultOtomotif();

    //getAllProductMultiproduct
    @GET("products?id_product_category=25&status=PUBLISH")
    Call<ResponseProductBaru> getResultMultiproduct();

    //getAllProductKomputer
    @GET("products?id_product_category=35&status=PUBLISH")
    Call<ResponseProductBaru> getResultKomputer();

    //getAllProductGadget
    @GET("products?id_product_category=36&status=PUBLISH")
    Call<ResponseProductBaru> getResultGadget();

    //getAllProductElektronik
    @GET("products?id_product_category=33&status=PUBLISH")
    Call<ResponseProductBaru> getResultElektronik();

    //getAllProductHobi
    @GET("products?id_product_category=22&status=PUBLISH")
    Call<ResponseProductBaru> getResultHobi();

    //getAllProductCoorporate
    @GET("products?id_product_category=17&status=PUBLISH")
    Call<ResponseProductBaru> getResultCoorporate();

    //getAllProductHobi
    @GET("products?id_product_category=29&status=PUBLISH")
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
    @POST("inserttransmultimobil")
    Call<ResponseBody> pengajuanMobil(
            @Field("id_member") String id_member,
            @Field("jml_pinjaman") String jml_pinjaman,
            @Field("hrg_kendaraan") String hrg_kendaraan,
            @Field("merk_kendaraan") String merk_kendaraan,
            @Field("tipe_kendaraan") String tipe_kendaraan,
            @Field("tahun_kendaraan") String tahun_kendaraan,
            @Field("asuransi") String asuransi,
            @Field("lokasi") String lokasi,
            @Field("mitra_kredit") String mitra_kredit,
            @Field("img_bpkb") String img_bpkb
    );

    @GET("insertlogtransmulti")
    Call<ResponseBody> pilihLeasingPinjaman(
            @Query("id_member") String id_member,
            @Query("id_transaksi") String id_transaksi,
            @Query("tenor") String tenor,
            @Query("id_kreditor") String id_kreditor,
            @Query("cicilan") String cicilan
    );

    @GET("getallproductcategories")
    Call<ResponseBody> getKategoriFotoImpian();

    @FormUrlEncoded
    @POST("instransfotoimpian")
    Call<ResponseBody> fotoImpian(
            @Field("id_member") String id_member,
            @Field("product_name") String product_name,
            @Field("id_category") String id_category,
            @Field("sumber_pesanan") String sumber_pesanan,
            @Field("description") String description,
            @Field("product_img") String product_img
    );

    @FormUrlEncoded
    @POST("getNotification")
    Call<ResponseBody> getnotifikasi(
            @Field("id_member") String id_member
    );

    @FormUrlEncoded
    @POST("updateSeennotification")
    Call<ResponseBody> updateSeen(
            @Field("id_notification") String id_notification
    );

    @FormUrlEncoded
    @POST("ApiMobile2/register")
    Call<NewResponseRegister> registerMember (@Field("username") String username,
                                          @Field("email") String email,
                                          @Field("phone") String phone,
                                          @Field("password") String password,
                                          @Field("password_confirm") String password_confirm);
}
