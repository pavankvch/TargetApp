package kuwait.com.targetlogistics.home;

import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings.LayoutAlgorithm;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import kuwait.com.targetlogistics.BaseActivity;
import kuwait.com.targetlogistics.R;
import kuwait.com.targetlogistics.common.Utils;
import kuwait.com.targetlogistics.databinding.ActivityPriceListBinding;

public class PriceListActivity extends BaseActivity {
    private String PAYMENT_URL = "";
    private String backUrl = "";
    ActivityPriceListBinding binding;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.binding = ActivityPriceListBinding.inflate(getLayoutInflater(), this.baseBinding.frmContainer, true);
        init();
    }

    private void init() {
        setTitle(getResources().getString(R.string.price_list));
        this.PAYMENT_URL = Utils.getPriceListUrl(this.me);
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
                    PriceListActivity.this.dismissDialog();
                }
            }

            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                Utils.printMsg("WebPage Url =>", url + "");
                PriceListActivity.this.showOnlyProgressDialog();
            }

            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if (!url.startsWith("tel:")) {
                    return handleUri(Uri.parse(url));
                }
                PriceListActivity.this.startActivity(new Intent("android.intent.action.DIAL", Uri.parse(url)));
                return true;
            }

            @TargetApi(21)
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                if (!request.getUrl().toString().startsWith("tel:")) {
                    return handleUri(request.getUrl());
                }
                PriceListActivity.this.startActivity(new Intent("android.intent.action.DIAL", Uri.parse(request.getUrl().toString())));
                return true;
            }

            private boolean handleUri(Uri uri) {
                Utils.printMsg("WebPage Url =>", String.valueOf(uri));
                PriceListActivity.this.backUrl = String.valueOf(uri);
                PriceListActivity.this.binding.webview.loadUrl(String.valueOf(uri));
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
