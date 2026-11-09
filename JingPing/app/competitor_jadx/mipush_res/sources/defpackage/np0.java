package defpackage;

import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

/* JADX INFO: loaded from: classes.dex */
public final class np0 implements jy0, iy0 {
    public static final TreeMap M = new TreeMap();
    public final int E;
    public volatile String F;
    public final long[] G;
    public final double[] H;
    public final String[] I;
    public final byte[][] J;
    public final int[] K;
    public int L;

    public np0(int i) {
        this.E = i;
        int i2 = i + 1;
        this.K = new int[i2];
        this.G = new long[i2];
        this.H = new double[i2];
        this.I = new String[i2];
        this.J = new byte[i2][];
    }

    public static final np0 w(int i, String str) {
        t40.n(str, "query");
        TreeMap treeMap = M;
        synchronized (treeMap) {
            Map.Entry entryCeilingEntry = treeMap.ceilingEntry(Integer.valueOf(i));
            if (entryCeilingEntry == null) {
                np0 np0Var = new np0(i);
                np0Var.F = str;
                np0Var.L = i;
                return np0Var;
            }
            treeMap.remove(entryCeilingEntry.getKey());
            np0 np0Var2 = (np0) entryCeilingEntry.getValue();
            np0Var2.getClass();
            np0Var2.F = str;
            np0Var2.L = i;
            return np0Var2;
        }
    }

    @Override // defpackage.iy0
    public final void e(int i, String str) {
        this.K[i] = 4;
        this.I[i] = str;
    }

    @Override // defpackage.iy0
    public final void h(int i) {
        this.K[i] = 1;
    }

    @Override // defpackage.iy0
    public final void i(int i, double d) {
        this.K[i] = 3;
        this.H[i] = d;
    }

    @Override // defpackage.jy0
    public final String j() {
        String str = this.F;
        if (str != null) {
            return str;
        }
        throw new IllegalStateException("Required value was null.");
    }

    @Override // defpackage.jy0
    public final void l(iy0 iy0Var) {
        int i = this.L;
        if (1 > i) {
            return;
        }
        int i2 = 1;
        while (true) {
            int i3 = this.K[i2];
            if (i3 == 1) {
                iy0Var.h(i2);
            } else if (i3 == 2) {
                iy0Var.n(i2, this.G[i2]);
            } else if (i3 == 3) {
                iy0Var.i(i2, this.H[i2]);
            } else if (i3 == 4) {
                String str = this.I[i2];
                if (str == null) {
                    throw new IllegalArgumentException("Required value was null.");
                }
                iy0Var.e(i2, str);
            } else if (i3 == 5) {
                byte[] bArr = this.J[i2];
                if (bArr == null) {
                    throw new IllegalArgumentException("Required value was null.");
                }
                iy0Var.s(i2, bArr);
            }
            if (i2 == i) {
                return;
            } else {
                i2++;
            }
        }
    }

    @Override // defpackage.iy0
    public final void n(int i, long j) {
        this.K[i] = 2;
        this.G[i] = j;
    }

    @Override // defpackage.iy0
    public final void s(int i, byte[] bArr) {
        this.K[i] = 5;
        this.J[i] = bArr;
    }

    public final void x() {
        TreeMap treeMap = M;
        synchronized (treeMap) {
            treeMap.put(Integer.valueOf(this.E), this);
            if (treeMap.size() > 15) {
                int size = treeMap.size() - 10;
                Iterator it = treeMap.descendingKeySet().iterator();
                t40.m(it, "queryPool.descendingKeySet().iterator()");
                while (true) {
                    int i = size - 1;
                    if (size <= 0) {
                        break;
                    }
                    it.next();
                    it.remove();
                    size = i;
                }
            }
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
    }
}
