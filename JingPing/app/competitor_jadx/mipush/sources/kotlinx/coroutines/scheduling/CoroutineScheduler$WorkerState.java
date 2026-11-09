package kotlinx.coroutines.scheduling;

import defpackage.gu;
import defpackage.hu;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes.dex */
public final class CoroutineScheduler$WorkerState {
    public static final CoroutineScheduler$WorkerState BLOCKING;
    public static final CoroutineScheduler$WorkerState CPU_ACQUIRED;
    public static final CoroutineScheduler$WorkerState DORMANT;
    public static final /* synthetic */ CoroutineScheduler$WorkerState[] E;
    public static final /* synthetic */ hu F;
    public static final CoroutineScheduler$WorkerState PARKING;
    public static final CoroutineScheduler$WorkerState TERMINATED;

    static {
        CoroutineScheduler$WorkerState coroutineScheduler$WorkerState = new CoroutineScheduler$WorkerState("CPU_ACQUIRED", 0);
        CPU_ACQUIRED = coroutineScheduler$WorkerState;
        CoroutineScheduler$WorkerState coroutineScheduler$WorkerState2 = new CoroutineScheduler$WorkerState("BLOCKING", 1);
        BLOCKING = coroutineScheduler$WorkerState2;
        CoroutineScheduler$WorkerState coroutineScheduler$WorkerState3 = new CoroutineScheduler$WorkerState("PARKING", 2);
        PARKING = coroutineScheduler$WorkerState3;
        CoroutineScheduler$WorkerState coroutineScheduler$WorkerState4 = new CoroutineScheduler$WorkerState("DORMANT", 3);
        DORMANT = coroutineScheduler$WorkerState4;
        CoroutineScheduler$WorkerState coroutineScheduler$WorkerState5 = new CoroutineScheduler$WorkerState("TERMINATED", 4);
        TERMINATED = coroutineScheduler$WorkerState5;
        CoroutineScheduler$WorkerState[] coroutineScheduler$WorkerStateArr = {coroutineScheduler$WorkerState, coroutineScheduler$WorkerState2, coroutineScheduler$WorkerState3, coroutineScheduler$WorkerState4, coroutineScheduler$WorkerState5};
        E = coroutineScheduler$WorkerStateArr;
        F = new hu(coroutineScheduler$WorkerStateArr);
    }

    public static gu getEntries() {
        return F;
    }

    public static CoroutineScheduler$WorkerState valueOf(String str) {
        return (CoroutineScheduler$WorkerState) Enum.valueOf(CoroutineScheduler$WorkerState.class, str);
    }

    public static CoroutineScheduler$WorkerState[] values() {
        return (CoroutineScheduler$WorkerState[]) E.clone();
    }
}
