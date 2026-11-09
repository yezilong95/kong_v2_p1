package defpackage;

import java.io.EOFException;
import java.io.IOException;
import java.util.Arrays;
import java.util.zip.CRC32;
import java.util.zip.Inflater;

/* JADX INFO: loaded from: classes.dex */
public final class mv implements uj0 {
    public byte b;
    public final sc0 c;
    public final Inflater d;
    public final ay e;
    public final CRC32 f;

    public mv(uj0 uj0Var) {
        oy.j(uj0Var, "source");
        sc0 sc0Var = new sc0(uj0Var);
        this.c = sc0Var;
        Inflater inflater = new Inflater(true);
        this.d = inflater;
        this.e = new ay(sc0Var, inflater);
        this.f = new CRC32();
    }

    public static void a(String str, int i, int i2) throws IOException {
        if (i2 != i) {
            throw new IOException(String.format("%s: actual 0x%08x != expected 0x%08x", Arrays.copyOf(new Object[]{str, Integer.valueOf(i2), Integer.valueOf(i)}, 3)));
        }
    }

    @Override // defpackage.uj0
    public final jo0 b() {
        return this.c.b.b();
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() throws IOException {
        this.e.close();
    }

    @Override // defpackage.uj0
    public final long d(long j, p9 p9Var) throws IOException {
        long j2;
        mv mvVar = this;
        oy.j(p9Var, "sink");
        byte b = mvVar.b;
        CRC32 crc32 = mvVar.f;
        sc0 sc0Var = mvVar.c;
        if (b == 0) {
            sc0Var.K(10L);
            p9 p9Var2 = sc0Var.c;
            byte bT = p9Var2.t(3L);
            boolean z = ((bT >> 1) & 1) == 1;
            if (z) {
                mvVar.t(p9Var2, 0L, 10L);
            }
            a("ID1ID2", 8075, sc0Var.H());
            sc0Var.L(8L);
            if (((bT >> 2) & 1) == 1) {
                sc0Var.K(2L);
                if (z) {
                    t(p9Var2, 0L, 2L);
                }
                short sG = p9Var2.G();
                long j3 = ((short) (((sG & 255) << 8) | ((sG & 65280) >>> 8))) & 65535;
                sc0Var.K(j3);
                if (z) {
                    t(p9Var2, 0L, j3);
                }
                sc0Var.L(j3);
            }
            if (((bT >> 3) & 1) == 1) {
                long jT = sc0Var.t((byte) 0, 0L, Long.MAX_VALUE);
                if (jT == -1) {
                    throw new EOFException();
                }
                if (z) {
                    j2 = 2;
                    t(p9Var2, 0L, jT + 1);
                } else {
                    j2 = 2;
                }
                sc0Var.L(jT + 1);
            } else {
                j2 = 2;
            }
            if (((bT >> 4) & 1) == 1) {
                long j4 = j2;
                long jT2 = sc0Var.t((byte) 0, 0L, Long.MAX_VALUE);
                if (jT2 == -1) {
                    throw new EOFException();
                }
                if (z) {
                    j2 = j4;
                    mvVar = this;
                    mvVar.t(p9Var2, 0L, jT2 + 1);
                } else {
                    mvVar = this;
                    j2 = j4;
                }
                sc0Var.L(jT2 + 1);
            } else {
                mvVar = this;
            }
            if (z) {
                sc0Var.K(j2);
                short sG2 = p9Var2.G();
                a("FHCRC", (short) (((sG2 & 255) << 8) | ((sG2 & 65280) >>> 8)), (short) crc32.getValue());
                crc32.reset();
            }
            mvVar.b = (byte) 1;
        }
        if (mvVar.b == 1) {
            long j5 = p9Var.c;
            long jD = mvVar.e.d(8192L, p9Var);
            if (jD != -1) {
                mvVar.t(p9Var, j5, jD);
                return jD;
            }
            mvVar.b = (byte) 2;
        }
        if (mvVar.b == 2) {
            a("CRC", sc0Var.G(), (int) crc32.getValue());
            a("ISIZE", sc0Var.G(), (int) mvVar.d.getBytesWritten());
            mvVar.b = (byte) 3;
            if (!sc0Var.a()) {
                throw new IOException("gzip finished without exhausting source");
            }
        }
        return -1L;
    }

    public final void t(p9 p9Var, long j, long j2) {
        th0 th0Var = p9Var.b;
        oy.g(th0Var);
        while (true) {
            int i = th0Var.c;
            int i2 = th0Var.b;
            if (j < i - i2) {
                break;
            }
            j -= (long) (i - i2);
            th0Var = th0Var.f;
            oy.g(th0Var);
        }
        while (j2 > 0) {
            int i3 = (int) (((long) th0Var.b) + j);
            int iMin = (int) Math.min(th0Var.c - i3, j2);
            this.f.update(th0Var.a, i3, iMin);
            j2 -= (long) iMin;
            th0Var = th0Var.f;
            oy.g(th0Var);
            j = 0;
        }
    }
}
