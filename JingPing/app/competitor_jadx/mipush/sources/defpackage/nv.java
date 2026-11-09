package defpackage;

import android.view.ViewGroup;
import android.webkit.RenderProcessGoneDetail;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: classes.dex */
public final class nv extends WebViewClient {
    public final WeakReference a;

    public nv(pv pvVar) {
        this.a = new WeakReference(pvVar);
    }

    @Override // android.webkit.WebViewClient
    public final boolean onRenderProcessGone(WebView webView, RenderProcessGoneDetail renderProcessGoneDetail) {
        if (webView != null) {
            try {
                webView.stopLoading();
                if (webView.getParent() instanceof ViewGroup) {
                    ((ViewGroup) webView.getParent()).removeView(webView);
                }
                webView.destroy();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        pv pvVar = (pv) this.a.get();
        if (pvVar == null) {
            return true;
        }
        pvVar.d = null;
        return true;
    }

    @Override // android.webkit.WebViewClient
    public final boolean shouldOverrideUrlLoading(WebView webView, String str) {
        return false;
    }
}
