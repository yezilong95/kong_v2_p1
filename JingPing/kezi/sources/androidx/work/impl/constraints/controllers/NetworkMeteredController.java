package androidx.work.impl.constraints.controllers;

import android.os.Build;
import androidx.work.Logger;
import androidx.work.NetworkType;
import androidx.work.impl.constraints.NetworkState;
import androidx.work.impl.constraints.trackers.ConstraintTracker;
import androidx.work.impl.model.WorkSpec;
import defpackage.aj;
import defpackage.oy;

/* JADX INFO: loaded from: classes.dex */
public final class NetworkMeteredController extends ConstraintController<NetworkState> {
    public static final Companion Companion = new Companion(null);
    private static final String TAG;

    public static final class Companion {
        public /* synthetic */ Companion(aj ajVar) {
            this();
        }

        private Companion() {
        }
    }

    static {
        String strTagWithPrefix = Logger.tagWithPrefix("NetworkMeteredCtrlr");
        oy.i(strTagWithPrefix, "tagWithPrefix(\"NetworkMeteredCtrlr\")");
        TAG = strTagWithPrefix;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public NetworkMeteredController(ConstraintTracker<NetworkState> constraintTracker) {
        super(constraintTracker);
        oy.j(constraintTracker, "tracker");
    }

    @Override // androidx.work.impl.constraints.controllers.ConstraintController
    public boolean hasConstraint(WorkSpec workSpec) {
        oy.j(workSpec, "workSpec");
        return workSpec.constraints.getRequiredNetworkType() == NetworkType.METERED;
    }

    @Override // androidx.work.impl.constraints.controllers.ConstraintController
    public boolean isConstrained(NetworkState networkState) {
        oy.j(networkState, "value");
        if (Build.VERSION.SDK_INT >= 26) {
            return (networkState.isConnected() && networkState.isMetered()) ? false : true;
        }
        Logger.get().debug(TAG, "Metered network constraint is not supported before API 26, only checking for connected state.");
        return !networkState.isConnected();
    }
}
