package defpackage;

import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public abstract class oq {
    public static final kq l = new kq();
    public static final kq m = new kq();
    public static final kq n = new kq();
    public static final kq o = new kq();
    public static final kq p = new kq();
    public static final kq q = new kq();
    public float a;
    public float b;
    public boolean c;
    public final fo d;
    public final eo e;
    public boolean f;
    public final float g;
    public long h;
    public final float i;
    public final ArrayList j;
    public final ArrayList k;

    public oq(fo foVar) {
        eo eoVar = fo.U;
        this.a = 0.0f;
        this.b = Float.MAX_VALUE;
        this.c = false;
        this.f = false;
        this.g = -3.4028235E38f;
        this.h = 0L;
        this.j = new ArrayList();
        this.k = new ArrayList();
        this.d = foVar;
        this.e = eoVar;
        if (eoVar == n || eoVar == o || eoVar == p) {
            this.i = 0.1f;
            return;
        }
        if (eoVar == q) {
            this.i = 0.00390625f;
        } else if (eoVar == l || eoVar == m) {
            this.i = 0.00390625f;
        } else {
            this.i = 1.0f;
        }
    }

    public final void a(float f) {
        this.e.getClass();
        fo foVar = this.d;
        foVar.S.b = f / 10000.0f;
        foVar.invalidateSelf();
        int i = 0;
        while (true) {
            ArrayList arrayList = this.k;
            if (i >= arrayList.size()) {
                for (int size = arrayList.size() - 1; size >= 0; size--) {
                    if (arrayList.get(size) == null) {
                        arrayList.remove(size);
                    }
                }
                return;
            }
            if (arrayList.get(i) != null) {
                arrayList.get(i).getClass();
                throw new ClassCastException();
            }
            i++;
        }
    }

    public void removeEndListener(mq mqVar) {
        ArrayList arrayList = this.j;
        int iIndexOf = arrayList.indexOf(mqVar);
        if (iIndexOf >= 0) {
            arrayList.set(iIndexOf, null);
        }
    }

    public void removeUpdateListener(nq nqVar) {
        ArrayList arrayList = this.k;
        int iIndexOf = arrayList.indexOf(nqVar);
        if (iIndexOf >= 0) {
            arrayList.set(iIndexOf, null);
        }
    }
}
