package defpackage;

import java.util.Iterator;
import java.util.NoSuchElementException;
import kotlin.jvm.internal.Lambda;

/* JADX INFO: loaded from: classes.dex */
public final class dh implements Iterator {
    public final Lambda E;
    public int F = -1;
    public Object G;
    public Object H;
    public final /* synthetic */ eh I;

    /* JADX WARN: Multi-variable type inference failed */
    public dh(eh ehVar, d10 d10Var) {
        this.I = ehVar;
        this.E = (Lambda) d10Var;
        a();
    }

    public final void a() {
        T t;
        while (true) {
            int i = this.F + 1;
            this.F = i;
            eh ehVar = this.I;
            if (i >= ehVar.a) {
                return;
            }
            y20 y20Var = (y20) ehVar.d.get(i);
            if (y20Var != null && (t = y20Var.get()) != 0) {
                this.G = t;
                Object obj = ehVar.e.get(this.F);
                if (obj instanceof fc0) {
                    obj = ((fc0) obj).a;
                }
                if (obj != null) {
                    this.H = obj;
                    return;
                }
            }
        }
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.F < this.I.a;
    }

    /* JADX WARN: Type inference failed for: r1v3, types: [d10, kotlin.jvm.internal.Lambda] */
    @Override // java.util.Iterator
    public final Object next() {
        if (this.F >= this.I.a) {
            throw new NoSuchElementException();
        }
        Object obj = this.G;
        if (obj == null) {
            t40.o1("key");
            throw null;
        }
        Object obj2 = this.H;
        if (obj2 == null) {
            t40.o1("value");
            throw null;
        }
        Object objInvoke = this.E.invoke(obj, obj2);
        a();
        return objInvoke;
    }

    @Override // java.util.Iterator
    public final void remove() {
        throw new UnsupportedOperationException("not implemented");
    }
}
