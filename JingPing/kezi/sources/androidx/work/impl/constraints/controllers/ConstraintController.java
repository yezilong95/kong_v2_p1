package androidx.work.impl.constraints.controllers;

import androidx.work.impl.constraints.ConstraintListener;
import androidx.work.impl.constraints.trackers.ConstraintTracker;
import androidx.work.impl.model.WorkSpec;
import defpackage.oy;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public abstract class ConstraintController<T> implements ConstraintListener<T> {
    private OnConstraintUpdatedCallback callback;
    private T currentValue;
    private final List<String> matchingWorkSpecIds;
    private final List<WorkSpec> matchingWorkSpecs;
    private final ConstraintTracker<T> tracker;

    public interface OnConstraintUpdatedCallback {
        void onConstraintMet(List<WorkSpec> list);

        void onConstraintNotMet(List<WorkSpec> list);
    }

    public ConstraintController(ConstraintTracker<T> constraintTracker) {
        oy.j(constraintTracker, "tracker");
        this.tracker = constraintTracker;
        this.matchingWorkSpecs = new ArrayList();
        this.matchingWorkSpecIds = new ArrayList();
    }

    private final void updateCallback(OnConstraintUpdatedCallback onConstraintUpdatedCallback, T t) {
        if (this.matchingWorkSpecs.isEmpty() || onConstraintUpdatedCallback == null) {
            return;
        }
        if (t == null || isConstrained(t)) {
            onConstraintUpdatedCallback.onConstraintNotMet(this.matchingWorkSpecs);
        } else {
            onConstraintUpdatedCallback.onConstraintMet(this.matchingWorkSpecs);
        }
    }

    public final OnConstraintUpdatedCallback getCallback() {
        return this.callback;
    }

    public abstract boolean hasConstraint(WorkSpec workSpec);

    public abstract boolean isConstrained(T t);

    public final boolean isWorkSpecConstrained(String str) {
        oy.j(str, "workSpecId");
        T t = this.currentValue;
        return t != null && isConstrained(t) && this.matchingWorkSpecIds.contains(str);
    }

    @Override // androidx.work.impl.constraints.ConstraintListener
    public void onConstraintChanged(T t) {
        this.currentValue = t;
        updateCallback(this.callback, t);
    }

    public final void replace(Iterable<WorkSpec> iterable) {
        oy.j(iterable, "workSpecs");
        this.matchingWorkSpecs.clear();
        this.matchingWorkSpecIds.clear();
        List<WorkSpec> list = this.matchingWorkSpecs;
        for (WorkSpec workSpec : iterable) {
            if (hasConstraint(workSpec)) {
                list.add(workSpec);
            }
        }
        List<WorkSpec> list2 = this.matchingWorkSpecs;
        List<String> list3 = this.matchingWorkSpecIds;
        Iterator<T> it = list2.iterator();
        while (it.hasNext()) {
            list3.add(((WorkSpec) it.next()).id);
        }
        if (this.matchingWorkSpecs.isEmpty()) {
            this.tracker.removeListener(this);
        } else {
            this.tracker.addListener(this);
        }
        updateCallback(this.callback, this.currentValue);
    }

    public final void reset() {
        if (this.matchingWorkSpecs.isEmpty()) {
            return;
        }
        this.matchingWorkSpecs.clear();
        this.tracker.removeListener(this);
    }

    public final void setCallback(OnConstraintUpdatedCallback onConstraintUpdatedCallback) {
        if (this.callback != onConstraintUpdatedCallback) {
            this.callback = onConstraintUpdatedCallback;
            updateCallback(onConstraintUpdatedCallback, this.currentValue);
        }
    }
}
