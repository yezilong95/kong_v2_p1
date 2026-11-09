package defpackage;

/* JADX INFO: loaded from: classes.dex */
public final class in extends mr0 {
    public static final in H;

    static {
        int i = mz0.c;
        int i2 = mz0.d;
        long j = mz0.e;
        String str = mz0.a;
        in inVar = new in();
        inVar.G = new kj(j, str, i, i2);
        H = inVar;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        throw new UnsupportedOperationException("Dispatchers.Default cannot be closed");
    }

    @Override // defpackage.ej
    public final String toString() {
        return "Dispatchers.Default";
    }
}
