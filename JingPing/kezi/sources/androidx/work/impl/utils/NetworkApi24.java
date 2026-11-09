package androidx.work.impl.utils;

import android.net.ConnectivityManager;
import defpackage.oy;

/* JADX INFO: loaded from: classes.dex */
public final class NetworkApi24 {
    public static final void registerDefaultNetworkCallbackCompat(ConnectivityManager connectivityManager, ConnectivityManager.NetworkCallback networkCallback) {
        oy.j(connectivityManager, "<this>");
        oy.j(networkCallback, "networkCallback");
        connectivityManager.registerDefaultNetworkCallback(networkCallback);
    }
}
