package androidx.work.impl.background.systemalarm;

import android.content.Context;
import android.os.PowerManager;
import androidx.work.Logger;
import androidx.work.impl.StartStopToken;
import androidx.work.impl.background.systemalarm.SystemAlarmDispatcher;
import androidx.work.impl.constraints.WorkConstraintsCallback;
import androidx.work.impl.constraints.WorkConstraintsTrackerImpl;
import androidx.work.impl.constraints.trackers.Trackers;
import androidx.work.impl.model.WorkGenerationalId;
import androidx.work.impl.model.WorkSpec;
import androidx.work.impl.model.WorkSpecKt;
import androidx.work.impl.utils.WakeLocks;
import androidx.work.impl.utils.WorkTimer;
import defpackage.un;
import defpackage.zn;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Executor;

/* JADX INFO: loaded from: classes.dex */
public class DelayMetCommandHandler implements WorkConstraintsCallback, WorkTimer.TimeLimitExceededListener {
    private static final int STATE_INITIAL = 0;
    private static final int STATE_START_REQUESTED = 1;
    private static final int STATE_STOP_REQUESTED = 2;
    private static final String TAG = Logger.tagWithPrefix("DelayMetCommandHandler");
    private final Context mContext;
    private int mCurrentState;
    private final SystemAlarmDispatcher mDispatcher;
    private boolean mHasConstraints;
    private final Object mLock;
    private final Executor mMainThreadExecutor;
    private final Executor mSerialExecutor;
    private final int mStartId;
    private final StartStopToken mToken;
    private PowerManager.WakeLock mWakeLock;
    private final WorkConstraintsTrackerImpl mWorkConstraintsTracker;
    private final WorkGenerationalId mWorkGenerationalId;

    public DelayMetCommandHandler(Context context, int i, SystemAlarmDispatcher systemAlarmDispatcher, StartStopToken startStopToken) {
        this.mContext = context;
        this.mStartId = i;
        this.mDispatcher = systemAlarmDispatcher;
        this.mWorkGenerationalId = startStopToken.getId();
        this.mToken = startStopToken;
        Trackers trackers = systemAlarmDispatcher.getWorkManager().getTrackers();
        this.mSerialExecutor = systemAlarmDispatcher.getTaskExecutor().getSerialTaskExecutor();
        this.mMainThreadExecutor = systemAlarmDispatcher.getTaskExecutor().getMainThreadExecutor();
        this.mWorkConstraintsTracker = new WorkConstraintsTrackerImpl(trackers, this);
        this.mHasConstraints = false;
        this.mCurrentState = 0;
        this.mLock = new Object();
    }

    private void cleanUp() {
        synchronized (this.mLock) {
            try {
                this.mWorkConstraintsTracker.reset();
                this.mDispatcher.getWorkTimer().stopTimer(this.mWorkGenerationalId);
                PowerManager.WakeLock wakeLock = this.mWakeLock;
                if (wakeLock != null && wakeLock.isHeld()) {
                    Logger.get().debug(TAG, "Releasing wakelock " + this.mWakeLock + "for WorkSpec " + this.mWorkGenerationalId);
                    this.mWakeLock.release();
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startWork() {
        if (this.mCurrentState != 0) {
            Logger.get().debug(TAG, "Already started work for " + this.mWorkGenerationalId);
            return;
        }
        this.mCurrentState = 1;
        Logger.get().debug(TAG, "onAllConstraintsMet for " + this.mWorkGenerationalId);
        if (this.mDispatcher.getProcessor().startWork(this.mToken)) {
            this.mDispatcher.getWorkTimer().startTimer(this.mWorkGenerationalId, 600000L, this);
        } else {
            cleanUp();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void stopWork() {
        String workSpecId = this.mWorkGenerationalId.getWorkSpecId();
        if (this.mCurrentState >= 2) {
            Logger.get().debug(TAG, "Already stopped work for " + workSpecId);
            return;
        }
        this.mCurrentState = 2;
        Logger logger = Logger.get();
        String str = TAG;
        logger.debug(str, "Stopping work for WorkSpec " + workSpecId);
        this.mMainThreadExecutor.execute(new SystemAlarmDispatcher.AddRunnable(this.mDispatcher, CommandHandler.createStopWorkIntent(this.mContext, this.mWorkGenerationalId), this.mStartId));
        if (!this.mDispatcher.getProcessor().isEnqueued(this.mWorkGenerationalId.getWorkSpecId())) {
            Logger.get().debug(str, "Processor does not have WorkSpec " + workSpecId + ". No need to reschedule");
            return;
        }
        Logger.get().debug(str, "WorkSpec " + workSpecId + " needs to be rescheduled");
        this.mMainThreadExecutor.execute(new SystemAlarmDispatcher.AddRunnable(this.mDispatcher, CommandHandler.createScheduleWorkIntent(this.mContext, this.mWorkGenerationalId), this.mStartId));
    }

    public void handleProcessWork() {
        String workSpecId = this.mWorkGenerationalId.getWorkSpecId();
        Context context = this.mContext;
        StringBuilder sbQ = zn.q(workSpecId, " (");
        sbQ.append(this.mStartId);
        sbQ.append(")");
        this.mWakeLock = WakeLocks.newWakeLock(context, sbQ.toString());
        Logger logger = Logger.get();
        String str = TAG;
        logger.debug(str, "Acquiring wakelock " + this.mWakeLock + "for WorkSpec " + workSpecId);
        this.mWakeLock.acquire();
        WorkSpec workSpec = this.mDispatcher.getWorkManager().getWorkDatabase().workSpecDao().getWorkSpec(workSpecId);
        if (workSpec == null) {
            this.mSerialExecutor.execute(new un(this, 0));
            return;
        }
        boolean zHasConstraints = workSpec.hasConstraints();
        this.mHasConstraints = zHasConstraints;
        if (zHasConstraints) {
            this.mWorkConstraintsTracker.replace(Collections.singletonList(workSpec));
            return;
        }
        Logger.get().debug(str, "No constraints for " + workSpecId);
        onAllConstraintsMet(Collections.singletonList(workSpec));
    }

    @Override // androidx.work.impl.constraints.WorkConstraintsCallback
    public void onAllConstraintsMet(List<WorkSpec> list) {
        Iterator<WorkSpec> it = list.iterator();
        while (it.hasNext()) {
            if (WorkSpecKt.generationalId(it.next()).equals(this.mWorkGenerationalId)) {
                this.mSerialExecutor.execute(new un(this, 1));
                return;
            }
        }
    }

    @Override // androidx.work.impl.constraints.WorkConstraintsCallback
    public void onAllConstraintsNotMet(List<WorkSpec> list) {
        this.mSerialExecutor.execute(new un(this, 0));
    }

    public void onExecuted(boolean z) {
        Logger.get().debug(TAG, "onExecuted " + this.mWorkGenerationalId + ", " + z);
        cleanUp();
        if (z) {
            this.mMainThreadExecutor.execute(new SystemAlarmDispatcher.AddRunnable(this.mDispatcher, CommandHandler.createScheduleWorkIntent(this.mContext, this.mWorkGenerationalId), this.mStartId));
        }
        if (this.mHasConstraints) {
            this.mMainThreadExecutor.execute(new SystemAlarmDispatcher.AddRunnable(this.mDispatcher, CommandHandler.createConstraintsChangedIntent(this.mContext), this.mStartId));
        }
    }

    @Override // androidx.work.impl.utils.WorkTimer.TimeLimitExceededListener
    public void onTimeLimitExceeded(WorkGenerationalId workGenerationalId) {
        Logger.get().debug(TAG, "Exceeded time limits on execution for " + workGenerationalId);
        this.mSerialExecutor.execute(new un(this, 0));
    }
}
