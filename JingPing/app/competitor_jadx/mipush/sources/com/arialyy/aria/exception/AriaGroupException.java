package com.arialyy.aria.exception;

import defpackage.zn;

/* JADX INFO: loaded from: classes.dex */
public class AriaGroupException extends AriaException {
    public AriaGroupException(String str) {
        super(zn.v("Aria Group Exception:\n", str));
    }

    public AriaGroupException(String str, Exception exc) {
        super(str, exc);
    }
}
