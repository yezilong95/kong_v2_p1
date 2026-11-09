package defpackage;

import java.io.Closeable;
import java.io.Flushable;

/* JADX INFO: loaded from: classes.dex */
public interface hj0 extends Closeable, Flushable {
    jo0 b();

    void c(long j, p9 p9Var);

    @Override // java.io.Closeable, java.lang.AutoCloseable
    void close();

    void flush();
}
