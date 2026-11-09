package androidx.work.impl.constraints.trackers;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import androidx.work.Logger;
import androidx.work.impl.constraints.NetworkState;
import androidx.work.impl.utils.NetworkApi21;
import androidx.work.impl.utils.NetworkApi23;
import androidx.work.impl.utils.taskexecutor.TaskExecutor;
import defpackage.t40;

/* JADX INFO: loaded from: classes.dex */
public final class NetworkStateTrackerKt {
    private static final String TAG;

    static {
        String strTagWithPrefix = Logger.tagWithPrefix("NetworkStateTracker");
        t40.m(strTagWithPrefix, "tagWithPrefix(\"NetworkStateTracker\")");
        TAG = strTagWithPrefix;
    }

    public static final ConstraintTracker<NetworkState> NetworkStateTracker(Context context, TaskExecutor taskExecutor) {
        t40.n(context, "context");
        t40.n(taskExecutor, "taskExecutor");
        return new NetworkStateTracker24(context, taskExecutor);
    }

    public static final NetworkState getActiveNetworkState(ConnectivityManager connectivityManager) {
        t40.n(connectivityManager, "<this>");
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        boolean z = false;
        boolean z2 = activeNetworkInfo != null && activeNetworkInfo.isConnected();
        boolean zIsActiveNetworkValidated = isActiveNetworkValidated(connectivityManager);
        boolean zIsActiveNetworkMetered = connectivityManager.isActiveNetworkMetered();
        if (activeNetworkInfo != null && !activeNetworkInfo.isRoaming()) {
            z = true;
        }
        return new NetworkState(z2, zIsActiveNetworkValidated, zIsActiveNetworkMetered, z);
    }

    public static /* synthetic */ void getActiveNetworkState$annotations(ConnectivityManager connectivityManager) {
    }

    public static final boolean isActiveNetworkValidated(ConnectivityManager connectivityManager) {
        t40.n(connectivityManager, "<this>");
        try {
            NetworkCapabilities networkCapabilitiesCompat = NetworkApi21.getNetworkCapabilitiesCompat(connectivityManager, NetworkApi23.getActiveNetworkCompat(connectivityManager));
            if (networkCapabilitiesCompat != null) {
                return NetworkApi21.hasCapabilityCompat(networkCapabilitiesCompat, 16);
            }
            return false;
        } catch (SecurityException e) {
            Logger.get().error(TAG, "Unable to validate active network", e);
            return false;
        }
    }
}
