package com.application.kreditimpian.FormPengajuan.UpgradeImpian.repository;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.application.kreditimpian.Api.api_v2.BaseApiService;
import com.application.kreditimpian.Model.ModelMitra;
import com.application.kreditimpian.Model.ModelUpgradeImpian.ModelMitraPinjaman;
import com.application.kreditimpian.Model.ModelUpgradeImpian.ModelPinjaman;
import com.application.kreditimpian.Model.ModelUpgradeImpian.ModelUpgradeImpian;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.application.kreditimpian.Api.api_v2.UtilsApi.BASE_URL_API;

public class UpgradeImpianRepository {
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
    private MutableLiveData<ArrayList<ModelUpgradeImpian>> mutableLiveData;

    private BaseApiService getApiMobile2() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL_API + "ApiMobile2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(BaseApiService.class);
    }

    public LiveData<ArrayList<ModelMitra>> getMitraUpgrade() {
        MutableLiveData<ArrayList<ModelMitra>> arrayListMutableLiveData = new MutableLiveData<>();
        responseBodyCall = getApiMobile2().getMitraUpgradeImpian();
        responseBodyCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.body() != null) {
                    try {
                        responses = response.body().string();
                        jsonObject = new JSONObject(responses);
                        ArrayList<ModelMitra> modelMitras = new ArrayList<>();
                        if (jsonObject.getString("response_code").equals("200")) {
                            jsonArray = new JSONArray(jsonObject.getString("data"));
                            for (int i = 0; i < jsonArray.length(); i++) {
                                jsonObject = jsonArray.getJSONObject(i);
                                ModelMitra modelMitra = new ModelMitra();
                                modelMitra.setId(jsonObject.getString("id_company"));
                                modelMitra.setName(jsonObject.getString("company_name"));
                                modelMitra.setChecked(false);
                                modelMitras.add(modelMitra);
                            }
                            arrayListMutableLiveData.setValue(modelMitras);
                        }
                    } catch (IOException | JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    Log.v("jajal", response.body() + " a");
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
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
                        e.printStackTrace();
                    }
                } else {
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
                        Log.v("jajal", e.getMessage());
                        e.printStackTrace();
                    }
                } else {
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
                        Log.v("jajal", e.getMessage());
                        e.printStackTrace();
                    }
                } else {
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
}
