package androidx.work.impl.constraints.trackers;

import android.content.Context;
import androidx.work.impl.constraints.NetworkState;
import androidx.work.impl.utils.taskexecutor.TaskExecutor;
import defpackage.oy;

/* JADX INFO: loaded from: classes.dex */
public final class Trackers {
    private final ConstraintTracker<Boolean> batteryChargingTracker;
    private final BatteryNotLowTracker batteryNotLowTracker;
    private final ConstraintTracker<NetworkState> networkStateTracker;
    private final ConstraintTracker<Boolean> storageNotLowTracker;

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public Trackers(Context context, TaskExecutor taskExecutor) {
        this(context, taskExecutor, null, null, null, null, 60, null);
        oy.j(context, "context");
        oy.j(taskExecutor, "taskExecutor");
    }

    public final ConstraintTracker<Boolean> getBatteryChargingTracker() {
        return this.batteryChargingTracker;
    }

    public final BatteryNotLowTracker getBatteryNotLowTracker() {
        return this.batteryNotLowTracker;
    }

    public final ConstraintTracker<NetworkState> getNetworkStateTracker() {
        return this.networkStateTracker;
    }

    public final ConstraintTracker<Boolean> getStorageNotLowTracker() {
        return this.storageNotLowTracker;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public Trackers(Context context, TaskExecutor taskExecutor, ConstraintTracker<Boolean> constraintTracker) {
        this(context, taskExecutor, constraintTracker, null, null, null, 56, null);
        oy.j(context, "context");
        oy.j(taskExecutor, "taskExecutor");
        oy.j(constraintTracker, "batteryChargingTracker");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public Trackers(Context context, TaskExecutor taskExecutor, ConstraintTracker<Boolean> constraintTracker, BatteryNotLowTracker batteryNotLowTracker) {
        this(context, taskExecutor, constraintTracker, batteryNotLowTracker, null, null, 48, null);
        oy.j(context, "context");
        oy.j(taskExecutor, "taskExecutor");
        oy.j(constraintTracker, "batteryChargingTracker");
        oy.j(batteryNotLowTracker, "batteryNotLowTracker");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public Trackers(Context context, TaskExecutor taskExecutor, ConstraintTracker<Boolean> constraintTracker, BatteryNotLowTracker batteryNotLowTracker, ConstraintTracker<NetworkState> constraintTracker2) {
        this(context, taskExecutor, constraintTracker, batteryNotLowTracker, constraintTracker2, null, 32, null);
        oy.j(context, "context");
        oy.j(taskExecutor, "taskExecutor");
        oy.j(constraintTracker, "batteryChargingTracker");
        oy.j(batteryNotLowTracker, "batteryNotLowTracker");
        oy.j(constraintTracker2, "networkStateTracker");
    }

    public Trackers(Context context, TaskExecutor taskExecutor, ConstraintTracker<Boolean> constraintTracker, BatteryNotLowTracker batteryNotLowTracker, ConstraintTracker<NetworkState> constraintTracker2, ConstraintTracker<Boolean> constraintTracker3) {
        oy.j(context, "context");
        oy.j(taskExecutor, "taskExecutor");
        oy.j(constraintTracker, "batteryChargingTracker");
        oy.j(batteryNotLowTracker, "batteryNotLowTracker");
        oy.j(constraintTracker2, "networkStateTracker");
        oy.j(constraintTracker3, "storageNotLowTracker");
        this.batteryChargingTracker = constraintTracker;
        this.batteryNotLowTracker = batteryNotLowTracker;
        this.networkStateTracker = constraintTracker2;
        this.storageNotLowTracker = constraintTracker3;
    }

    /*  JADX ERROR: NullPointerException in pass: InitCodeVariables
        java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.SSAVar.getPhiList()" because "resultVar" is null
        	at jadx.core.dex.visitors.InitCodeVariables.collectConnectedVars(InitCodeVariables.java:119)
        	at jadx.core.dex.visitors.InitCodeVariables.setCodeVar(InitCodeVariables.java:82)
        	at jadx.core.dex.visitors.InitCodeVariables.initCodeVar(InitCodeVariables.java:74)
        	at jadx.core.dex.visitors.InitCodeVariables.initCodeVars(InitCodeVariables.java:48)
        	at jadx.core.dex.visitors.InitCodeVariables.visit(InitCodeVariables.java:29)
        */
    public /* synthetic */ Trackers(android.content.Context r8, androidx.work.impl.utils.taskexecutor.TaskExecutor r9, androidx.work.impl.constraints.trackers.ConstraintTracker r10, androidx.work.impl.constraints.trackers.BatteryNotLowTracker r11, androidx.work.impl.constraints.trackers.ConstraintTracker r12, androidx.work.impl.constraints.trackers.ConstraintTracker r13, int r14, defpackage.aj r15) {
        /*
            r7 = this;
            r0 = r14 & 4
            java.lang.String r1 = "context.applicationContext"
            if (r0 == 0) goto L14
            androidx.work.impl.constraints.trackers.BatteryChargingTracker r0 = new androidx.work.impl.constraints.trackers.BatteryChargingTracker
            android.content.Context r3 = r8.getApplicationContext()
            defpackage.oy.i(r3, r1)
            r0.<init>(r3, r9)
            r3 = r0
            goto L15
        L14:
            r3 = r10
        L15:
            r0 = r14 & 8
            if (r0 == 0) goto L27
            androidx.work.impl.constraints.trackers.BatteryNotLowTracker r0 = new androidx.work.impl.constraints.trackers.BatteryNotLowTracker
            android.content.Context r4 = r8.getApplicationContext()
            defpackage.oy.i(r4, r1)
            r0.<init>(r4, r9)
            r4 = r0
            goto L28
        L27:
            r4 = r11
        L28:
            r0 = r14 & 16
            if (r0 == 0) goto L39
            android.content.Context r0 = r8.getApplicationContext()
            defpackage.oy.i(r0, r1)
            androidx.work.impl.constraints.trackers.ConstraintTracker r0 = androidx.work.impl.constraints.trackers.NetworkStateTrackerKt.NetworkStateTracker(r0, r9)
            r5 = r0
            goto L3a
        L39:
            r5 = r12
        L3a:
            r0 = r14 & 32
            if (r0 == 0) goto L4f
            androidx.work.impl.constraints.trackers.StorageNotLowTracker r0 = new androidx.work.impl.constraints.trackers.StorageNotLowTracker
            android.content.Context r6 = r8.getApplicationContext()
            defpackage.oy.i(r6, r1)
            r0.<init>(r6, r9)
            r6 = r0
            r1 = r8
            r2 = r9
            r0 = r7
            goto L53
        L4f:
            r6 = r13
            r0 = r7
            r1 = r8
            r2 = r9
        L53:
            r0.<init>(r1, r2, r3, r4, r5, r6)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.work.impl.constraints.trackers.Trackers.<init>(android.content.Context, androidx.work.impl.utils.taskexecutor.TaskExecutor, androidx.work.impl.constraints.trackers.ConstraintTracker, androidx.work.impl.constraints.trackers.BatteryNotLowTracker, androidx.work.impl.constraints.trackers.ConstraintTracker, androidx.work.impl.constraints.trackers.ConstraintTracker, int, aj):void");
    }
}
