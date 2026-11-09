package com.arialyy.aria.exception;

import defpackage.zn;

/* JADX INFO: loaded from: classes.dex */
public class AriaFTPException extends AriaException {
    public AriaFTPException(String str) {
        super(zn.v("Aria FTP Exception:\n", str));
    }

    public AriaFTPException(String str, Exception exc) {
        super(str, exc);
    }
}
