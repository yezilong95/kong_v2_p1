package androidx.work.impl.constraints.controllers;

import android.os.Build;
import androidx.work.NetworkType;
import androidx.work.impl.constraints.NetworkState;
import androidx.work.impl.constraints.trackers.ConstraintTracker;
import androidx.work.impl.model.WorkSpec;
import defpackage.t40;

/* JADX INFO: loaded from: classes.dex */
public final class NetworkConnectedController extends ConstraintController<NetworkState> {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public NetworkConnectedController(ConstraintTracker<NetworkState> constraintTracker) {
        super(constraintTracker);
        t40.n(constraintTracker, "tracker");
    }

    @Override // androidx.work.impl.constraints.controllers.ConstraintController
    public boolean hasConstraint(WorkSpec workSpec) {
        t40.n(workSpec, "workSpec");
        return workSpec.constraints.getRequiredNetworkType() == NetworkType.CONNECTED;
    }

    @Override // androidx.work.impl.constraints.controllers.ConstraintController
    public boolean isConstrained(NetworkState networkState) {
        t40.n(networkState, "value");
        return Build.VERSION.SDK_INT >= 26 ? (networkState.isConnected() && networkState.isValidated()) ? false : true : !networkState.isConnected();
    }
}
