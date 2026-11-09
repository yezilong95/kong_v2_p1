package defpackage;

/* JADX INFO: loaded from: classes.dex */
public final class pj extends yg0 {
    public static final pj e;

    static {
        int i = bn0.c;
        int i2 = bn0.d;
        long j = bn0.e;
        String str = bn0.a;
        pj pjVar = new pj();
        pjVar.d = new wh(j, str, i, i2);
        e = pjVar;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        throw new UnsupportedOperationException("Dispatchers.Default cannot be closed");
    }

    @Override // defpackage.qh
    public final String toString() {
        return "Dispatchers.Default";
    }
}
