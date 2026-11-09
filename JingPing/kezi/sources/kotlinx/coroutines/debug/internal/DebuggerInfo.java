package kotlinx.coroutines.debug.internal;

import defpackage.gh;
import defpackage.hk;
import defpackage.nh;
import defpackage.qm;
import defpackage.ri;
import defpackage.sh;
import defpackage.th;
import java.io.Serializable;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class DebuggerInfo implements Serializable {
    public final Long b;
    public final String c;

    public DebuggerInfo(ri riVar, nh nhVar) {
        hk.m(nhVar.get(sh.c));
        this.b = null;
        gh ghVar = (gh) nhVar.get(qm.c);
        this.c = ghVar != null ? ghVar.toString() : null;
        nhVar.get(th.c).getClass();
        throw new ClassCastException();
    }

    public final Long getCoroutineId() {
        return this.b;
    }

    public final String getDispatcher() {
        return this.c;
    }

    public final List<StackTraceElement> getLastObservedStackTrace() {
        return null;
    }

    public final String getLastObservedThreadName() {
        return null;
    }

    public final String getLastObservedThreadState() {
        return null;
    }

    public final String getName() {
        return null;
    }

    public final long getSequenceNumber() {
        return 0L;
    }

    public final String getState() {
        return null;
    }
}
