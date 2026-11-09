package kotlin;

import defpackage.jf0;
import defpackage.oy;
import java.io.Serializable;

/* JADX INFO: loaded from: classes.dex */
public final class Result<T> implements Serializable {
    public static final jf0 Companion = new jf0();
    public final Object b;

    public static final class Failure implements Serializable {
        public final Throwable exception;

        public Failure(Throwable th) {
            oy.j(th, "exception");
            this.exception = th;
        }

        public boolean equals(Object obj) {
            return (obj instanceof Failure) && oy.c(this.exception, ((Failure) obj).exception);
        }

        public int hashCode() {
            return this.exception.hashCode();
        }

        public String toString() {
            return "Failure(" + this.exception + ')';
        }
    }

    public /* synthetic */ Result(Object obj) {
        this.b = obj;
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
        return (obj2 instanceof Result) && oy.c(obj, ((Result) obj2).m11unboximpl());
    }

    /* JADX INFO: renamed from: equals-impl0, reason: not valid java name */
    public static final boolean m5equalsimpl0(Object obj, Object obj2) {
        return oy.c(obj, obj2);
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
        return m4equalsimpl(this.b, obj);
    }

    public int hashCode() {
        return m7hashCodeimpl(this.b);
    }

    public String toString() {
        return m10toStringimpl(this.b);
    }

    /* JADX INFO: renamed from: unbox-impl, reason: not valid java name */
    public final /* synthetic */ Object m11unboximpl() {
        return this.b;
    }
}
