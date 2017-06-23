package com.demo.subbucocociti_minitask.rest_api;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by subbu on 23/6/17.
 */

public class ApiClient {
    private static Retrofit retrofit=null;

    public static Retrofit getClient()
    {
        final OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .readTimeout(60, TimeUnit.SECONDS)
                .connectTimeout(60, TimeUnit.SECONDS)
                .build();

        if(retrofit == null)
        {
            retrofit = new Retrofit.Builder()
                    .baseUrl(ApiConstant.BASE_URL)
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

}
