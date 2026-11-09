package kotlinx.coroutines;

import defpackage.f50;
import defpackage.t40;
import java.util.concurrent.CancellationException;

/* JADX INFO: loaded from: classes.dex */
public final class JobCancellationException extends CancellationException {
    public final transient f50 job;

    public JobCancellationException(String str, Throwable th, f50 f50Var) {
        super(str);
        this.job = f50Var;
        if (th != null) {
            initCause(th);
        }
    }

    public JobCancellationException createCopy() {
        return null;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof JobCancellationException)) {
            return false;
        }
        JobCancellationException jobCancellationException = (JobCancellationException) obj;
        return t40.f(jobCancellationException.getMessage(), getMessage()) && t40.f(jobCancellationException.job, this.job) && t40.f(jobCancellationException.getCause(), getCause());
    }

    @Override // java.lang.Throwable
    public Throwable fillInStackTrace() {
        setStackTrace(new StackTraceElement[0]);
        return this;
    }

    public int hashCode() {
        String message = getMessage();
        t40.k(message);
        int iHashCode = (this.job.hashCode() + (message.hashCode() * 31)) * 31;
        Throwable cause = getCause();
        return iHashCode + (cause != null ? cause.hashCode() : 0);
    }

    @Override // java.lang.Throwable
    public String toString() {
        return super.toString() + "; job=" + this.job;
    }
}
