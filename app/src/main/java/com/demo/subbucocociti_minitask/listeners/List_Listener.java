package com.demo.subbucocociti_minitask.listeners;

import com.demo.subbucocociti_minitask.model.ShowCase;

import java.util.List;

/**
 * Created by subbu on 23/6/17.
 */

public interface List_Listener {
    void onListDataReceivedSuccess(List<ShowCase> listShowCase);
    void onListDataReceivedError(String strErr);
}
