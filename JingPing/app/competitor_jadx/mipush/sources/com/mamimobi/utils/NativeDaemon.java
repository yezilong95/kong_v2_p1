package com.mamimobi.utils;

/* JADX INFO: loaded from: classes.dex */
public class NativeDaemon {
    static {
        System.loadLibrary("daemon");
    }

    public static native int startDaemon(String str, String str2, int i);

    public static native int startDaemonService(String str, String str2, int i);

    public static native void stopDaemon();
}
