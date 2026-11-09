package androidx.work.impl.utils.taskexecutor;

import java.util.concurrent.Executor;

/* JADX INFO: loaded from: classes.dex */
public interface TaskExecutor {
    default void executeOnTaskThread(Runnable runnable) {
        getSerialTaskExecutor().execute(runnable);
    }

    Executor getMainThreadExecutor();

    SerialExecutor getSerialTaskExecutor();
}
