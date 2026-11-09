package defpackage;

/* JADX INFO: loaded from: classes.dex */
public final class q40 extends o40 {
    public static final q40 H = new q40(1, 0, 1);

    @Override // defpackage.o40
    public final boolean equals(Object obj) {
        if (!(obj instanceof q40)) {
            return false;
        }
        if (isEmpty() && ((q40) obj).isEmpty()) {
            return true;
        }
        q40 q40Var = (q40) obj;
        if (this.E == q40Var.E) {
            return this.F == q40Var.F;
        }
        return false;
    }

    @Override // defpackage.o40
    public final int hashCode() {
        if (isEmpty()) {
            return -1;
        }
        return (this.E * 31) + this.F;
    }

    @Override // defpackage.o40
    public final boolean isEmpty() {
        return this.E > this.F;
    }

    @Override // defpackage.o40
    public final String toString() {
        return this.E + ".." + this.F;
    }
}
