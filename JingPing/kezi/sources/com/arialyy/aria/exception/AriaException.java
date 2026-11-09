package com.arialyy.aria.exception;

import org.java_websocket.extensions.ExtensionRequestData;

/* JADX INFO: loaded from: classes.dex */
public class AriaException extends Exception {
    public AriaException(String str) {
        super(str);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public AriaException(String str, Exception exc) {
        String str2 = ExtensionRequestData.EMPTY_VALUE;
        super((str == null ? ExtensionRequestData.EMPTY_VALUE : str) + "\n" + (exc != null ? exc.getMessage() : str2));
    }
}
