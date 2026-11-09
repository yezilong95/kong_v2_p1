package defpackage;

import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

/* JADX INFO: loaded from: classes.dex */
public final class sf0 implements vl0, ul0 {
    public static final TreeMap j = new TreeMap();
    public final int b;
    public volatile String c;
    public final long[] d;
    public final double[] e;
    public final String[] f;
    public final byte[][] g;
    public final int[] h;
    public int i;

    public sf0(int i) {
        this.b = i;
        int i2 = i + 1;
        this.h = new int[i2];
        this.d = new long[i2];
        this.e = new double[i2];
        this.f = new String[i2];
        this.g = new byte[i2][];
    }

    public static final sf0 C(int i, String str) {
        oy.j(str, "query");
        TreeMap treeMap = j;
        synchronized (treeMap) {
            Map.Entry entryCeilingEntry = treeMap.ceilingEntry(Integer.valueOf(i));
            if (entryCeilingEntry == null) {
                sf0 sf0Var = new sf0(i);
                sf0Var.c = str;
                sf0Var.i = i;
                return sf0Var;
            }
            treeMap.remove(entryCeilingEntry.getKey());
            sf0 sf0Var2 = (sf0) entryCeilingEntry.getValue();
            sf0Var2.getClass();
            sf0Var2.c = str;
            sf0Var2.i = i;
            return sf0Var2;
        }
    }

    @Override // defpackage.ul0
    public final void B(byte[] bArr, int i) {
        this.h[i] = 5;
        this.g[i] = bArr;
    }

    public final void D() {
        TreeMap treeMap = j;
        synchronized (treeMap) {
            treeMap.put(Integer.valueOf(this.b), this);
            if (treeMap.size() > 15) {
                int size = treeMap.size() - 10;
                Iterator it = treeMap.descendingKeySet().iterator();
                oy.i(it, "queryPool.descendingKeySet().iterator()");
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

    @Override // defpackage.vl0
    public final void a(ul0 ul0Var) {
        int i = this.i;
        if (1 > i) {
            return;
        }
        int i2 = 1;
        while (true) {
            int i3 = this.h[i2];
            if (i3 == 1) {
                ul0Var.r(i2);
            } else if (i3 == 2) {
                ul0Var.x(this.d[i2], i2);
            } else if (i3 == 3) {
                ul0Var.s(i2, this.e[i2]);
            } else if (i3 == 4) {
                String str = this.f[i2];
                if (str == null) {
                    throw new IllegalArgumentException("Required value was null.");
                }
                ul0Var.j(i2, str);
            } else if (i3 == 5) {
                byte[] bArr = this.g[i2];
                if (bArr == null) {
                    throw new IllegalArgumentException("Required value was null.");
                }
                ul0Var.B(bArr, i2);
            }
            if (i2 == i) {
                return;
            } else {
                i2++;
            }
        }
    }

    @Override // defpackage.ul0
    public final void j(int i, String str) {
        this.h[i] = 4;
        this.f[i] = str;
    }

    @Override // defpackage.ul0
    public final void r(int i) {
        this.h[i] = 1;
    }

    @Override // defpackage.ul0
    public final void s(int i, double d) {
        this.h[i] = 3;
        this.e[i] = d;
    }

    @Override // defpackage.vl0
    public final String t() {
        String str = this.c;
        if (str != null) {
            return str;
        }
        throw new IllegalStateException("Required value was null.");
    }

    @Override // defpackage.ul0
    public final void x(long j2, int i) {
        this.h[i] = 2;
        this.d[i] = j2;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
    }
}
