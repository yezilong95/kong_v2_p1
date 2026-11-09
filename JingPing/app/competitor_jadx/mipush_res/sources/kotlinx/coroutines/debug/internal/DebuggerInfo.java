package kotlinx.coroutines.debug.internal;

import defpackage.bj;
import defpackage.gj;
import defpackage.hj;
import defpackage.is;
import defpackage.lm;
import defpackage.vi;
import defpackage.zn;
import java.io.Serializable;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class DebuggerInfo implements Serializable {
    public final Long E;
    public final String F;

    public DebuggerInfo(lm lmVar, bj bjVar) {
        zn.s(bjVar.get(gj.F));
        this.E = null;
        vi viVar = (vi) bjVar.get(is.F);
        this.F = viVar != null ? viVar.toString() : null;
        bjVar.get(hj.F).getClass();
        throw new ClassCastException();
    }

    public final Long getCoroutineId() {
        return this.E;
    }

    public final String getDispatcher() {
        return this.F;
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
