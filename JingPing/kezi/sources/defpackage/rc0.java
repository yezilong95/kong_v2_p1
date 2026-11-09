package defpackage;

import java.nio.ByteBuffer;

/* JADX INFO: loaded from: classes.dex */
public final class rc0 implements v9 {
    public final hj0 b;
    public final p9 c;
    public boolean d;

    public rc0(hj0 hj0Var) {
        oy.j(hj0Var, "sink");
        this.b = hj0Var;
        this.c = new p9();
    }

    public final v9 C(int i) {
        if (this.d) {
            throw new IllegalStateException("closed");
        }
        this.c.Q(i);
        a();
        return this;
    }

    public final v9 a() {
        if (this.d) {
            throw new IllegalStateException("closed");
        }
        p9 p9Var = this.c;
        long j = p9Var.c;
        if (j == 0) {
            j = 0;
        } else {
            th0 th0Var = p9Var.b;
            oy.g(th0Var);
            th0 th0Var2 = th0Var.g;
            oy.g(th0Var2);
            int i = th0Var2.c;
            if (i < 8192 && th0Var2.e) {
                j -= (long) (i - th0Var2.b);
            }
        }
        if (j > 0) {
            this.b.c(j, p9Var);
        }
        return this;
    }

    @Override // defpackage.hj0
    public final jo0 b() {
        return this.b.b();
    }

    @Override // defpackage.hj0
    public final void c(long j, p9 p9Var) {
        oy.j(p9Var, "source");
        if (this.d) {
            throw new IllegalStateException("closed");
        }
        this.c.c(j, p9Var);
        a();
    }

    @Override // defpackage.hj0, java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        hj0 hj0Var = this.b;
        if (this.d) {
            return;
        }
        try {
            p9 p9Var = this.c;
            long j = p9Var.c;
            if (j > 0) {
                hj0Var.c(j, p9Var);
            }
            th = null;
        } catch (Throwable th) {
            th = th;
        }
        try {
            hj0Var.close();
        } catch (Throwable th2) {
            if (th == null) {
                th = th2;
            }
        }
        this.d = true;
        if (th != null) {
            throw th;
        }
    }

    @Override // defpackage.hj0, java.io.Flushable
    public final void flush() {
        if (this.d) {
            throw new IllegalStateException("closed");
        }
        p9 p9Var = this.c;
        long j = p9Var.c;
        hj0 hj0Var = this.b;
        if (j > 0) {
            hj0Var.c(j, p9Var);
        }
        hj0Var.flush();
    }

    @Override // java.nio.channels.Channel
    public final boolean isOpen() {
        return !this.d;
    }

    public final v9 t(int i) {
        if (this.d) {
            throw new IllegalStateException("closed");
        }
        this.c.O(i);
        a();
        return this;
    }

    public final String toString() {
        return "buffer(" + this.b + ')';
    }

    @Override // defpackage.v9
    public final v9 u(String str) {
        oy.j(str, "string");
        if (this.d) {
            throw new IllegalStateException("closed");
        }
        this.c.R(str);
        a();
        return this;
    }

    @Override // java.nio.channels.WritableByteChannel
    public final int write(ByteBuffer byteBuffer) {
        oy.j(byteBuffer, "source");
        if (this.d) {
            throw new IllegalStateException("closed");
        }
        int iWrite = this.c.write(byteBuffer);
        a();
        return iWrite;
    }
}
