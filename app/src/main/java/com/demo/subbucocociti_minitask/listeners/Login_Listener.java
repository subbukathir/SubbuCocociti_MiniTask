package com.demo.subbucocociti_minitask.listeners;

import com.demo.subbucocociti_minitask.response_data.Response_Login;

/**
 * Created by subbu on 23/6/17.
 */

public interface Login_Listener {
    void onLoginDataReceivedSuccess(Response_Login responseLogin);
    void onLoginDataReceivedError(String strErr);
}
