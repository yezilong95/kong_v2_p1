package defpackage;

import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

/* JADX INFO: loaded from: classes.dex */
public final class jv extends WebChromeClient {
    public final /* synthetic */ int a;

    @Override // android.webkit.WebChromeClient
    public final boolean onJsAlert(WebView webView, String str, String str2, JsResult jsResult) {
        switch (this.a) {
        }
        return true;
    }
}
