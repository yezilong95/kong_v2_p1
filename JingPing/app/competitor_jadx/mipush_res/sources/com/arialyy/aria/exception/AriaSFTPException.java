package com.arialyy.aria.exception;

import defpackage.zn;

/* JADX INFO: loaded from: classes.dex */
public class AriaSFTPException extends AriaException {
    public AriaSFTPException(String str) {
        super(zn.v("Aria SFTP Exception:\n", str));
    }

    public AriaSFTPException(String str, Exception exc) {
        super(str, exc);
    }
}
