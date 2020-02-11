package com.application.kreditimpian.Model.ModelErrorMessage;

import com.application.kreditimpian.Api.api_v2.RetrofitClient;
import com.fasterxml.jackson.databind.util.Converter;

import java.io.IOException;
import java.lang.annotation.Annotation;

import okhttp3.ResponseBody;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by indriyanto Nugroho on 11 Feb 2020.
 */
public class ErrorUtils {


/*    public static APIError parseError(Response<?> response) throws IOException {
        Converter<ResponseBody, APIError> converter =
                Retrofit.retrofit()
                        .responseBodyConverter(APIError.class, new Annotation[0]);

        APIError error;


        try {
            error = converter.convert(response.errorBody());
        } catch (IOException e) {
            return new APIError();
        }

        return error;
    }*/

}
