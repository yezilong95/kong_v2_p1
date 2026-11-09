package androidx.work.impl.utils;

import android.net.ConnectivityManager;
import defpackage.t40;

/* JADX INFO: loaded from: classes.dex */
public final class NetworkApi24 {
    public static final void registerDefaultNetworkCallbackCompat(ConnectivityManager connectivityManager, ConnectivityManager.NetworkCallback networkCallback) {
        t40.n(connectivityManager, "<this>");
        t40.n(networkCallback, "networkCallback");
        connectivityManager.registerDefaultNetworkCallback(networkCallback);
    }
}
