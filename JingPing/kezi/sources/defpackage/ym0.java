package defpackage;

/* JADX INFO: loaded from: classes.dex */
public final class ym0 extends wm0 {
    public final Runnable d;

    public ym0(Runnable runnable, long j, xm0 xm0Var) {
        super(j, xm0Var);
        this.d = runnable;
    }

    @Override // java.lang.Runnable
    public final void run() {
        try {
            this.d.run();
        } finally {
            this.c.getClass();
        }
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("Task[");
        Runnable runnable = this.d;
        sb.append(runnable.getClass().getSimpleName());
        sb.append('@');
        sb.append(xi.y(runnable));
        sb.append(", ");
        sb.append(this.b);
        sb.append(", ");
        sb.append(this.c);
        sb.append(']');
        return sb.toString();
    }
}
