package kuwait.com.targetlogistics.history;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings.LayoutAlgorithm;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.google.android.gms.plus.PlusShare;
import kuwait.com.targetlogistics.BaseActivity;
import kuwait.com.targetlogistics.R;
import kuwait.com.targetlogistics.common.Utils;
import kuwait.com.targetlogistics.databinding.ActivityTrackShipmentBinding;

public class TrackShipmentActivity extends BaseActivity {
    private String PAYMENT_URL = "";
    private String backUrl = "";
    ActivityTrackShipmentBinding binding;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.binding = ActivityTrackShipmentBinding.inflate(getLayoutInflater(), this.baseBinding.frmContainer, true);
        init();
    }

    private void init() {
        setTitle(getResources().getString(R.string.track_shipment));
        if (getIntent().hasExtra(PlusShare.KEY_CALL_TO_ACTION_URL)) {
            this.PAYMENT_URL = getIntent().getStringExtra(PlusShare.KEY_CALL_TO_ACTION_URL);
        }
        Utils.printMsg("URL", this.PAYMENT_URL);
        this.binding.webview.getSettings().setBuiltInZoomControls(true);
        this.binding.webview.getSettings().setLoadsImagesAutomatically(true);
        this.binding.webview.getSettings().setJavaScriptEnabled(true);
        this.binding.webview.clearCache(true);
        this.binding.webview.getSettings().setUseWideViewPort(true);
        this.binding.webview.getSettings().setLoadWithOverviewMode(true);
        if (VERSION.SDK_INT >= 19) {
            this.binding.webview.getSettings().setLayoutAlgorithm(LayoutAlgorithm.TEXT_AUTOSIZING);
        } else {
            this.binding.webview.getSettings().setLayoutAlgorithm(LayoutAlgorithm.NORMAL);
        }
        this.binding.webview.setScrollBarStyle(0);
        showOnlyProgressDialog();
        this.binding.webview.loadUrl(this.PAYMENT_URL);
        this.binding.webview.setWebViewClient(new WebViewClient() {
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                if (view.getProgress() == 100) {
                    TrackShipmentActivity.this.dismissDialog();
                }
            }

            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                Utils.printMsg("WebPage Url =>", url + "");
                TrackShipmentActivity.this.showOnlyProgressDialog();
            }

            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return handleUri(Uri.parse(url));
            }

            @TargetApi(21)
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                return handleUri(request.getUrl());
            }

            private boolean handleUri(Uri uri) {
                Utils.printMsg("WebPage Url =>", String.valueOf(uri));
                TrackShipmentActivity.this.backUrl = String.valueOf(uri);
                TrackShipmentActivity.this.binding.webview.loadUrl(String.valueOf(uri));
                return true;
            }
        });
    }

    protected void onDestroy() {
        super.onDestroy();
        if (Utils.getLanguage(this.me).equals("en")) {
            Utils.setMyLocale(this.me, "en");
        } else {
            Utils.setMyLocale(this.me, "ar");
        }
    }

    public void onBackPressed() {
        finish();
    }
}
