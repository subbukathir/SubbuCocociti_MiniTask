package com.demo.subbucocociti_minitask.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.demo.subbucocociti_minitask.R;
import com.demo.subbucocociti_minitask.adapter.Adapter_ListData;
import com.demo.subbucocociti_minitask.model.ShowCase;
import com.demo.subbucocociti_minitask.response_data.Response_ListData;
import com.demo.subbucocociti_minitask.rest_api.ApiClient;
import com.demo.subbucocociti_minitask.rest_api.ApiConstant;
import com.demo.subbucocociti_minitask.rest_api.ApiInterface;
import com.demo.subbucocociti_minitask.utils.Apputils;
import com.demo.subbucocociti_minitask.utils.SimpleItemDivider;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
/**
 * Created by subbu on 23/6/17.
 */
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
    private Response_ListData mResponseData = new Response_ListData();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            mArgs = getIntent().getExtras().getBundle(Apputils.ARG_BUNDLE);
            if(mArgs !=null){
                mToken = mArgs.getString(Apputils.ARG_TOKEN);
                mEmail = mArgs.getString(Apputils.ARG_EMAIL);
            }
            mProgressbar  = (ProgressBar) findViewById(R.id.progressbar);
            mRecyclerview = (RecyclerView) findViewById(R.id.recyclerview);
            ll_recyclerview = (LinearLayout) findViewById(R.id.ll_recyclerview);

            mRecyclerview.setItemAnimator(new DefaultItemAnimator());
            mRecyclerview.addItemDecoration(new SimpleItemDivider(this,LinearLayoutManager.VERTICAL));
            LinearLayoutManager layoutManager = new LinearLayoutManager(MainActivity.this,LinearLayoutManager.VERTICAL,false);
            mRecyclerview.setLayoutManager(layoutManager);

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
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle(R.string.lbl_list);
    }

    private void getListData(String token,String email){
        try {
            ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
            Call<Response_ListData> call = apiInterface.getListResult(mToken,mEmail);

            call.enqueue(new Callback<Response_ListData>() {
                @Override
                public void onResponse(Call<Response_ListData> call, Response<Response_ListData> response) {
                    Log.e(TAG,"onResponse");
                    if(response.isSuccessful()){
                        mResponseData = response.body();
                        if(mResponseData.getStatus().equals("succes")){
                            showCaseList = mResponseData.getShowcases();
                            if(showCaseList.size()>0){
                                mAdapter = new Adapter_ListData(MainActivity.this,showCaseList);
                                mRecyclerview.setAdapter(mAdapter);
                                ll_recyclerview.setVisibility(View.VISIBLE);
                                mProgressbar.setVisibility(View.GONE);
                            }


                        }
                        else{
                            ll_recyclerview.setVisibility(View.GONE);
                            mProgressbar.setVisibility(View.VISIBLE);
                        }

                    }
                    else
                    {
                        Toast.makeText(MainActivity.this,response.message(), Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<Response_ListData> call, Throwable t) {
                    Log.e(TAG,"onFailure " + t.toString());
                }
            });
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
