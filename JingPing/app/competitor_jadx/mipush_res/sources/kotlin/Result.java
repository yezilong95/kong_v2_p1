package kotlin;

import defpackage.ep0;
import defpackage.t40;
import java.io.Serializable;

/* JADX INFO: loaded from: classes.dex */
public final class Result<T> implements Serializable {
    public static final ep0 Companion = new ep0();
    public final Object E;

    public static final class Failure implements Serializable {
        public final Throwable exception;

        public Failure(Throwable th) {
            t40.n(th, "exception");
            this.exception = th;
        }

        public boolean equals(Object obj) {
            return (obj instanceof Failure) && t40.f(this.exception, ((Failure) obj).exception);
        }

        public int hashCode() {
            return this.exception.hashCode();
        }

        public String toString() {
            return "Failure(" + this.exception + ')';
        }
    }

    public /* synthetic */ Result(Object obj) {
        this.E = obj;
    }

    /* JADX INFO: renamed from: box-impl, reason: not valid java name */
    public static final /* synthetic */ Result m2boximpl(Object obj) {
        return new Result(obj);
    }

    /* JADX INFO: renamed from: constructor-impl, reason: not valid java name */
    public static <T> Object m3constructorimpl(Object obj) {
        return obj;
    }

    /* JADX INFO: renamed from: equals-impl, reason: not valid java name */
    public static boolean m4equalsimpl(Object obj, Object obj2) {
        return (obj2 instanceof Result) && t40.f(obj, ((Result) obj2).m11unboximpl());
    }

    /* JADX INFO: renamed from: equals-impl0, reason: not valid java name */
    public static final boolean m5equalsimpl0(Object obj, Object obj2) {
        return t40.f(obj, obj2);
    }

    /* JADX INFO: renamed from: exceptionOrNull-impl, reason: not valid java name */
    public static final Throwable m6exceptionOrNullimpl(Object obj) {
        if (obj instanceof Failure) {
            return ((Failure) obj).exception;
        }
        return null;
    }

    public static /* synthetic */ void getValue$annotations() {
    }

    /* JADX INFO: renamed from: hashCode-impl, reason: not valid java name */
    public static int m7hashCodeimpl(Object obj) {
        if (obj == null) {
            return 0;
        }
        return obj.hashCode();
    }

    /* JADX INFO: renamed from: isFailure-impl, reason: not valid java name */
    public static final boolean m8isFailureimpl(Object obj) {
        return obj instanceof Failure;
    }

    /* JADX INFO: renamed from: isSuccess-impl, reason: not valid java name */
    public static final boolean m9isSuccessimpl(Object obj) {
        return !(obj instanceof Failure);
    }

    /* JADX INFO: renamed from: toString-impl, reason: not valid java name */
    public static String m10toStringimpl(Object obj) {
        if (obj instanceof Failure) {
            return obj.toString();
        }
        return "Success(" + obj + ')';
    }

    public boolean equals(Object obj) {
        return m4equalsimpl(this.E, obj);
    }

    public int hashCode() {
        return m7hashCodeimpl(this.E);
    }

    public String toString() {
        return m10toStringimpl(this.E);
    }

    /* JADX INFO: renamed from: unbox-impl, reason: not valid java name */
    public final /* synthetic */ Object m11unboximpl() {
        return this.E;
    }
}
