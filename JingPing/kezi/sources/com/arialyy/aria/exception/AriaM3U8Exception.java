package com.arialyy.aria.exception;

import defpackage.hk;

/* JADX INFO: loaded from: classes.dex */
public class AriaM3U8Exception extends AriaException {
    public AriaM3U8Exception(String str) {
        super(hk.n("Aria M3U8 Exception:\n", str));
    }

    public AriaM3U8Exception(String str, Exception exc) {
        super(str, exc);
    }
}
