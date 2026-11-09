package androidx.work.impl.constraints;

import androidx.work.Logger;
import androidx.work.impl.constraints.controllers.BatteryChargingController;
import androidx.work.impl.constraints.controllers.BatteryNotLowController;
import androidx.work.impl.constraints.controllers.ConstraintController;
import androidx.work.impl.constraints.controllers.NetworkConnectedController;
import androidx.work.impl.constraints.controllers.NetworkMeteredController;
import androidx.work.impl.constraints.controllers.NetworkNotRoamingController;
import androidx.work.impl.constraints.controllers.NetworkUnmeteredController;
import androidx.work.impl.constraints.controllers.StorageNotLowController;
import androidx.work.impl.constraints.trackers.Trackers;
import androidx.work.impl.model.WorkSpec;
import defpackage.t40;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class WorkConstraintsTrackerImpl implements WorkConstraintsTracker, ConstraintController.OnConstraintUpdatedCallback {
    private final WorkConstraintsCallback callback;
    private final ConstraintController<?>[] constraintControllers;
    private final Object lock;

    public WorkConstraintsTrackerImpl(WorkConstraintsCallback workConstraintsCallback, ConstraintController<?>[] constraintControllerArr) {
        t40.n(constraintControllerArr, "constraintControllers");
        this.callback = workConstraintsCallback;
        this.constraintControllers = constraintControllerArr;
        this.lock = new Object();
    }

    public final boolean areAllConstraintsMet(String str) {
        ConstraintController<?> constraintController;
        boolean z;
        t40.n(str, "workSpecId");
        synchronized (this.lock) {
            try {
                ConstraintController<?>[] constraintControllerArr = this.constraintControllers;
                int length = constraintControllerArr.length;
                int i = 0;
                while (true) {
                    if (i >= length) {
                        constraintController = null;
                        break;
                    }
                    constraintController = constraintControllerArr[i];
                    if (constraintController.isWorkSpecConstrained(str)) {
                        break;
                    }
                    i++;
                }
                if (constraintController != null) {
                    Logger.get().debug(WorkConstraintsTrackerKt.TAG, "Work " + str + " constrained by " + constraintController.getClass().getSimpleName());
                }
                z = constraintController == null;
            } catch (Throwable th) {
                throw th;
            }
        }
        return z;
    }

    @Override // androidx.work.impl.constraints.controllers.ConstraintController.OnConstraintUpdatedCallback
    public void onConstraintMet(List<WorkSpec> list) {
        t40.n(list, "workSpecs");
        synchronized (this.lock) {
            try {
                ArrayList<WorkSpec> arrayList = new ArrayList();
                for (Object obj : list) {
                    if (areAllConstraintsMet(((WorkSpec) obj).id)) {
                        arrayList.add(obj);
                    }
                }
                for (WorkSpec workSpec : arrayList) {
                    Logger.get().debug(WorkConstraintsTrackerKt.TAG, "Constraints met for " + workSpec);
                }
                WorkConstraintsCallback workConstraintsCallback = this.callback;
                if (workConstraintsCallback != null) {
                    workConstraintsCallback.onAllConstraintsMet(arrayList);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    @Override // androidx.work.impl.constraints.controllers.ConstraintController.OnConstraintUpdatedCallback
    public void onConstraintNotMet(List<WorkSpec> list) {
        t40.n(list, "workSpecs");
        synchronized (this.lock) {
            WorkConstraintsCallback workConstraintsCallback = this.callback;
            if (workConstraintsCallback != null) {
                workConstraintsCallback.onAllConstraintsNotMet(list);
            }
        }
    }

    @Override // androidx.work.impl.constraints.WorkConstraintsTracker
    public void replace(Iterable<WorkSpec> iterable) {
        t40.n(iterable, "workSpecs");
        synchronized (this.lock) {
            try {
                for (ConstraintController<?> constraintController : this.constraintControllers) {
                    constraintController.setCallback(null);
                }
                for (ConstraintController<?> constraintController2 : this.constraintControllers) {
                    constraintController2.replace(iterable);
                }
                for (ConstraintController<?> constraintController3 : this.constraintControllers) {
                    constraintController3.setCallback(this);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    @Override // androidx.work.impl.constraints.WorkConstraintsTracker
    public void reset() {
        synchronized (this.lock) {
            for (ConstraintController<?> constraintController : this.constraintControllers) {
                constraintController.reset();
            }
        }
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public WorkConstraintsTrackerImpl(Trackers trackers, WorkConstraintsCallback workConstraintsCallback) {
        this(workConstraintsCallback, (ConstraintController<?>[]) new ConstraintController[]{new BatteryChargingController(trackers.getBatteryChargingTracker()), new BatteryNotLowController(trackers.getBatteryNotLowTracker()), new StorageNotLowController(trackers.getStorageNotLowTracker()), new NetworkConnectedController(trackers.getNetworkStateTracker()), new NetworkUnmeteredController(trackers.getNetworkStateTracker()), new NetworkNotRoamingController(trackers.getNetworkStateTracker()), new NetworkMeteredController(trackers.getNetworkStateTracker())});
        t40.n(trackers, "trackers");
    }
}
