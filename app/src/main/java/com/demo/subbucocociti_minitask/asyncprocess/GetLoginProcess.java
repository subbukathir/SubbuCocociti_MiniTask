package com.demo.subbucocociti_minitask.asyncprocess;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.demo.subbucocociti_minitask.listeners.Login_Listener;
import com.demo.subbucocociti_minitask.model.User;
import com.demo.subbucocociti_minitask.request_data.RequestLogin;
import com.demo.subbucocociti_minitask.response_data.Response_Login;
import com.demo.subbucocociti_minitask.rest_api.ApiClient;
import com.demo.subbucocociti_minitask.rest_api.ApiConstant;
import com.demo.subbucocociti_minitask.rest_api.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by vikram on 22/6/17.
 */

public class GetLoginProcess extends AsyncTask {
    private static final String TAG = GetLoginProcess.class.getSimpleName();

    private AppCompatActivity mActivity;
    private String strEmail,strPwd;
    private Login_Listener mCallback;

    private ApiInterface mInterface;
    private Response_Login mResponse;
    public GetLoginProcess(AppCompatActivity mActivity,String email,String pwd){
        this.mActivity = mActivity;
        this.strEmail = email;
        this.strPwd = pwd;
        mInterface = ApiClient.getClient().create(ApiInterface.class);
    }

    public void setListener(Login_Listener listener){
        this.mCallback = listener;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected Object doInBackground(Object[] objects) {
        try {
            Call<Response_Login> call = mInterface.getLoginResult(new RequestLogin(new User(strEmail,strPwd)));

            String value = new RequestLogin(new User(strEmail,strPwd)).toString();
            Log.e(TAG," request type " + value);
            call.enqueue(new Callback<Response_Login>() {
                @Override
                public void onResponse(Call<Response_Login> call, Response<Response_Login> response) {
                    if(response.isSuccessful()){
                        mResponse = response.body();
                        if(mResponse.getStatus().equalsIgnoreCase(ApiConstant.SUCCESS)) mCallback.onLoginDataReceivedSuccess(mResponse);
                        else mCallback.onLoginDataReceivedError(mResponse.getInfo());

                    }else {
                        mCallback.onLoginDataReceivedError(response.errorBody().toString());
                    }
                }

                @Override
                public void onFailure(Call<Response_Login> call, Throwable t) {
                    Log.e(TAG,"onFailure");
                    mCallback.onLoginDataReceivedError(t.toString());

                }
            });
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return null;
    }
}
