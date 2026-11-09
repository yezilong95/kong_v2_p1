package com.norman.webviewup.lib;

/* JADX INFO: loaded from: classes.dex */
public class WebViewReplaceException extends Exception {
    public WebViewReplaceException(String str) {
        super(str);
    }

    public WebViewReplaceException(String str, Throwable th) {
        super(str, th);
        setStackTrace(th.getStackTrace());
    }
}
