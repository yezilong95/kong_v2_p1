package kotlin.coroutines;

import defpackage.bq0;
import defpackage.lh;
import defpackage.mh;
import defpackage.nh;
import defpackage.oe;
import defpackage.oy;
import defpackage.pe;
import defpackage.qe;
import defpackage.ru;
import java.io.Serializable;
import kotlin.jvm.internal.Ref$IntRef;
import org.java_websocket.extensions.ExtensionRequestData;

/* JADX INFO: loaded from: classes.dex */
public final class CombinedContext implements nh, Serializable {
    public final nh b;
    public final lh c;

    public CombinedContext(nh nhVar, lh lhVar) {
        oy.j(nhVar, "left");
        oy.j(lhVar, "element");
        this.b = nhVar;
        this.c = lhVar;
    }

    private final Object writeReplace() {
        int iB = b();
        nh[] nhVarArr = new nh[iB];
        Ref$IntRef ref$IntRef = new Ref$IntRef();
        fold(bq0.a, new qe(nhVarArr, ref$IntRef));
        if (ref$IntRef.element == iB) {
            return new oe(nhVarArr);
        }
        throw new IllegalStateException("Check failed.");
    }

    public final int b() {
        int i = 2;
        CombinedContext combinedContext = this;
        while (true) {
            nh nhVar = combinedContext.b;
            combinedContext = nhVar instanceof CombinedContext ? (CombinedContext) nhVar : null;
            if (combinedContext == null) {
                return i;
            }
            i++;
        }
    }

    public boolean equals(Object obj) {
        boolean zC;
        if (this == obj) {
            return true;
        }
        if (obj instanceof CombinedContext) {
            CombinedContext combinedContext = (CombinedContext) obj;
            if (combinedContext.b() == b()) {
                CombinedContext combinedContext2 = this;
                while (true) {
                    lh lhVar = combinedContext2.c;
                    if (!oy.c(combinedContext.get(lhVar.getKey()), lhVar)) {
                        zC = false;
                        break;
                    }
                    nh nhVar = combinedContext2.b;
                    if (!(nhVar instanceof CombinedContext)) {
                        lh lhVar2 = (lh) nhVar;
                        zC = oy.c(combinedContext.get(lhVar2.getKey()), lhVar2);
                        break;
                    }
                    combinedContext2 = (CombinedContext) nhVar;
                }
                if (zC) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override // defpackage.nh
    public <R> R fold(R r, ru ruVar) {
        oy.j(ruVar, "operation");
        return (R) ruVar.invoke(this.b.fold(r, ruVar), this.c);
    }

    @Override // defpackage.nh
    public <E extends lh> E get(mh mhVar) {
        oy.j(mhVar, "key");
        CombinedContext combinedContext = this;
        while (true) {
            E e = (E) combinedContext.c.get(mhVar);
            if (e != null) {
                return e;
            }
            nh nhVar = combinedContext.b;
            if (!(nhVar instanceof CombinedContext)) {
                return (E) nhVar.get(mhVar);
            }
            combinedContext = (CombinedContext) nhVar;
        }
    }

    public int hashCode() {
        return this.c.hashCode() + this.b.hashCode();
    }

    @Override // defpackage.nh
    public nh minusKey(mh mhVar) {
        oy.j(mhVar, "key");
        lh lhVar = this.c;
        lh lhVar2 = lhVar.get(mhVar);
        nh nhVar = this.b;
        if (lhVar2 != null) {
            return nhVar;
        }
        nh nhVarMinusKey = nhVar.minusKey(mhVar);
        return nhVarMinusKey == nhVar ? this : nhVarMinusKey == EmptyCoroutineContext.INSTANCE ? lhVar : new CombinedContext(nhVarMinusKey, lhVar);
    }

    @Override // defpackage.nh
    public nh plus(nh nhVar) {
        oy.j(nhVar, "context");
        return nhVar == EmptyCoroutineContext.INSTANCE ? this : (nh) nhVar.fold(this, pe.f);
    }

    public String toString() {
        return "[" + ((String) fold(ExtensionRequestData.EMPTY_VALUE, pe.c)) + ']';
    }
}
