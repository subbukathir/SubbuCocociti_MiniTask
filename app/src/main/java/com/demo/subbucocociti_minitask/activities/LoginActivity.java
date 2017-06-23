package com.demo.subbucocociti_minitask.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.demo.subbucocociti_minitask.R;
import com.demo.subbucocociti_minitask.asyncprocess.GetLoginProcess;
import com.demo.subbucocociti_minitask.listeners.Login_Listener;
import com.demo.subbucocociti_minitask.response_data.Response_Login;
import com.demo.subbucocociti_minitask.utils.Apputils;

/**
 * Created by subbu on 23/6/17.
 */

public class LoginActivity extends AppCompatActivity implements Login_Listener,View.OnClickListener {
    private static final String TAG = LoginActivity.class.getSimpleName();

    private Toolbar mToolbar;

    private AppCompatEditText ape_email,ape_password;
    private AppCompatButton ape_btn_login;

    private GetLoginProcess mProcess;
    private AppCompatActivity mActivity;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mActivity = this;

        ape_email = (AppCompatEditText) findViewById(R.id.et_email);
        ape_password = (AppCompatEditText) findViewById(R.id.et_password);
        ape_btn_login = (AppCompatButton) findViewById(R.id.btn_login);

        ape_btn_login.setOnClickListener(this);

    }

    @Override
    protected void onStart() {
        super.onStart();
        setUpActionBar();
    }

    private void setUpActionBar(){
        mToolbar =(Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle(R.string.lbl_login);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_login:
                String email = ape_email.getText().toString().trim();
                String pwd = ape_password.getText().toString().trim();

                if(!email.equals("") && !pwd.equals("")){

                    if(Apputils.validateEmail(email) ){
                        Apputils.showProgressDialog(mActivity,"Loading ...",true);
                        mProcess = new GetLoginProcess(mActivity,email,pwd);
                        mProcess.setListener(this);
                        mProcess.execute();
                    }else ape_email.setError(getString(R.string.msg_err_email));
                }
                else if(email.equals("")) ape_email.setError(getString(R.string.lbl_enter_email));
                else if(pwd.equals("")) ape_password.setError(getString(R.string.lbl_enter_password));
                else Toast.makeText(mActivity,"Please fill the details and try again",Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    public void onLoginDataReceivedSuccess(Response_Login responseLogin) {
        Log.e(TAG,"onLoginDataReceivedSuccess");
        Apputils.hideProgressDialog();

        Bundle bundle = new Bundle();
        bundle.putString(Apputils.ARG_TOKEN,responseLogin.getData().getUser().getAccessToken());
        bundle.putString(Apputils.ARG_EMAIL,responseLogin.getData().getUser().getEmail());
        Log.e(TAG,"result " + responseLogin.getStatus());

        Intent intent = new Intent(LoginActivity.this,MainActivity.class);
        intent.putExtra(Apputils.ARG_BUNDLE,bundle);
        startActivity(intent);

    }

    @Override
    public void onLoginDataReceivedError(String strErr) {
        Log.e(TAG,"onLoginDataReceivedError");
        Apputils.hideProgressDialog();

        Toast.makeText(mActivity,strErr,Toast.LENGTH_SHORT).show();
        Log.e(TAG," result error " + strErr);
    }
}
