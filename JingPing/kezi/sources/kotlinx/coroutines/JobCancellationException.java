package kotlinx.coroutines;

import defpackage.bz;
import defpackage.oy;
import java.util.concurrent.CancellationException;

/* JADX INFO: loaded from: classes.dex */
public final class JobCancellationException extends CancellationException {
    public final transient bz job;

    public JobCancellationException(String str, Throwable th, bz bzVar) {
        super(str);
        this.job = bzVar;
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
        return oy.c(jobCancellationException.getMessage(), getMessage()) && oy.c(jobCancellationException.job, this.job) && oy.c(jobCancellationException.getCause(), getCause());
    }

    @Override // java.lang.Throwable
    public Throwable fillInStackTrace() {
        setStackTrace(new StackTraceElement[0]);
        return this;
    }

    public int hashCode() {
        String message = getMessage();
        oy.g(message);
        int iHashCode = (this.job.hashCode() + (message.hashCode() * 31)) * 31;
        Throwable cause = getCause();
        return iHashCode + (cause != null ? cause.hashCode() : 0);
    }

    @Override // java.lang.Throwable
    public String toString() {
        return super.toString() + "; job=" + this.job;
    }
}
