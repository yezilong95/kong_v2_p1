package defpackage;

import kotlinx.coroutines.scheduling.CoroutineScheduler$WorkerState;

/* JADX INFO: loaded from: classes.dex */
public abstract /* synthetic */ class ij {
    public static final /* synthetic */ int[] a;

    static {
        int[] iArr = new int[CoroutineScheduler$WorkerState.values().length];
        try {
            iArr[CoroutineScheduler$WorkerState.PARKING.ordinal()] = 1;
        } catch (NoSuchFieldError unused) {
        }
        try {
            iArr[CoroutineScheduler$WorkerState.BLOCKING.ordinal()] = 2;
        } catch (NoSuchFieldError unused2) {
        }
        try {
            iArr[CoroutineScheduler$WorkerState.CPU_ACQUIRED.ordinal()] = 3;
        } catch (NoSuchFieldError unused3) {
        }
        try {
            iArr[CoroutineScheduler$WorkerState.DORMANT.ordinal()] = 4;
        } catch (NoSuchFieldError unused4) {
        }
        try {
            iArr[CoroutineScheduler$WorkerState.TERMINATED.ordinal()] = 5;
        } catch (NoSuchFieldError unused5) {
        }
        a = iArr;
    }
}
