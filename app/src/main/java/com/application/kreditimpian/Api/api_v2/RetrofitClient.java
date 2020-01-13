package com.application.kreditimpian.Api.api_v2;

import android.content.Context;
import android.content.SharedPreferences;

import com.application.kreditimpian.Api.SharedPrefManager;
import com.application.kreditimpian.Api.network.interceptor.TokenAuthenticator;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.application.kreditimpian.Api.SharedPrefManager.SP_TOKEN;


public class RetrofitClient {

//    private static OkHttpClient client = new OkHttpClient();
//    private static Retrofit retrofit = null;
//
//    public static Retrofit getClient(String baseUrl){
//        TokenAuthenticator interceptor = new TokenAuthenticator();
//        OkHttpClient client = new OkHttpClient.Builder()
//                .addInterceptor(interceptor)
//                .build();
//
//        if (retrofit == null){
//            retrofit = new Retrofit.Builder()
//                    .baseUrl(baseUrl)
//                    .addConverterFactory(GsonConverterFactory.create())
//                    .client(client)
//                    .build();
//        }
//        return retrofit;
//    }


//    private static OkHttpClient client = new OkHttpClient();
//    private static Retrofit retrofit = null;
//
//    public static Retrofit getClient(Context context, String baseUrl){
//        TokenAuthenticator interceptor = new TokenAuthenticator(context);
//        OkHttpClient client = new OkHttpClient.Builder()
//                .addInterceptor(interceptor)
//                .build();
//
//        if (retrofit == null){
//            retrofit = new Retrofit.Builder()
//                    .baseUrl(baseUrl)
//                    .addConverterFactory(GsonConverterFactory.create())
//                    .client(client)
//                    .build();
//        }
//        return retrofit;
//    }



    SharedPrefManager sharedPrefManager;
    private static Retrofit retrofit = null;

    public static Retrofit getClient(String baseUrl){

        OkHttpClient.Builder okhttpBuilder = new OkHttpClient.Builder();
        okhttpBuilder.addInterceptor(new Interceptor() {
            @NotNull
            @Override
            public Response intercept(@NotNull Chain chain) throws IOException {

                    Request request = chain.request();
                    Request.Builder newRequest = request.newBuilder()
                                                .addHeader("Authorization","Bearer " + SP_TOKEN);

                    return chain.proceed(newRequest.build());

            }
        });



        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder()
                                    .addInterceptor(interceptor)
                                    //.addInterceptor(new TokenAuthenticator(ontext))
                                    .build();

        if (retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(okhttpBuilder.build())
                    .build();
        }
        return retrofit;
    }
}
