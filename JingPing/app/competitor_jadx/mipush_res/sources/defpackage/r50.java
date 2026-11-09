package defpackage;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/* JADX INFO: loaded from: classes.dex */
public final class r50 implements w30 {
    public static final /* synthetic */ AtomicIntegerFieldUpdater b = AtomicIntegerFieldUpdater.newUpdater(r50.class, "_isCompleting$volatile");
    public static final /* synthetic */ AtomicReferenceFieldUpdater c = AtomicReferenceFieldUpdater.newUpdater(r50.class, Object.class, "_rootCause$volatile");
    public static final /* synthetic */ AtomicReferenceFieldUpdater d = AtomicReferenceFieldUpdater.newUpdater(r50.class, Object.class, "_exceptionsHolder$volatile");
    private volatile /* synthetic */ Object _exceptionsHolder$volatile;
    private volatile /* synthetic */ int _isCompleting$volatile = 0;
    private volatile /* synthetic */ Object _rootCause$volatile;
    public final th0 a;

    public r50(th0 th0Var, Throwable th) {
        this.a = th0Var;
        this._rootCause$volatile = th;
    }

    public final void a(Throwable th) {
        Throwable thB = b();
        if (thB == null) {
            c.set(this, th);
            return;
        }
        if (th == thB) {
            return;
        }
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = d;
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

    public final Throwable b() {
        return (Throwable) c.get(this);
    }

    public final boolean c() {
        return b() != null;
    }

    public final ArrayList d(Throwable th) {
        ArrayList arrayList;
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = d;
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
        Throwable thB = b();
        if (thB != null) {
            arrayList.add(0, thB);
        }
        if (th != null && !th.equals(thB)) {
            arrayList.add(th);
        }
        atomicReferenceFieldUpdater.set(this, j31.t);
        return arrayList;
    }

    @Override // defpackage.w30
    public final th0 getList() {
        return this.a;
    }

    @Override // defpackage.w30
    public final boolean isActive() {
        return b() == null;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("Finishing[cancelling=");
        sb.append(c());
        sb.append(", completing=");
        sb.append(b.get(this) != 0);
        sb.append(", rootCause=");
        sb.append(b());
        sb.append(", exceptions=");
        sb.append(d.get(this));
        sb.append(", list=");
        sb.append(this.a);
        sb.append(']');
        return sb.toString();
    }
}
