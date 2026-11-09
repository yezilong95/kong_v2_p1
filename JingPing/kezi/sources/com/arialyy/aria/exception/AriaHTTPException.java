package com.arialyy.aria.exception;

import defpackage.hk;

/* JADX INFO: loaded from: classes.dex */
public class AriaHTTPException extends AriaException {
    public AriaHTTPException(String str) {
        super(hk.n("Aria HTTP Exception:\n", str));
    }

    public AriaHTTPException(String str, Exception exc) {
        super(str, exc);
    }
}
