package com.application.kreditimpian.Api.network.interceptor;

import android.content.Intent;


import com.application.kreditimpian.Api.SharedPrefManager;
import com.application.kreditimpian.Api.api_v2.BaseApiService;
import com.application.kreditimpian.Api.api_v2.RetrofitClient;
import com.application.kreditimpian.LoginRegister.LoginUser;
import com.application.kreditimpian.ResponseMessage.ResponseLoginSucces;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class TokenAuthenticator implements Interceptor {

    SharedPrefManager sharedPrefManager;


    @Override
    public Response intercept(Chain chain) throws IOException {

        Request originalRequest = chain.request();

        Request newRequest = originalRequest.newBuilder()
                .header("Authorization ", sharedPrefManager.getSPToken())
                .build();

        return chain.proceed(newRequest);

    }
}


//    public TokenAuthenticator() {
//
//        sharedPrefManager = new SharedPrefManager(MyApp.getContext());
//    }

//
//    @Override
//    public Response intercept(Chain chain) throws IOException {
//        Request newRequest  = chain.request().newBuilder()
//                .addHeader("Authorization", "Bearer " + sharedPrefManager.getSPToken())
//                .build();
//        return chain.proceed(newRequest);
//    }



//    @Override
//    public Response intercept(Chain chain) throws IOException {
//        Request request = chain.request()
//                .newBuilder()
//                .addHeader("Authorization", SharedPrefManager.SP_TOKEN )
//                .build();
//        Response response = chain.proceed(request);
//        return response;
//    }


//    @Override
//    public Response intercept(Chain chain) throws IOException {
//        Response mainResponse = chain.proceed(chain.request());
//        Request mainRequest = chain.request();
//        //BaseApiService apiInterface = RetrofitClient.getClient().create(BaseApiService.class);
//
//        if ( mainResponse.code() == 401 || mainResponse.code() == 403 ) {
//            String token = sharedPrefManager.getSPToken();
//           // retrofit2.Response<ResponseLoginSucces> refreshToken = apiInterface.refreshToken(token).execute();
//            if (mainResponse.isSuccessful()) {
////                sharedPrefManager.saveSPString(SharedPrefManager.SP_TOKEN, "Bearer " +
////                                                mainResponse.body().string());
//                Request.Builder builder = mainRequest.newBuilder().header("Authorization",
//                        sharedPrefManager.getSPToken())
//                        .method(mainRequest.method(), mainRequest.body());
//                mainResponse = chain.proceed(builder.build());
//            }
//
////            ///Jika tidak ingin refresh token dan langsung logout
////            sharedPrefManager.saveSPBoolean(SharedPrefManager.SP_SUDAH_LOGIN, false);
////            Intent i = new Intent(MyApp.getContext(), LoginUser.class);
////            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
////            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
////            MyApp.getContext().startActivity(i);
//
//        } else if ( mainResponse.code() == 500 ){
//            sharedPrefManager.saveSPBoolean(SharedPrefManager.SP_SUDAH_LOGIN, false);
//            Intent i = new Intent(MyApp.getContext(), LoginUser.class);
//            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//            MyApp.getContext().startActivity(i);
//        }
//
//        return mainResponse;
//    }
//}
