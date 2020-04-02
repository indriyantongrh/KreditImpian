package com.application.kreditimpian.FormPengajuan.UpgradeImpian.repository;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.application.kreditimpian.Api.api_v2.BaseApiService;
import com.application.kreditimpian.Model.Installment;
import com.application.kreditimpian.Model.ModelCicilan.CompaniesDataItem;
import com.application.kreditimpian.Model.ModelCicilan.DataCicilanItem;
import com.application.kreditimpian.Model.ModelMitra;
import com.application.kreditimpian.Model.ModelNotifikasi;
import com.application.kreditimpian.Model.ModelProductNew.Category;
import com.application.kreditimpian.Model.ModelUpgradeImpian.ModelMitraMultiguna;
import com.application.kreditimpian.Model.ModelUpgradeImpian.ModelMitraPinjaman;
import com.application.kreditimpian.Model.ModelUpgradeImpian.ModelPinjaman;
import com.application.kreditimpian.Model.ModelUpgradeImpian.ModelUpgradeImpian;
import com.application.kreditimpian.Model.Send;
import com.application.kreditimpian.Model.Shipping;
import com.google.gson.JsonArray;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.application.kreditimpian.Api.api_v2.UtilsApi.BASE_URL_API;

public class UpgradeImpianRepository {
    private Context context;
    private Call<ResponseBody> responseBodyCall;
    private ModelUpgradeImpian modelUpgradeImpian1;
    private ModelPinjaman modelPinjaman;
    private ModelMitraPinjaman modelMitra;

    private String responses;
    private JSONObject jsonObject, jsonObject1, jsonObject3;
    private JSONArray jsonArray, jsonArray1;
    private ArrayList<ModelUpgradeImpian> modelUpgradeImpians;
    private ArrayList<ModelMitraPinjaman> modelMitraArrayList;
    private ArrayList<ModelPinjaman> modelPinjamanArrayList;
    private ArrayList<Shipping> shippingItemArrayList;
    private ArrayList<Send> sendItemArrayList;
    private ArrayList<Installment> installmentItemArrayList;

    private MutableLiveData<ArrayList<ModelUpgradeImpian>> mutableLiveData;

    public UpgradeImpianRepository(Context context) {
        this.context = context;
    }

    private BaseApiService getApiMobile2() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL_API + "ApiMobile2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(BaseApiService.class);
    }

    public LiveData<ArrayList<ModelMitraMultiguna>> getMitraUpgrade() {
        MutableLiveData<ArrayList<ModelMitraMultiguna>> arrayListMutableLiveData = new MutableLiveData<>();
        responseBodyCall = getApiMobile2().getMitraUpgradeImpian();
        responseBodyCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.body() != null) {
                    try {
                        responses = response.body().string();
                        jsonObject = new JSONObject(responses);
                        ArrayList<ModelMitraMultiguna> modelMitras = new ArrayList<>();
                        if (jsonObject.getString("response_code").equals("200")) {
                            jsonArray = new JSONArray(jsonObject.getString("data"));
                            for (int i = 0; i < jsonArray.length(); i++) {
                                jsonObject = jsonArray.getJSONObject(i);
                                ModelMitraMultiguna modelMitra = new ModelMitraMultiguna();
                                modelMitra.setId(jsonObject.getString("id_company"));
                                modelMitra.setNama(jsonObject.getString("company_name"));
                                modelMitra.setChecked(false);
                                modelMitra.setDisable(true);
                                modelMitras.add(modelMitra);
                            }
                            arrayListMutableLiveData.setValue(modelMitras);
                        }
                    } catch (IOException | JSONException e) {
                        Toast.makeText(context, "Error JSON: " + e.getMessage(), Toast.LENGTH_LONG).show();
                        e.printStackTrace();
                    }
                } else {
                    Toast.makeText(context, "Error Response Body: " + response, Toast.LENGTH_LONG).show();
                    Log.v("jajal", response.body() + " a");
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(context, "Error retrofit: " + t.getMessage(), Toast.LENGTH_LONG).show();
                Log.v("jajal", t.getMessage() + " a");
            }
        });
        return arrayListMutableLiveData;
    }

    public LiveData<ArrayList<ModelUpgradeImpian>> pengajuanMotor(ModelUpgradeImpian modelUpgradeImpian) {
        mutableLiveData = new MutableLiveData<>();
        responseBodyCall = getApiMobile2().pengajuanMotor(
                modelUpgradeImpian.getIdmember(),
                modelUpgradeImpian.getJmlhpinjaman(),
                modelUpgradeImpian.getHrgkendaraan(),
                modelUpgradeImpian.getMerkkendaraan(),
                modelUpgradeImpian.getTipekendaraan(),
                modelUpgradeImpian.getTahun(),
                modelUpgradeImpian.getLokasi(),
                modelUpgradeImpian.getMitra(),
                modelUpgradeImpian.getImage()
        );
        responseBodyCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.body() != null) {
                    try {
                        responses = response.body().string();
                        jsonObject = new JSONObject(responses);
                        modelUpgradeImpians = new ArrayList<>();
                        if (jsonObject.getString("response_code").equals("200")) {
                            jsonObject = new JSONObject(jsonObject.getString("data"));
                            modelUpgradeImpian1 = new ModelUpgradeImpian();
                            modelUpgradeImpian1.setIdTransaksi(jsonObject.getString("id_transaksi"));
                            jsonObject3 = new JSONObject(jsonObject.getString("metadata_motor"));
                            modelUpgradeImpian1.setKendaraan(jsonObject3.getString("vehicles"));
                            modelUpgradeImpian1.setJmlhpinjaman(jsonObject3.getString("loan"));
                            modelUpgradeImpian1.setHrgkendaraan(jsonObject3.getString("vehicle_price"));
                            modelUpgradeImpian1.setMerkkendaraan(jsonObject3.getString("vehicle_brand"));
                            modelUpgradeImpian1.setTipekendaraan(jsonObject3.getString("vehicle_type"));
                            modelUpgradeImpian1.setTahun(jsonObject3.getString("vehicle_year"));

                            modelMitraArrayList = new ArrayList<>();
                            jsonArray = new JSONArray(jsonObject.getString("data_cicilan"));
                            for (int i = 0; i < jsonArray.length(); i++) {
                                jsonObject = jsonArray.getJSONObject(i);
                                modelMitra = new ModelMitraPinjaman();
                                modelMitra.setId(jsonObject.getString("id_company"));
                                modelMitra.setName(jsonObject.getString("company_name"));
                                modelMitra.setUrl(jsonObject.getString("company_logo"));

                                modelPinjamanArrayList = new ArrayList<>();
                                jsonArray1 = new JSONArray(jsonObject.getString("data_cicilan"));
                                for (int j = 0; j < jsonArray1.length(); j++) {
                                    jsonObject1 = jsonArray1.getJSONObject(j);
                                    modelPinjaman = new ModelPinjaman();
                                    modelPinjaman.setBulanTenor(jsonObject1.getString("bulan"));
                                    modelPinjaman.setHrgCicilan(jsonObject1.getString("cicilan"));
                                    modelPinjamanArrayList.add(modelPinjaman);
                                }
                                modelMitra.setModelPinjamanList(modelPinjamanArrayList);
                                modelMitraArrayList.add(modelMitra);
                            }

                            modelUpgradeImpian1.setModelMitraArrayList(modelMitraArrayList);
                            modelUpgradeImpians.add(modelUpgradeImpian1);
                        }
                        mutableLiveData.setValue(modelUpgradeImpians);
                    } catch (IOException | JSONException e) {
                        Toast.makeText(context, "Error JSON: " + e.getMessage(), Toast.LENGTH_LONG).show();
                        e.printStackTrace();
                    }
                } else {
                    Toast.makeText(context, "Error Response Body: " + response, Toast.LENGTH_LONG).show();
                    Log.v("jajal", response.body() + " a");
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(context, "Error retrofit: " + t.getMessage(), Toast.LENGTH_LONG).show();
                Log.v("jajal", t.getMessage() + " a");
            }
        });
        return mutableLiveData;
    }

    public LiveData<ArrayList<ModelUpgradeImpian>> pengajuanMobil(ModelUpgradeImpian modelUpgradeImpian) {
        mutableLiveData = new MutableLiveData<>();
        responseBodyCall = getApiMobile2().pengajuanMobil(
                modelUpgradeImpian.getIdmember(),
                modelUpgradeImpian.getJmlhpinjaman(),
                modelUpgradeImpian.getHrgkendaraan(),
                modelUpgradeImpian.getMerkkendaraan(),
                modelUpgradeImpian.getTipekendaraan(),
                modelUpgradeImpian.getTahun(),
                modelUpgradeImpian.getAsuransi(),
                modelUpgradeImpian.getLokasi(),
                modelUpgradeImpian.getMitra(),
                modelUpgradeImpian.getImage()
        );
        responseBodyCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.body() != null) {
                    try {
                        responses = response.body().string();
                        jsonObject = new JSONObject(responses);
                        modelUpgradeImpians = new ArrayList<>();
                        if (jsonObject.getString("response_code").equals("200")) {
                            jsonObject = new JSONObject(jsonObject.getString("data"));
                            modelUpgradeImpian1 = new ModelUpgradeImpian();
                            modelUpgradeImpian1.setIdTransaksi(jsonObject.getString("id_transaksi"));
                            jsonObject3 = new JSONObject(jsonObject.getString("metadata_mobil"));
                            modelUpgradeImpian1.setKendaraan(jsonObject3.getString("vehicles"));
                            modelUpgradeImpian1.setJmlhpinjaman(jsonObject3.getString("loan"));
                            modelUpgradeImpian1.setHrgkendaraan(jsonObject3.getString("vehicle_price"));
                            modelUpgradeImpian1.setMerkkendaraan(jsonObject3.getString("vehicle_brand"));
                            modelUpgradeImpian1.setTipekendaraan(jsonObject3.getString("vehicle_type"));
                            modelUpgradeImpian1.setAsuransi(jsonObject3.getString("insurance"));
                            modelUpgradeImpian1.setTahun(jsonObject3.getString("vehicle_year"));

                            modelMitraArrayList = new ArrayList<>();
                            jsonArray = new JSONArray(jsonObject.getString("data_cicilan"));
                            for (int i = 0; i < jsonArray.length(); i++) {
                                jsonObject = jsonArray.getJSONObject(i);
                                modelMitra = new ModelMitraPinjaman();
                                modelMitra.setId(jsonObject.getString("id_company"));
                                modelMitra.setName(jsonObject.getString("company_name"));
                                modelMitra.setUrl(jsonObject.getString("company_logo"));

                                modelPinjamanArrayList = new ArrayList<>();
                                jsonArray1 = new JSONArray(jsonObject.getString("data_cicilan"));
                                for (int j = 0; j < jsonArray1.length(); j++) {
                                    jsonObject1 = jsonArray1.getJSONObject(j);
                                    modelPinjaman = new ModelPinjaman();
                                    modelPinjaman.setBulanTenor(jsonObject1.getString("bulan"));
                                    modelPinjaman.setHrgCicilan(jsonObject1.getString("cicilan"));
                                    modelPinjamanArrayList.add(modelPinjaman);
                                }
                                modelMitra.setModelPinjamanList(modelPinjamanArrayList);
                                modelMitraArrayList.add(modelMitra);
                            }

                            modelUpgradeImpian1.setModelMitraArrayList(modelMitraArrayList);
                            modelUpgradeImpians.add(modelUpgradeImpian1);
                        }
                        mutableLiveData.setValue(modelUpgradeImpians);
                    } catch (IOException | JSONException e) {
                        Toast.makeText(context, "Error JSON: " + e.getMessage(), Toast.LENGTH_LONG).show();
                        Log.v("jajal", e.getMessage());
                        e.printStackTrace();
                    }
                } else {
                    Toast.makeText(context, "Error Response Body: " + response, Toast.LENGTH_LONG).show();
                    Log.v("jajal", response.body() + " a");
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(context, "Error retrofit: " + t.getMessage(), Toast.LENGTH_LONG).show();
                Log.v("jajal", t.getMessage() + " a");
            }
        });
        return mutableLiveData;
    }

    public LiveData<ArrayList<ModelUpgradeImpian>> pilihLeasingg(ModelUpgradeImpian modelUpgradeImpian) {
        mutableLiveData = new MutableLiveData<>();
        responseBodyCall = getApiMobile2().pilihLeasingPinjaman(
                modelUpgradeImpian.getIdmember(),
                modelUpgradeImpian.getIdTransaksi(),
                modelUpgradeImpian.getTahun(),
                modelUpgradeImpian.getMitra(),
                modelUpgradeImpian.getJmlhpinjaman()
        );
        responseBodyCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.body() != null) {
                    try {
                        responses = response.body().string();
                        jsonObject = new JSONObject(responses);
                        modelUpgradeImpians = new ArrayList<>();
                        modelUpgradeImpian1 = new ModelUpgradeImpian();
                        modelUpgradeImpian1.setCode(jsonObject.getString("response_code"));
                        modelUpgradeImpian1.setResult(jsonObject.getString("message"));
                        modelUpgradeImpians.add(modelUpgradeImpian1);
                        mutableLiveData.setValue(modelUpgradeImpians);
                    } catch (IOException | JSONException e) {
                        Toast.makeText(context, "Error JSON: " + e.getMessage(), Toast.LENGTH_LONG).show();
                        Log.v("jajal", e.getMessage());
                        e.printStackTrace();
                    }
                } else {
                    Toast.makeText(context, "Error Response Body: " + response, Toast.LENGTH_LONG).show();
                    Log.v("jajal", response.body() + " a");
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.v("jajal", t.getMessage() + " a");
            }
        });
        return mutableLiveData;
    }

    public LiveData<ArrayList<Category>> getKategoriFotoImpian() {
        MutableLiveData<ArrayList<Category>> mutableLiveData = new MutableLiveData<>();
        responseBodyCall = getApiMobile2().getKategoriFotoImpian();
        responseBodyCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.body() != null) {
                    try {
                        responses = response.body().string();
                        jsonObject = new JSONObject(responses);
                        ArrayList<Category> categories = new ArrayList<>();
                        if (jsonObject.getString("response_code").equals("200")) {
                            jsonArray = new JSONArray(jsonObject.getString("data"));
                            for (int i = 0; i < jsonArray.length(); i++) {
                                jsonObject = jsonArray.getJSONObject(i);
                                Category category = new Category();
                                category.setId(jsonObject.getString("id_category"));
                                category.setName(jsonObject.getString("name"));
                                categories.add(category);
                            }
                        }
                        mutableLiveData.setValue(categories);
                    } catch (IOException | JSONException e) {
                        Toast.makeText(context, "Error JSON: " + e.getMessage(), Toast.LENGTH_LONG).show();
                        Log.v("jajal", e.getMessage());
                        e.printStackTrace();
                    }
                } else {
                    Toast.makeText(context, "Error Response Body: " + response, Toast.LENGTH_LONG).show();
                    Log.v("jajal", response.body() + " a");
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(context, "Error retrofit: " + t.getMessage(), Toast.LENGTH_LONG).show();
                Log.v("jajal", t.getMessage() + " a");
            }
        });
        return mutableLiveData;
    }

    public LiveData<String> uploadFotoImpian(Category category) {
        MutableLiveData<String> mutableLiveData = new MutableLiveData<>();
        responseBodyCall = getApiMobile2().fotoImpian(
                category.getIdParent(),
                category.getName(),
                category.getId(),
                category.getSlug(),
                String.valueOf(category.getDescription()),
                category.getImage()
        );
        responseBodyCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.body() != null) {
                    try {
                        responses = response.body().string();
                        jsonObject = new JSONObject(responses);
                        mutableLiveData.setValue(jsonObject.getString("response_code"));
                    } catch (IOException | JSONException e) {
                        Toast.makeText(context, "Error JSON: " + e.getMessage(), Toast.LENGTH_LONG).show();
                        Log.v("jajal", e.getMessage());
                        e.printStackTrace();
                    }
                } else {
                    Toast.makeText(context, "Error Response Body: " + response, Toast.LENGTH_LONG).show();
                    Log.v("jajal", response.body() + " a");
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(context, "Error retrofit: " + t.getMessage(), Toast.LENGTH_LONG).show();
                Log.v("jajal", t.getMessage() + " a");
            }
        });
        return mutableLiveData;
    }
    public LiveData<HashMap> getNotifikasi(ModelNotifikasi modelNotifikasi) {
        MutableLiveData<HashMap> mutableLiveData = new MutableLiveData<>();
        responseBodyCall = getApiMobile2().getnotifikasi(
                modelNotifikasi.getIdMember()
        );
        responseBodyCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.body() != null) {
                    try {
                        responses = response.body().string();
                        jsonObject = new JSONObject(responses);
                        HashMap<String, Object> hashMap = new HashMap<>();
                        hashMap.put("code", jsonObject.getString("response_code"));
                        Log.v("jajalNotifikasi", jsonObject+"");
                        if (jsonObject.getString("response_code").equals("200")) {
                            ArrayList<ModelNotifikasi> modelNotifikasiArrayList = new ArrayList<>();
//                            Object json = new JSONTokener(jsonObject.getString("data")).nextValue();
                            if (!jsonObject.getString("data").equals("null")) {
                                jsonArray = new JSONArray(jsonObject.getString("data"));
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    jsonObject = jsonArray.getJSONObject(i);
                                    ModelNotifikasi modelNotifikasi1 = new ModelNotifikasi();
                                    modelNotifikasi1.setIdNotifikasi(jsonObject.getString("id"));
                                    modelNotifikasi1.setMessage(jsonObject.getString("message"));
                                    modelNotifikasi1.setStatus(jsonObject.getString("status"));
                                    modelNotifikasi1.setMetadata(jsonObject.getString("metadata"));                                    modelNotifikasi1.setStatus(jsonObject.getString("status"));
                                    modelNotifikasi1.setTgl(jsonObject.getString("date"));
                                    modelNotifikasi1.setNumber(jsonObject.getString("number"));
                                    modelNotifikasi1.setId_product(jsonObject.getString("id_product"));
                                    modelNotifikasi1.setId_product_category(jsonObject.getString("id_product_category"));
                                    modelNotifikasi1.setName(jsonObject.getString("name"));
                                    modelNotifikasi1.setPrice_capital(jsonObject.getString("price_capital"));
                                    modelNotifikasi1.setPrice_sale(jsonObject.getString("price_sale"));
                                    modelNotifikasi1.setFilename(jsonObject.getString("filename"));
                                    modelNotifikasi1.setName_merchant(jsonObject.getString("name_merchant"));
                                    modelNotifikasi1.setTenor(jsonObject.getString("tenor"));
                                    modelNotifikasi1.setDown_payment(jsonObject.getString("down_payment"));
                                    modelNotifikasi1.setNote(jsonObject.getString("note"));
                                    modelNotifikasi1.setPostal_fee(jsonObject.getString("postal_fee"));
                                    modelNotifikasi1.setName_company(jsonObject.getString("name_company"));
                                    modelNotifikasi1.setName_city(jsonObject.getString("name_city"));
                                    modelNotifikasi1.setName_district(jsonObject.getString("name_district"));
                                    modelNotifikasi1.setPayment_method(jsonObject.getString("payment_method"));
                                    modelNotifikasi1.setTotal_pembayaran(jsonObject.getString("total_pembayaran"));
                                    modelNotifikasi1.setCourier(jsonObject.getString("courier"));

                                    //get Object Instalment
                                    JSONObject getInstallment = jsonObject.getJSONObject("installment");
                                    Installment modelInstallment = new Installment();
                                    modelInstallment.setJsonMember0(getInstallment.getString("0"));
                                    modelNotifikasi1.setInstallment(modelInstallment); // jajal di run
                                    //wait
                                    Log.v("jajalInstallment", getInstallment.getString("0")+"");


                                    ///getAddreess send
                                    JSONObject getship = jsonObject.getJSONObject("shipping");
                                    getship = getship.getJSONObject("send");
                                    Send send = new Send();
                                    send.setCity(getship.getString("city"));
                                    send.setAddressLabel(getship.getString("address_label"));
                                    send.setReceiver(getship.getString("receiver"));
                                    send.setMobile(getship.getString("mobile"));
                                    send.setCity(getship.getString("city"));
                                    send.setDistrict(getship.getString("district"));
                                    send.setAddress(getship.getString("address"));
                                    send.setPostalCode(getship.getString("postal_code"));
                                    modelNotifikasi1.setSend(send);
                                    Log.v("jajalSend", getship.getString("city")+"");


                                    modelNotifikasiArrayList.add(modelNotifikasi1);
                                }
                                hashMap.put("list", modelNotifikasiArrayList);
                            }
                        }
                        mutableLiveData.setValue(hashMap);

                    } catch (IOException | JSONException e) {
                        Toast.makeText(context, "Error JSON: " + e.getMessage(), Toast.LENGTH_LONG).show();
                        Log.v("jajal", e.getMessage());
                        e.printStackTrace();
                    }
                } else {
                    Toast.makeText(context, "Error Response Body: " + response, Toast.LENGTH_LONG).show();
                    Log.v("jajal", response.body() + " a");
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(context, "Error retrofit: " + t.getMessage(), Toast.LENGTH_LONG).show();
                Log.v("jajal", t.getMessage() + " a");
            }
        });
        return mutableLiveData;
    }

    public LiveData<String> updateNotifikasi(ModelNotifikasi modelNotifikasi) {
        MutableLiveData<String> mutableLiveData = new MutableLiveData<>();
        responseBodyCall = getApiMobile2().updateSeen(
                modelNotifikasi.getIdNotifikasi()
        );
        responseBodyCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.body() != null) {
                    try {
                        responses = response.body().string();
                        jsonObject = new JSONObject(responses);

                        mutableLiveData.setValue(jsonObject.getString("response_code"));


                    } catch (IOException | JSONException e) {
                        Toast.makeText(context, "Error JSON: " + e.getMessage(), Toast.LENGTH_LONG).show();
                        Log.v("jajal", e.getMessage());
                        e.printStackTrace();
                    }
                } else {
                    Toast.makeText(context, "Error Response Body: " + response, Toast.LENGTH_LONG).show();
                    Log.v("jajal", response.body() + " a");
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(context, "Error retrofit: " + t.getMessage(), Toast.LENGTH_LONG).show();
                Log.v("jajal", t.getMessage() + " a");
            }
        });
        return mutableLiveData;
    }
}
