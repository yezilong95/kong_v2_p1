package com.arialyy.aria.exception;

import defpackage.zn;

/* JADX INFO: loaded from: classes.dex */
public class AriaHTTPException extends AriaException {
    public AriaHTTPException(String str) {
        super(zn.v("Aria HTTP Exception:\n", str));
    }

    public AriaHTTPException(String str, Exception exc) {
        super(str, exc);
    }
}
