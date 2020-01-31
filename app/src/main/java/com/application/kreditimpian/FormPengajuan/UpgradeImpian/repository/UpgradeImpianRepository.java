package com.application.kreditimpian.FormPengajuan.UpgradeImpian.repository;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.application.kreditimpian.Api.api_v2.BaseApiService;
import com.application.kreditimpian.Model.ModelMitra;
import com.application.kreditimpian.Model.ModelUpgradeImpian;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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
    private Call<ResponseBody> responseBodyCall;
    private String responses;
    private JSONObject jsonObject;
    private JSONArray jsonArray;

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
                        if (jsonObject.getString("response_code").equals("200")){
                            jsonArray = new JSONArray(jsonObject.getString("data"));
                            Log.v("jajal", jsonArray.length()+"");
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
                } else{
                    Log.v("jajal", response.body()+" a");
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.v("jajal", t.getMessage()+" a");
            }
        });
        return arrayListMutableLiveData;
    }
    public LiveData<HashMap> pengajuanMotor(ModelUpgradeImpian modelUpgradeImpian){
        MutableLiveData<HashMap> hashMapMutableLiveData = new MutableLiveData<>();
        responseBodyCall = getApiMobile2().pengajuanMotor(
                modelUpgradeImpian.getIdmember(),
                modelUpgradeImpian.getJmlhpinjaman(),
                modelUpgradeImpian.getHrgkendaraan(),
                modelUpgradeImpian.getMerkkendaraan(),
                modelUpgradeImpian.getTipekendaraan(),
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

                    } catch (IOException | JSONException e) {
                        e.printStackTrace();
                    }
                } else{
                    Log.v("jajal", response.body()+" a");
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.v("jajal", t.getMessage()+" a");
            }
        });
        return hashMapMutableLiveData;
    }
}
