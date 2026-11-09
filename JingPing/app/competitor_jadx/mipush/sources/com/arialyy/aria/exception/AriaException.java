package com.arialyy.aria.exception;

/* JADX INFO: loaded from: classes.dex */
public class AriaException extends Exception {
    public AriaException(String str) {
        super(str);
    }

    public AriaException(String str, Exception exc) {
        super((str == null ? "" : str) + "\n" + (exc != null ? exc.getMessage() : ""));
    }
}
