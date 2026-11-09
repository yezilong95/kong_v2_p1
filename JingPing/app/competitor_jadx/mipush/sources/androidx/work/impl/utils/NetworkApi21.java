package androidx.work.impl.utils;

import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import defpackage.t40;

/* JADX INFO: loaded from: classes.dex */
public final class NetworkApi21 {
    public static final NetworkCapabilities getNetworkCapabilitiesCompat(ConnectivityManager connectivityManager, Network network) {
        t40.n(connectivityManager, "<this>");
        return connectivityManager.getNetworkCapabilities(network);
    }

    public static final boolean hasCapabilityCompat(NetworkCapabilities networkCapabilities, int i) {
        t40.n(networkCapabilities, "<this>");
        return networkCapabilities.hasCapability(i);
    }

    public static final void unregisterNetworkCallbackCompat(ConnectivityManager connectivityManager, ConnectivityManager.NetworkCallback networkCallback) {
        t40.n(connectivityManager, "<this>");
        t40.n(networkCallback, "networkCallback");
        connectivityManager.unregisterNetworkCallback(networkCallback);
    }
}
