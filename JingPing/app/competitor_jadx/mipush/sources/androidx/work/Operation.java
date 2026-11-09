package androidx.work;

import android.annotation.SuppressLint;
import defpackage.ca0;
import defpackage.ea0;

/* JADX INFO: loaded from: classes.dex */
public interface Operation {

    @SuppressLint({"SyntheticAccessor"})
    public static final State.IN_PROGRESS IN_PROGRESS;

    @SuppressLint({"SyntheticAccessor"})
    public static final State.SUCCESS SUCCESS;

    public static abstract class State {

        public static final class FAILURE extends State {
            private final Throwable mThrowable;

            public FAILURE(Throwable th) {
                this.mThrowable = th;
            }

            public Throwable getThrowable() {
                return this.mThrowable;
            }

            public String toString() {
                return "FAILURE (" + this.mThrowable.getMessage() + ")";
            }
        }

        public static final class IN_PROGRESS extends State {
            public String toString() {
                return "IN_PROGRESS";
            }

            private IN_PROGRESS() {
            }
        }

        public static final class SUCCESS extends State {
            public String toString() {
                return "SUCCESS";
            }

            private SUCCESS() {
            }
        }
    }

    static {
        SUCCESS = new State.SUCCESS();
        IN_PROGRESS = new State.IN_PROGRESS();
    }

    ca0 getResult();

    ea0 getState();
}
