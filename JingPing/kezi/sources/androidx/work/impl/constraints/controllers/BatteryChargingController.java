package androidx.work.impl.constraints.controllers;

import androidx.work.impl.constraints.trackers.ConstraintTracker;
import androidx.work.impl.model.WorkSpec;
import defpackage.oy;

/* JADX INFO: loaded from: classes.dex */
public final class BatteryChargingController extends ConstraintController<Boolean> {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public BatteryChargingController(ConstraintTracker<Boolean> constraintTracker) {
        super(constraintTracker);
        oy.j(constraintTracker, "tracker");
    }

    @Override // androidx.work.impl.constraints.controllers.ConstraintController
    public boolean hasConstraint(WorkSpec workSpec) {
        oy.j(workSpec, "workSpec");
        return workSpec.constraints.requiresCharging();
    }

    public boolean isConstrained(boolean z) {
        return !z;
    }

    @Override // androidx.work.impl.constraints.controllers.ConstraintController
    public /* bridge */ /* synthetic */ boolean isConstrained(Boolean bool) {
        return isConstrained(bool.booleanValue());
    }
}
