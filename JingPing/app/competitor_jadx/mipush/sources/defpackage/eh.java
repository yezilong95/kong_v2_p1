package defpackage;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceArray;

/* JADX INFO: loaded from: classes.dex */
public final class eh {
    public static final /* synthetic */ AtomicIntegerFieldUpdater g = AtomicIntegerFieldUpdater.newUpdater(eh.class, "load$volatile");
    public final int a;
    public final int b;
    public final int c;
    public final /* synthetic */ AtomicReferenceArray d;
    public final /* synthetic */ AtomicReferenceArray e;
    public final /* synthetic */ hh f;
    private volatile /* synthetic */ int load$volatile;

    public eh(hh hhVar, int i) {
        this.f = hhVar;
        this.a = i;
        this.b = Integer.numberOfLeadingZeros(i) + 1;
        this.c = (i * 2) / 3;
        this.d = new AtomicReferenceArray(i);
        this.e = new AtomicReferenceArray(i);
    }

    public final Object a(Object obj, Object obj2, y20 y20Var) {
        wy0 wy0Var;
        int i;
        int iHashCode = (obj.hashCode() * (-1640531527)) >>> this.b;
        boolean z = false;
        loop0: while (true) {
            AtomicReferenceArray atomicReferenceArray = this.d;
            y20 y20Var2 = (y20) atomicReferenceArray.get(iHashCode);
            wy0Var = j31.d;
            AtomicIntegerFieldUpdater atomicIntegerFieldUpdater = g;
            if (y20Var2 == null) {
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
                    if (y20Var == null) {
                        y20Var = new y20(obj, this.f.E);
                        obj.hashCode();
                    }
                    y20 y20Var3 = y20Var;
                    while (!atomicReferenceArray.compareAndSet(iHashCode, null, y20Var3)) {
                        if (atomicReferenceArray.get(iHashCode) != null) {
                            z = z2;
                            y20Var = y20Var3;
                        }
                    }
                    break loop0;
                }
                return null;
            }
            Object obj3 = y20Var2.get();
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
            if (obj4 instanceof fc0) {
                break;
            }
            while (!atomicReferenceArray2.compareAndSet(iHashCode, obj4, obj2)) {
                if (atomicReferenceArray2.get(iHashCode) != obj4) {
                    break;
                }
            }
            return obj4;
        }
        return wy0Var;
    }

    public final eh b() {
        int i;
        Object obj;
        while (true) {
            hh hhVar = this.f;
            int size = hhVar.size();
            if (size < 4) {
                size = 4;
            }
            eh ehVar = new eh(hhVar, Integer.highestOneBit(size) * 4);
            while (i < this.a) {
                y20 y20Var = (y20) this.d.get(i);
                Object obj2 = y20Var != null ? y20Var.get() : null;
                if (y20Var != null && obj2 == null) {
                    c(i);
                }
                while (true) {
                    AtomicReferenceArray atomicReferenceArray = this.e;
                    obj = atomicReferenceArray.get(i);
                    if (!(obj instanceof fc0)) {
                        fc0 fc0Var = obj == null ? j31.e : obj.equals(Boolean.TRUE) ? j31.f : new fc0(obj);
                        while (!atomicReferenceArray.compareAndSet(i, obj, fc0Var)) {
                            if (atomicReferenceArray.get(i) != obj) {
                                break;
                            }
                        }
                        break;
                    }
                    obj = ((fc0) obj).a;
                    break;
                }
                i = (obj2 == null || obj == null || ehVar.a(obj2, obj, y20Var) != j31.d) ? i + 1 : 0;
            }
            return ehVar;
        }
    }

    public final void c(int i) {
        while (true) {
            AtomicReferenceArray atomicReferenceArray = this.e;
            Object obj = atomicReferenceArray.get(i);
            if (obj == null || (obj instanceof fc0)) {
                return;
            }
            while (!atomicReferenceArray.compareAndSet(i, obj, null)) {
                if (atomicReferenceArray.get(i) != obj) {
                    break;
                }
            }
            AtomicIntegerFieldUpdater atomicIntegerFieldUpdater = hh.F;
            hh hhVar = this.f;
            hhVar.getClass();
            hh.F.decrementAndGet(hhVar);
            return;
        }
    }
}
