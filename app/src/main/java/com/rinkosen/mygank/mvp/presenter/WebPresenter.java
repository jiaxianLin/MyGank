package com.rinkosen.mygank.mvp.presenter;

import android.content.Context;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.rinkosen.mygank.mvp.view.IWebView;

/**
 * Created by rinkousen on 17/10/26.
 */

public class WebPresenter extends BasePresenter {

    IWebView view;

    Context context;

    public WebPresenter(IWebView view) {
        this.view = view;
        context = (Context) view;
    }

    public void setWebViewSettings(WebView webView, String url) {
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setLoadWithOverviewMode(true);
        settings.setAppCacheEnabled(true);
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        settings.setSupportZoom(true);
        webView.setWebChromeClient(new ChromeClient());
        webView.setWebViewClient(new GankClient());
        webView.loadUrl(url);
    }

    private class ChromeClient extends WebChromeClient {
        @Override
        public void onProgressChanged(WebView webview, int newProgress) {
            super.onProgressChanged(webview, newProgress);
            view.showProgress(newProgress);
        }

        @Override
        public void onReceivedTitle(WebView webview, String title) {
            super.onReceivedTitle(webview, title);
            view.setWebTitle(title);
        }
    }

    private class GankClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView webview, String url) {
            if (url != null) webview.loadUrl(url);
            return true;
        }
    }
}
