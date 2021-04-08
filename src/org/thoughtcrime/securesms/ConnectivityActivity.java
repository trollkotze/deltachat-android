package org.thoughtcrime.securesms;

import android.os.Bundle;

import com.b44t.messenger.DcContext;
import com.b44t.messenger.DcEvent;

import org.thoughtcrime.securesms.connect.DcEventCenter;
import org.thoughtcrime.securesms.connect.DcHelper;

public class ConnectivityActivity extends WebViewActivity implements DcEventCenter.DcEventDelegate {
  @Override
  protected void onCreate(Bundle state, boolean ready) {
    super.onCreate(state, ready);
    refresh();

    DcHelper.getContext(this).eventCenter.addObserver(DcContext.DC_EVENT_CONNECTIVITY_CHANGED, this);

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

  @Override
  public void onDestroy() {
    super.onDestroy();
    DcHelper.getContext(this).eventCenter.removeObservers(this);
  }

  private void refresh() {
    getSupportActionBar().setTitle(getString(R.string.connectivity) + " - " + DcContext.getConnectivitySummary(this));

    String finalHtml = DcHelper.getContext(this).getConnectivityHtml();
    webView.loadDataWithBaseURL(null, finalHtml, "text/html", "utf-8", null);
  }

  @Override
  public void handleEvent(DcEvent event) {
    refresh();
  }
}
