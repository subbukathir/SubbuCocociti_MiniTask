package com.demo.subbucocociti_minitask.rest_api;

import com.demo.subbucocociti_minitask.model.ShowCase;
import com.demo.subbucocociti_minitask.request_data.RequestLogin;
import com.demo.subbucocociti_minitask.response_data.Response_Login;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

import static com.demo.subbucocociti_minitask.rest_api.ApiConstant.LIST;

/**
 * Created by subbu on 23/6/17.
 */

public interface ApiInterface {

    @POST(ApiConstant.LOGIN)
    Call<Response_Login> getLoginResult(@Body RequestLogin loginData);

    @GET(LIST)
    Call<List<ShowCase>> getListResult(@Header("X-ACCESS-TOKEN") String token,@Header("X-USER-EMAIL") String email);
}
