package defpackage;

/* JADX INFO: loaded from: classes.dex */
public final class lz0 extends kz0 {
    public final Runnable G;

    public lz0(Runnable runnable, long j, iq0 iq0Var) {
        super(j, iq0Var);
        this.G = runnable;
    }

    @Override // java.lang.Runnable
    public final void run() {
        try {
            this.G.run();
        } finally {
            this.F.getClass();
        }
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("Task[");
        Runnable runnable = this.G;
        sb.append(runnable.getClass().getSimpleName());
        sb.append('@');
        sb.append(qm.C(runnable));
        sb.append(", ");
        sb.append(this.E);
        sb.append(", ");
        sb.append(this.F);
        sb.append(']');
        return sb.toString();
    }
}
