package org.thoughtcrime.securesms;

import android.os.Bundle;

import com.b44t.messenger.DcContext;

import org.thoughtcrime.securesms.connect.DcHelper;

public class ConnectivityActivity extends WebViewActivity {
  @Override
  protected void onCreate(Bundle state, boolean ready) {
    super.onCreate(state, ready);
    String s = "[invalid connectivity]";
    int connectivity = DcHelper.getContext(this).getConnectivity();
    if (connectivity == DcContext.DC_CONNECTIVITY_NOT_CONNECTED) s = getString(R.string.connectivity_not_connected);
    if (connectivity == DcContext.DC_CONNECTIVITY_CONNECTING) s = getString(R.string.connectivity_connecting);
    if (connectivity == DcContext.DC_CONNECTIVITY_CONNECTED) s = getString(R.string.connectivity_connected);

    getSupportActionBar().setTitle("Connectivity: " + s);

    String finalHtml = DcHelper.getContext(this).getConnectivityHtml();
    webView.loadDataWithBaseURL(null, finalHtml, "text/html", "utf-8", null);

//    webView.loadDataWithBaseURL(null, "<!DOCTYPE html>\n<html><head><meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" /></head><body>\n" +
//                    "<div><h3>Receiving messages:</h3><ul>"+
//                    "<li><b>&quot;Inbox&quot;:</b> Error</li>"+
//                    "<li><b>&quot;DeltaChat&quot;:</b> Error</li>"+
//                    "</ul></div>"+
//                    "<h3>Sending messages:</h3><ul style=\"list-style-type: none;\"><li>"+
//                    "Error"+
//                    "</li></ul>"
//
//
//            ,"text/html", "utf-8", null);
  }
}
