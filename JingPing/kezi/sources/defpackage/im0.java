package defpackage;

import java.io.Serializable;
import kotlin.InitializedLazyImpl;

/* JADX INFO: loaded from: classes.dex */
public final class im0 implements i10, Serializable {
    public ou b;
    public volatile Object c;
    public final Object d;

    public im0(ou ouVar) {
        oy.j(ouVar, "initializer");
        this.b = ouVar;
        this.c = qm.k;
        this.d = this;
    }

    private final Object writeReplace() {
        return new InitializedLazyImpl(getValue());
    }

    @Override // defpackage.i10
    public final Object getValue() {
        Object objInvoke;
        Object obj = this.c;
        qm qmVar = qm.k;
        if (obj != qmVar) {
            return obj;
        }
        synchronized (this.d) {
            objInvoke = this.c;
            if (objInvoke == qmVar) {
                ou ouVar = this.b;
                oy.g(ouVar);
                objInvoke = ouVar.invoke();
                this.c = objInvoke;
                this.b = null;
            }
        }
        return objInvoke;
    }

    public final String toString() {
        return this.c != qm.k ? String.valueOf(getValue()) : "Lazy value not initialized yet.";
    }
}
