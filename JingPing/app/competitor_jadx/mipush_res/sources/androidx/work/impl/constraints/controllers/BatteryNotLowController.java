package androidx.work.impl.constraints.controllers;

import androidx.work.impl.constraints.trackers.BatteryNotLowTracker;
import androidx.work.impl.model.WorkSpec;
import defpackage.t40;

/* JADX INFO: loaded from: classes.dex */
public final class BatteryNotLowController extends ConstraintController<Boolean> {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public BatteryNotLowController(BatteryNotLowTracker batteryNotLowTracker) {
        super(batteryNotLowTracker);
        t40.n(batteryNotLowTracker, "tracker");
    }

    @Override // androidx.work.impl.constraints.controllers.ConstraintController
    public boolean hasConstraint(WorkSpec workSpec) {
        t40.n(workSpec, "workSpec");
        return workSpec.constraints.requiresBatteryNotLow();
    }

    public boolean isConstrained(boolean z) {
        return !z;
    }

    @Override // androidx.work.impl.constraints.controllers.ConstraintController
    public /* bridge */ /* synthetic */ boolean isConstrained(Boolean bool) {
        return isConstrained(bool.booleanValue());
    }
}
