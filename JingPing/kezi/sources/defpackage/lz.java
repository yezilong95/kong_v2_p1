package defpackage;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/* JADX INFO: loaded from: classes.dex */
public final class lz implements sx {
    public static final AtomicIntegerFieldUpdater c = AtomicIntegerFieldUpdater.newUpdater(lz.class, "_isCompleting");
    public static final AtomicReferenceFieldUpdater d = AtomicReferenceFieldUpdater.newUpdater(lz.class, Object.class, "_rootCause");
    public static final AtomicReferenceFieldUpdater e = AtomicReferenceFieldUpdater.newUpdater(lz.class, Object.class, "_exceptionsHolder");
    private volatile Object _exceptionsHolder;
    private volatile int _isCompleting = 0;
    private volatile Object _rootCause;
    public final p90 b;

    public lz(p90 p90Var, Throwable th) {
        this.b = p90Var;
        this._rootCause = th;
    }

    @Override // defpackage.sx
    public final boolean a() {
        return d() == null;
    }

    public final void b(Throwable th) {
        Throwable thD = d();
        if (thD == null) {
            d.set(this, th);
            return;
        }
        if (th == thD) {
            return;
        }
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = e;
        Object obj = atomicReferenceFieldUpdater.get(this);
        if (obj == null) {
            atomicReferenceFieldUpdater.set(this, th);
            return;
        }
        if (!(obj instanceof Throwable)) {
            if (obj instanceof ArrayList) {
                ((ArrayList) obj).add(th);
                return;
            } else {
                throw new IllegalStateException(("State is " + obj).toString());
            }
        }
        if (th == obj) {
            return;
        }
        ArrayList arrayList = new ArrayList(4);
        arrayList.add(obj);
        arrayList.add(th);
        atomicReferenceFieldUpdater.set(this, arrayList);
    }

    @Override // defpackage.sx
    public final p90 c() {
        return this.b;
    }

    public final Throwable d() {
        return (Throwable) d.get(this);
    }

    public final boolean e() {
        return d() != null;
    }

    public final ArrayList f(Throwable th) {
        ArrayList arrayList;
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = e;
        Object obj = atomicReferenceFieldUpdater.get(this);
        if (obj == null) {
            arrayList = new ArrayList(4);
        } else if (obj instanceof Throwable) {
            ArrayList arrayList2 = new ArrayList(4);
            arrayList2.add(obj);
            arrayList = arrayList2;
        } else {
            if (!(obj instanceof ArrayList)) {
                throw new IllegalStateException(("State is " + obj).toString());
            }
            arrayList = (ArrayList) obj;
        }
        Throwable thD = d();
        if (thD != null) {
            arrayList.add(0, thD);
        }
        if (th != null && !th.equals(thD)) {
            arrayList.add(th);
        }
        atomicReferenceFieldUpdater.set(this, nv0.g);
        return arrayList;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("Finishing[cancelling=");
        sb.append(e());
        sb.append(", completing=");
        sb.append(c.get(this) != 0);
        sb.append(", rootCause=");
        sb.append(d());
        sb.append(", exceptions=");
        sb.append(e.get(this));
        sb.append(", list=");
        sb.append(this.b);
        sb.append(']');
        return sb.toString();
    }
}
