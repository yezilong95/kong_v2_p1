package androidx.work;

import android.content.Context;
import android.net.Network;
import android.net.Uri;
import androidx.work.impl.utils.futures.SettableFuture;
import androidx.work.impl.utils.taskexecutor.TaskExecutor;
import defpackage.ca0;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.Executor;

/* JADX INFO: loaded from: classes.dex */
public abstract class ListenableWorker {
    private Context mAppContext;
    private volatile boolean mStopped;
    private boolean mUsed;
    private WorkerParameters mWorkerParams;

    public static abstract class Result {

        public static final class Failure extends Result {
            private final Data mOutputData;

            public Failure() {
                this(Data.EMPTY);
            }

            public boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                if (obj == null || Failure.class != obj.getClass()) {
                    return false;
                }
                return this.mOutputData.equals(((Failure) obj).mOutputData);
            }

            @Override // androidx.work.ListenableWorker.Result
            public Data getOutputData() {
                return this.mOutputData;
            }

            public int hashCode() {
                return this.mOutputData.hashCode() + 846803280;
            }

            public String toString() {
                return "Failure {mOutputData=" + this.mOutputData + '}';
            }

            public Failure(Data data) {
                this.mOutputData = data;
            }
        }

        public static final class Retry extends Result {
            public boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                return obj != null && Retry.class == obj.getClass();
            }

            @Override // androidx.work.ListenableWorker.Result
            public Data getOutputData() {
                return Data.EMPTY;
            }

            public int hashCode() {
                return 25945934;
            }

            public String toString() {
                return "Retry";
            }
        }

        public static final class Success extends Result {
            private final Data mOutputData;

            public Success() {
                this(Data.EMPTY);
            }

            public boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                if (obj == null || Success.class != obj.getClass()) {
                    return false;
                }
                return this.mOutputData.equals(((Success) obj).mOutputData);
            }

            @Override // androidx.work.ListenableWorker.Result
            public Data getOutputData() {
                return this.mOutputData;
            }

            public int hashCode() {
                return this.mOutputData.hashCode() - 1876823561;
            }

            public String toString() {
                return "Success {mOutputData=" + this.mOutputData + '}';
            }

            public Success(Data data) {
                this.mOutputData = data;
            }
        }

        public static Result failure() {
            return new Failure();
        }

        public static Result retry() {
            return new Retry();
        }

        public static Result success() {
            return new Success();
        }

        public abstract Data getOutputData();

        public static Result failure(Data data) {
            return new Failure(data);
        }

        public static Result success(Data data) {
            return new Success(data);
        }
    }

    public ListenableWorker(Context context, WorkerParameters workerParameters) {
        if (context == null) {
            throw new IllegalArgumentException("Application Context is null");
        }
        if (workerParameters == null) {
            throw new IllegalArgumentException("WorkerParameters is null");
        }
        this.mAppContext = context;
        this.mWorkerParams = workerParameters;
    }

    public final Context getApplicationContext() {
        return this.mAppContext;
    }

    public Executor getBackgroundExecutor() {
        return this.mWorkerParams.getBackgroundExecutor();
    }

    public ca0 getForegroundInfoAsync() {
        SettableFuture settableFutureCreate = SettableFuture.create();
        settableFutureCreate.setException(new IllegalStateException("Expedited WorkRequests require a ListenableWorker to provide an implementation for `getForegroundInfoAsync()`"));
        return settableFutureCreate;
    }

    public final UUID getId() {
        return this.mWorkerParams.getId();
    }

    public final Data getInputData() {
        return this.mWorkerParams.getInputData();
    }

    public final Network getNetwork() {
        return this.mWorkerParams.getNetwork();
    }

    public final int getRunAttemptCount() {
        return this.mWorkerParams.getRunAttemptCount();
    }

    public final Set<String> getTags() {
        return this.mWorkerParams.getTags();
    }

    public TaskExecutor getTaskExecutor() {
        return this.mWorkerParams.getTaskExecutor();
    }

    public final List<String> getTriggeredContentAuthorities() {
        return this.mWorkerParams.getTriggeredContentAuthorities();
    }

    public final List<Uri> getTriggeredContentUris() {
        return this.mWorkerParams.getTriggeredContentUris();
    }

    public WorkerFactory getWorkerFactory() {
        return this.mWorkerParams.getWorkerFactory();
    }

    public final boolean isStopped() {
        return this.mStopped;
    }

    public final boolean isUsed() {
        return this.mUsed;
    }

    public void onStopped() {
    }

    public final ca0 setForegroundAsync(ForegroundInfo foregroundInfo) {
        return this.mWorkerParams.getForegroundUpdater().setForegroundAsync(getApplicationContext(), getId(), foregroundInfo);
    }

    public ca0 setProgressAsync(Data data) {
        return this.mWorkerParams.getProgressUpdater().updateProgress(getApplicationContext(), getId(), data);
    }

    public final void setUsed() {
        this.mUsed = true;
    }

    public abstract ca0 startWork();

    public final void stop() {
        this.mStopped = true;
        onStopped();
    }
}
