package androidx.work.impl.constraints.controllers;

import android.os.Build;
import androidx.work.Logger;
import androidx.work.NetworkType;
import androidx.work.impl.constraints.NetworkState;
import androidx.work.impl.constraints.trackers.ConstraintTracker;
import androidx.work.impl.model.WorkSpec;
import defpackage.t40;
import defpackage.tm;

/* JADX INFO: loaded from: classes.dex */
public final class NetworkMeteredController extends ConstraintController<NetworkState> {
    public static final Companion Companion = new Companion(null);
    private static final String TAG;

    public static final class Companion {
        public /* synthetic */ Companion(tm tmVar) {
            this();
        }

        private Companion() {
        }
    }

    static {
        String strTagWithPrefix = Logger.tagWithPrefix("NetworkMeteredCtrlr");
        t40.m(strTagWithPrefix, "tagWithPrefix(\"NetworkMeteredCtrlr\")");
        TAG = strTagWithPrefix;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public NetworkMeteredController(ConstraintTracker<NetworkState> constraintTracker) {
        super(constraintTracker);
        t40.n(constraintTracker, "tracker");
    }

    @Override // androidx.work.impl.constraints.controllers.ConstraintController
    public boolean hasConstraint(WorkSpec workSpec) {
        t40.n(workSpec, "workSpec");
        return workSpec.constraints.getRequiredNetworkType() == NetworkType.METERED;
    }

    @Override // androidx.work.impl.constraints.controllers.ConstraintController
    public boolean isConstrained(NetworkState networkState) {
        t40.n(networkState, "value");
        if (Build.VERSION.SDK_INT >= 26) {
            return (networkState.isConnected() && networkState.isMetered()) ? false : true;
        }
        Logger.get().debug(TAG, "Metered network constraint is not supported before API 26, only checking for connected state.");
        return !networkState.isConnected();
    }
}
