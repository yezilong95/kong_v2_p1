package androidx.work.impl.utils;

import android.net.ConnectivityManager;
import android.net.Network;
import defpackage.t40;

/* JADX INFO: loaded from: classes.dex */
public final class NetworkApi23 {
    public static final Network getActiveNetworkCompat(ConnectivityManager connectivityManager) {
        t40.n(connectivityManager, "<this>");
        return connectivityManager.getActiveNetwork();
    }
}
