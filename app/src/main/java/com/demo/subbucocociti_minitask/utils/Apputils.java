package com.demo.subbucocociti_minitask.utils;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by subbu on 23/6/17.
 */

public class Apputils {


    public static final String ARG_TOKEN = "token";
    public static final String ARG_EMAIL = "email";

    private static final String EMAIL_PATTERN = "^[a-zA-Z0-9#_~!$&'()*+,;=:.\"(),:;<>@\\[\\]\\\\]+@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*$";
    private static Pattern pattern = Pattern.compile(EMAIL_PATTERN);
    private static Matcher matcher;

    static ProgressDialog progressDialog;

    public static void showProgressDialog(AppCompatActivity mActivity, String Str_Msg, boolean setCancelable)
    {
        progressDialog = new ProgressDialog(mActivity);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setMessage(Str_Msg);
        progressDialog.setCancelable(setCancelable);
        if (!progressDialog.isShowing()) {
            progressDialog.show();
        }
    }

    public static void hideProgressDialog()
    {
        if(progressDialog ==null){}
        else {
            progressDialog.hide();
            progressDialog.dismiss();
        }
    }

    public static boolean validateEmail(String email) {
        matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
