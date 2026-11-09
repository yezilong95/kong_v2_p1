package defpackage;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceArray;

/* JADX INFO: loaded from: classes.dex */
public final class kf {
    public static final AtomicIntegerFieldUpdater g = AtomicIntegerFieldUpdater.newUpdater(kf.class, "load");
    public final int a;
    public final int b;
    public final int c;
    public final AtomicReferenceArray d;
    public final AtomicReferenceArray e;
    public final /* synthetic */ nf f;
    private volatile int load;

    public kf(nf nfVar, int i) {
        this.f = nfVar;
        this.a = i;
        this.b = Integer.numberOfLeadingZeros(i) + 1;
        this.c = (i * 2) / 3;
        this.d = new AtomicReferenceArray(i);
        this.e = new AtomicReferenceArray(i);
    }

    public final Object a(Object obj, Object obj2, rv rvVar) {
        nm nmVar;
        int i;
        int iHashCode = (obj.hashCode() * (-1640531527)) >>> this.b;
        boolean z = false;
        loop0: while (true) {
            AtomicReferenceArray atomicReferenceArray = this.d;
            rv rvVar2 = (rv) atomicReferenceArray.get(iHashCode);
            nmVar = k9.b;
            AtomicIntegerFieldUpdater atomicIntegerFieldUpdater = g;
            if (rvVar2 == null) {
                if (obj2 != null) {
                    if (!z) {
                        do {
                            i = atomicIntegerFieldUpdater.get(this);
                            if (i >= this.c) {
                                break loop0;
                            }
                        } while (!atomicIntegerFieldUpdater.compareAndSet(this, i, i + 1));
                        z = true;
                    }
                    boolean z2 = z;
                    if (rvVar == null) {
                        rvVar = new rv(obj, this.f.b);
                        obj.hashCode();
                    }
                    rv rvVar3 = rvVar;
                    while (!atomicReferenceArray.compareAndSet(iHashCode, null, rvVar3)) {
                        if (atomicReferenceArray.get(iHashCode) != null) {
                            z = z2;
                            rvVar = rvVar3;
                        }
                    }
                    break loop0;
                }
                return null;
            }
            Object obj3 = rvVar2.get();
            if (!obj.equals(obj3)) {
                if (obj3 == null) {
                    c(iHashCode);
                }
                if (iHashCode == 0) {
                    iHashCode = this.a;
                }
                iHashCode--;
            } else if (z) {
                atomicIntegerFieldUpdater.decrementAndGet(this);
            }
        }
        while (true) {
            AtomicReferenceArray atomicReferenceArray2 = this.e;
            Object obj4 = atomicReferenceArray2.get(iHashCode);
            if (obj4 instanceof p40) {
                break;
            }
            while (!atomicReferenceArray2.compareAndSet(iHashCode, obj4, obj2)) {
                if (atomicReferenceArray2.get(iHashCode) != obj4) {
                    break;
                }
            }
            return obj4;
        }
        return nmVar;
    }

    public final kf b() {
        int i;
        Object obj;
        while (true) {
            nf nfVar = this.f;
            int size = nfVar.size();
            if (size < 4) {
                size = 4;
            }
            kf kfVar = new kf(nfVar, Integer.highestOneBit(size) * 4);
            while (i < this.a) {
                rv rvVar = (rv) this.d.get(i);
                Object obj2 = rvVar != null ? rvVar.get() : null;
                if (rvVar != null && obj2 == null) {
                    c(i);
                }
                while (true) {
                    AtomicReferenceArray atomicReferenceArray = this.e;
                    obj = atomicReferenceArray.get(i);
                    if (!(obj instanceof p40)) {
                        p40 p40Var = obj == null ? k9.c : obj.equals(Boolean.TRUE) ? k9.d : new p40(obj);
                        while (!atomicReferenceArray.compareAndSet(i, obj, p40Var)) {
                            if (atomicReferenceArray.get(i) != obj) {
                                break;
                            }
                        }
                        break;
                    }
                    obj = ((p40) obj).a;
                    break;
                }
                i = (obj2 == null || obj == null || kfVar.a(obj2, obj, rvVar) != k9.b) ? i + 1 : 0;
            }
            return kfVar;
        }
    }

    public final void c(int i) {
        while (true) {
            AtomicReferenceArray atomicReferenceArray = this.e;
            Object obj = atomicReferenceArray.get(i);
            if (obj == null || (obj instanceof p40)) {
                return;
            }
            while (!atomicReferenceArray.compareAndSet(i, obj, null)) {
                if (atomicReferenceArray.get(i) != obj) {
                    break;
                }
            }
            AtomicIntegerFieldUpdater atomicIntegerFieldUpdater = nf.c;
            nf nfVar = this.f;
            nfVar.getClass();
            nf.c.decrementAndGet(nfVar);
            return;
        }
    }
}
