package com.norman.webviewup.lib.reflect;

import java.lang.reflect.InvocationTargetException;

/* JADX INFO: loaded from: classes.dex */
public class ReflectException extends RuntimeException {
    public ReflectException(String str) {
        super(str);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public ReflectException(String str, Throwable th) {
        Throwable cause;
        if (th instanceof InvocationTargetException) {
            InvocationTargetException invocationTargetException = (InvocationTargetException) th;
            Throwable cause2 = invocationTargetException.getCause();
            cause = invocationTargetException;
            if (cause2 != null) {
                cause = cause2;
            }
        } else {
            cause = th instanceof ReflectException ? th.getCause() : th;
        }
        super(str, cause);
        setStackTrace(th.getStackTrace());
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public ReflectException(Throwable th) {
        Throwable cause;
        if (th instanceof InvocationTargetException) {
            InvocationTargetException invocationTargetException = (InvocationTargetException) th;
            Throwable cause2 = invocationTargetException.getCause();
            cause = invocationTargetException;
            if (cause2 != null) {
                cause = cause2;
            }
        } else {
            cause = th instanceof ReflectException ? th.getCause() : th;
        }
        super(cause);
        setStackTrace(th.getStackTrace());
    }
}
