package com.demo.subbucocociti_minitask.rest_api;

import com.demo.subbucocociti_minitask.request_data.RequestLogin;
import com.demo.subbucocociti_minitask.response_data.Response_ListData;
import com.demo.subbucocociti_minitask.response_data.Response_Login;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

import static com.demo.subbucocociti_minitask.rest_api.ApiConstant.LIST;

/**
 * Created by subbu on 23/6/17.
 */

public interface ApiInterface {


    @Headers({
            "Accept: application/json",
            "Content-Type: application/json"
    })
    @POST(ApiConstant.LOGIN)
    Call<Response_Login> getLoginResult(@Body RequestLogin loginData);

    @Headers({
            "Accept: application/json",
            "Content-Type: application/json"
    })
    @GET(LIST)
    Call<Response_ListData> getListResult(@Header("X-ACCESS-TOKEN") String token, @Header("X-USER-EMAIL") String email);
}
