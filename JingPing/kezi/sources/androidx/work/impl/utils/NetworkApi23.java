package androidx.work.impl.utils;

import android.net.ConnectivityManager;
import android.net.Network;
import defpackage.oy;

/* JADX INFO: loaded from: classes.dex */
public final class NetworkApi23 {
    public static final Network getActiveNetworkCompat(ConnectivityManager connectivityManager) {
        oy.j(connectivityManager, "<this>");
        return connectivityManager.getActiveNetwork();
    }
}
