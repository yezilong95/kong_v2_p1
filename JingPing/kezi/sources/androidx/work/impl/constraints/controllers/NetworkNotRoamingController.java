package androidx.work.impl.constraints.controllers;

import androidx.work.Logger;
import androidx.work.NetworkType;
import androidx.work.impl.constraints.NetworkState;
import androidx.work.impl.constraints.trackers.ConstraintTracker;
import androidx.work.impl.model.WorkSpec;
import defpackage.aj;
import defpackage.oy;

/* JADX INFO: loaded from: classes.dex */
public final class NetworkNotRoamingController extends ConstraintController<NetworkState> {
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
        String strTagWithPrefix = Logger.tagWithPrefix("NetworkNotRoamingCtrlr");
        oy.i(strTagWithPrefix, "tagWithPrefix(\"NetworkNotRoamingCtrlr\")");
        TAG = strTagWithPrefix;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public NetworkNotRoamingController(ConstraintTracker<NetworkState> constraintTracker) {
        super(constraintTracker);
        oy.j(constraintTracker, "tracker");
    }

    @Override // androidx.work.impl.constraints.controllers.ConstraintController
    public boolean hasConstraint(WorkSpec workSpec) {
        oy.j(workSpec, "workSpec");
        return workSpec.constraints.getRequiredNetworkType() == NetworkType.NOT_ROAMING;
    }

    @Override // androidx.work.impl.constraints.controllers.ConstraintController
    public boolean isConstrained(NetworkState networkState) {
        oy.j(networkState, "value");
        return (networkState.isConnected() && networkState.isNotRoaming()) ? false : true;
    }
}
