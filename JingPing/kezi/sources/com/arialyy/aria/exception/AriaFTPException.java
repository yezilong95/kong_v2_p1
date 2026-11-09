package com.arialyy.aria.exception;

import defpackage.hk;

/* JADX INFO: loaded from: classes.dex */
public class AriaFTPException extends AriaException {
    public AriaFTPException(String str) {
        super(hk.n("Aria FTP Exception:\n", str));
    }

    public AriaFTPException(String str, Exception exc) {
        super(str, exc);
    }
}
