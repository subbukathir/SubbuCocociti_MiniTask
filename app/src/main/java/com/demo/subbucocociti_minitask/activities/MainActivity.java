package com.demo.subbucocociti_minitask.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.demo.subbucocociti_minitask.R;
import com.demo.subbucocociti_minitask.adapter.Adapter_ListData;
import com.demo.subbucocociti_minitask.model.ShowCase;
import com.demo.subbucocociti_minitask.rest_api.ApiClient;
import com.demo.subbucocociti_minitask.rest_api.ApiInterface;
import com.demo.subbucocociti_minitask.utils.Apputils;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();

    private ProgressBar mProgressbar;
    private Toolbar mToolbar;
    private RecyclerView mRecyclerview;
    private LinearLayout ll_recyclerview;
    private Adapter_ListData mAdapter;

    private List<ShowCase> showCaseList = new ArrayList<>();

    private Bundle mArgs;
    private String mToken = "", mEmail="";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            mArgs = getIntent().getExtras();
            if(mArgs !=null){
                mToken = mArgs.getString(Apputils.ARG_TOKEN);
                mEmail = mArgs.getString(Apputils.ARG_EMAIL);
            }
            mProgressbar  = (ProgressBar) findViewById(R.id.progressbar);
            mRecyclerview = (RecyclerView) findViewById(R.id.recyclerview);
            ll_recyclerview = (LinearLayout) findViewById(R.id.ll_recyclerview);

            ll_recyclerview.setVisibility(View.GONE);
            mProgressbar.setVisibility(View.VISIBLE);

            if(!mToken.equalsIgnoreCase("")) getListData(mToken,mEmail);
            else Log.e(TAG,"token not found");
        }catch (Exception ex){
            ex.printStackTrace();
        }




    }

    @Override
    protected void onStart() {
        super.onStart();
        setUpActionBar();
    }

    private void setUpActionBar(){
        mToolbar =(Toolbar) findViewById(R.id.toolbar);
        getSupportActionBar().setTitle("User List");
        setSupportActionBar(mToolbar);
    }

    private void getListData(String token,String email){
        try {
            ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
            Call<List<ShowCase>> call = apiInterface.getListResult(mToken,mEmail);

            call.enqueue(new Callback<List<ShowCase>>() {
                @Override
                public void onResponse(Call<List<ShowCase>> call, Response<List<ShowCase>> response) {
                    Log.e(TAG,"onResponse");
                }

                @Override
                public void onFailure(Call<List<ShowCase>> call, Throwable t) {
                    Log.e(TAG,"onFailure");
                }
            });
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
