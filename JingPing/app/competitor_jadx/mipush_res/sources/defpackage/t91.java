package defpackage;

/* JADX INFO: loaded from: classes.dex */
public abstract class t91 {
    public xq a;
    public s91 b;

    public abstract xq a();

    public abstract s91 b();

    public final synchronized xq c() {
        try {
            if (this.a == null) {
                this.a = a();
            }
        } catch (Throwable th) {
            throw th;
        }
        return this.a;
    }

    public final synchronized s91 d() {
        try {
            if (this.b == null) {
                this.b = b();
            }
        } catch (Throwable th) {
            throw th;
        }
        return this.b;
    }
}
