package androidx.work.impl.constraints.trackers;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import androidx.work.Logger;
import androidx.work.impl.constraints.NetworkState;
import androidx.work.impl.utils.taskexecutor.TaskExecutor;
import defpackage.t40;

/* JADX INFO: loaded from: classes.dex */
public final class NetworkStateTrackerPre24 extends BroadcastReceiverConstraintTracker<NetworkState> {
    private final ConnectivityManager connectivityManager;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public NetworkStateTrackerPre24(Context context, TaskExecutor taskExecutor) {
        super(context, taskExecutor);
        t40.n(context, "context");
        t40.n(taskExecutor, "taskExecutor");
        Object systemService = getAppContext().getSystemService("connectivity");
        t40.l(systemService, "null cannot be cast to non-null type android.net.ConnectivityManager");
        this.connectivityManager = (ConnectivityManager) systemService;
    }

    public static /* synthetic */ void getIntentFilter$annotations() {
    }

    @Override // androidx.work.impl.constraints.trackers.BroadcastReceiverConstraintTracker
    public IntentFilter getIntentFilter() {
        return new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE");
    }

    @Override // androidx.work.impl.constraints.trackers.BroadcastReceiverConstraintTracker
    public void onBroadcastReceive(Intent intent) {
        t40.n(intent, "intent");
        if (t40.f(intent.getAction(), "android.net.conn.CONNECTIVITY_CHANGE")) {
            Logger.get().debug(NetworkStateTrackerKt.TAG, "Network broadcast received");
            setState(NetworkStateTrackerKt.getActiveNetworkState(this.connectivityManager));
        }
    }

    @Override // androidx.work.impl.constraints.trackers.ConstraintTracker
    public NetworkState getInitialState() {
        return NetworkStateTrackerKt.getActiveNetworkState(this.connectivityManager);
    }
}
