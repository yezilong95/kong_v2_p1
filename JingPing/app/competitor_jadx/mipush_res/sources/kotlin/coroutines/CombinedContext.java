package kotlin.coroutines;

import defpackage.aj;
import defpackage.bj;
import defpackage.d10;
import defpackage.ig;
import defpackage.jg;
import defpackage.kg;
import defpackage.t40;
import defpackage.y21;
import defpackage.zi;
import java.io.Serializable;
import kotlin.jvm.internal.Ref$IntRef;

/* JADX INFO: loaded from: classes.dex */
public final class CombinedContext implements bj, Serializable {
    public final bj E;
    public final zi F;

    public CombinedContext(bj bjVar, zi ziVar) {
        t40.n(bjVar, "left");
        t40.n(ziVar, "element");
        this.E = bjVar;
        this.F = ziVar;
    }

    private final Object writeReplace() {
        int iA = a();
        bj[] bjVarArr = new bj[iA];
        Ref$IntRef ref$IntRef = new Ref$IntRef();
        fold(y21.a, new kg(bjVarArr, ref$IntRef));
        if (ref$IntRef.element == iA) {
            return new ig(bjVarArr);
        }
        throw new IllegalStateException("Check failed.");
    }

    public final int a() {
        int i = 2;
        CombinedContext combinedContext = this;
        while (true) {
            bj bjVar = combinedContext.E;
            combinedContext = bjVar instanceof CombinedContext ? (CombinedContext) bjVar : null;
            if (combinedContext == null) {
                return i;
            }
            i++;
        }
    }

    public boolean equals(Object obj) {
        boolean zF;
        if (this == obj) {
            return true;
        }
        if (obj instanceof CombinedContext) {
            CombinedContext combinedContext = (CombinedContext) obj;
            if (combinedContext.a() == a()) {
                CombinedContext combinedContext2 = this;
                while (true) {
                    zi ziVar = combinedContext2.F;
                    if (!t40.f(combinedContext.get(ziVar.getKey()), ziVar)) {
                        zF = false;
                        break;
                    }
                    bj bjVar = combinedContext2.E;
                    if (!(bjVar instanceof CombinedContext)) {
                        zi ziVar2 = (zi) bjVar;
                        zF = t40.f(combinedContext.get(ziVar2.getKey()), ziVar2);
                        break;
                    }
                    combinedContext2 = (CombinedContext) bjVar;
                }
                if (zF) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override // defpackage.bj
    public <R> R fold(R r, d10 d10Var) {
        t40.n(d10Var, "operation");
        return (R) d10Var.invoke(this.E.fold(r, d10Var), this.F);
    }

    @Override // defpackage.bj
    public <E extends zi> E get(aj ajVar) {
        t40.n(ajVar, "key");
        CombinedContext combinedContext = this;
        while (true) {
            E e = (E) combinedContext.F.get(ajVar);
            if (e != null) {
                return e;
            }
            bj bjVar = combinedContext.E;
            if (!(bjVar instanceof CombinedContext)) {
                return (E) bjVar.get(ajVar);
            }
            combinedContext = (CombinedContext) bjVar;
        }
    }

    public int hashCode() {
        return this.F.hashCode() + this.E.hashCode();
    }

    @Override // defpackage.bj
    public bj minusKey(aj ajVar) {
        t40.n(ajVar, "key");
        zi ziVar = this.F;
        zi ziVar2 = ziVar.get(ajVar);
        bj bjVar = this.E;
        if (ziVar2 != null) {
            return bjVar;
        }
        bj bjVarMinusKey = bjVar.minusKey(ajVar);
        return bjVarMinusKey == bjVar ? this : bjVarMinusKey == EmptyCoroutineContext.INSTANCE ? ziVar : new CombinedContext(bjVarMinusKey, ziVar);
    }

    @Override // defpackage.bj
    public bj plus(bj bjVar) {
        t40.n(bjVar, "context");
        return bjVar == EmptyCoroutineContext.INSTANCE ? this : (bj) bjVar.fold(this, jg.I);
    }

    public String toString() {
        return "[" + ((String) fold("", jg.F)) + ']';
    }
}
