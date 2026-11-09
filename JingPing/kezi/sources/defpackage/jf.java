package defpackage;

import java.util.Iterator;
import java.util.NoSuchElementException;
import kotlin.jvm.internal.Lambda;

/* JADX INFO: loaded from: classes.dex */
public final class jf implements Iterator, wz {
    public final Lambda b;
    public int c = -1;
    public Object d;
    public Object e;
    public final /* synthetic */ kf f;

    /* JADX WARN: Multi-variable type inference failed */
    public jf(kf kfVar, ru ruVar) {
        this.f = kfVar;
        this.b = (Lambda) ruVar;
        a();
    }

    public final void a() {
        T t;
        while (true) {
            int i = this.c + 1;
            this.c = i;
            kf kfVar = this.f;
            if (i >= kfVar.a) {
                return;
            }
            rv rvVar = (rv) kfVar.d.get(i);
            if (rvVar != null && (t = rvVar.get()) != 0) {
                this.d = t;
                Object obj = kfVar.e.get(this.c);
                if (obj instanceof p40) {
                    obj = ((p40) obj).a;
                }
                if (obj != null) {
                    this.e = obj;
                    return;
                }
            }
        }
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.c < this.f.a;
    }

    /* JADX WARN: Type inference failed for: r1v3, types: [kotlin.jvm.internal.Lambda, ru] */
    @Override // java.util.Iterator
    public final Object next() {
        if (this.c >= this.f.a) {
            throw new NoSuchElementException();
        }
        Object obj = this.d;
        if (obj == null) {
            oy.S("key");
            throw null;
        }
        Object obj2 = this.e;
        if (obj2 == null) {
            oy.S("value");
            throw null;
        }
        Object objInvoke = this.b.invoke(obj, obj2);
        a();
        return objInvoke;
    }

    @Override // java.util.Iterator
    public final void remove() {
        throw new UnsupportedOperationException("not implemented");
    }
}
