package defpackage;

import java.io.Closeable;

/* JADX INFO: loaded from: classes.dex */
public abstract class if0 implements Closeable {
    public abstract w9 C();

    public abstract long a();

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        kq0.d(C());
    }

    public abstract c60 t();
}
