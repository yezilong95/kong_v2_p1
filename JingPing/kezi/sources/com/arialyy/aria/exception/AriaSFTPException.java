package com.arialyy.aria.exception;

import defpackage.hk;

/* JADX INFO: loaded from: classes.dex */
public class AriaSFTPException extends AriaException {
    public AriaSFTPException(String str) {
        super(hk.n("Aria SFTP Exception:\n", str));
    }

    public AriaSFTPException(String str, Exception exc) {
        super(str, exc);
    }
}
