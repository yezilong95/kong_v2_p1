package defpackage;

import android.content.Intent;
import android.net.Uri;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/* JADX INFO: loaded from: classes.dex */
public final class v1 extends WebViewClient {
    public final /* synthetic */ int a;

    @Override // android.webkit.WebViewClient
    public void onPageFinished(WebView webView, String str) {
        switch (this.a) {
            case 1:
            case 3:
                break;
            case 2:
            default:
                super.onPageFinished(webView, str);
                break;
        }
    }

    @Override // android.webkit.WebViewClient
    public boolean shouldOverrideUrlLoading(WebView webView, WebResourceRequest webResourceRequest) {
        switch (this.a) {
            case 0:
                Uri url = webResourceRequest.getUrl();
                if (!url.toString().startsWith("http")) {
                    return true;
                }
                webView.loadUrl(url.toString());
                return true;
            case 1:
            default:
                return super.shouldOverrideUrlLoading(webView, webResourceRequest);
            case 2:
                Uri url2 = webResourceRequest.getUrl();
                String scheme = url2.getScheme();
                if ("http".equalsIgnoreCase(scheme) || "https".equalsIgnoreCase(scheme)) {
                    return false;
                }
                try {
                    webView.getContext().startActivity(new Intent("android.intent.action.VIEW", url2));
                    break;
                } catch (Exception unused) {
                }
                return true;
        }
    }

    @Override // android.webkit.WebViewClient
    public boolean shouldOverrideUrlLoading(WebView webView, String str) {
        switch (this.a) {
            case 0:
                if (!str.startsWith("http")) {
                    return true;
                }
                webView.loadUrl(str);
                return true;
            case 1:
                return false;
            case 2:
            default:
                return super.shouldOverrideUrlLoading(webView, str);
            case 3:
                return false;
        }
    }

    private final void a(WebView webView, String str) {
    }

    private final void b(WebView webView, String str) {
    }
}
