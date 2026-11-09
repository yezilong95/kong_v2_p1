package defpackage;

/* JADX INFO: loaded from: classes.dex */
public abstract class bg0 implements Runnable {
    public final long E;
    public boolean F = false;

    public bg0(long j) {
        this.E = j;
    }

    public abstract void a(long j);

    @Override // java.lang.Runnable
    public final void run() {
        if (this.F) {
            return;
        }
        this.F = true;
        a(this.E);
    }
}
