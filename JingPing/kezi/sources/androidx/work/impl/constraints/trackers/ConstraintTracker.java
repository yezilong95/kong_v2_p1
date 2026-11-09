package androidx.work.impl.constraints.trackers;

import android.content.Context;
import androidx.work.Logger;
import androidx.work.impl.constraints.ConstraintListener;
import androidx.work.impl.utils.taskexecutor.TaskExecutor;
import defpackage.ae;
import defpackage.l6;
import defpackage.oy;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public abstract class ConstraintTracker<T> {
    private final Context appContext;
    private T currentState;
    private final LinkedHashSet<ConstraintListener<T>> listeners;
    private final Object lock;
    private final TaskExecutor taskExecutor;

    public ConstraintTracker(Context context, TaskExecutor taskExecutor) {
        oy.j(context, "context");
        oy.j(taskExecutor, "taskExecutor");
        this.taskExecutor = taskExecutor;
        Context applicationContext = context.getApplicationContext();
        oy.i(applicationContext, "context.applicationContext");
        this.appContext = applicationContext;
        this.lock = new Object();
        this.listeners = new LinkedHashSet<>();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void _set_state_$lambda$4$lambda$3(List list, ConstraintTracker constraintTracker) {
        oy.j(list, "$listenersList");
        oy.j(constraintTracker, "this$0");
        Iterator<T> it = list.iterator();
        while (it.hasNext()) {
            ((ConstraintListener) it.next()).onConstraintChanged(constraintTracker.currentState);
        }
    }

    public final void addListener(ConstraintListener<T> constraintListener) {
        oy.j(constraintListener, "listener");
        synchronized (this.lock) {
            try {
                if (this.listeners.add(constraintListener)) {
                    if (this.listeners.size() == 1) {
                        this.currentState = getInitialState();
                        Logger.get().debug(ConstraintTrackerKt.TAG, getClass().getSimpleName() + ": initial state = " + this.currentState);
                        startTracking();
                    }
                    constraintListener.onConstraintChanged(this.currentState);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final Context getAppContext() {
        return this.appContext;
    }

    public abstract T getInitialState();

    public final T getState() {
        T t = this.currentState;
        return t == null ? getInitialState() : t;
    }

    public final void removeListener(ConstraintListener<T> constraintListener) {
        oy.j(constraintListener, "listener");
        synchronized (this.lock) {
            if (this.listeners.remove(constraintListener) && this.listeners.isEmpty()) {
                stopTracking();
            }
        }
    }

    public final void setState(T t) {
        synchronized (this.lock) {
            T t2 = this.currentState;
            if (t2 == null || !t2.equals(t)) {
                this.currentState = t;
                this.taskExecutor.getMainThreadExecutor().execute(new l6(ae.f0(this.listeners), 1, this));
            }
        }
    }

    public abstract void startTracking();

    public abstract void stopTracking();
}
