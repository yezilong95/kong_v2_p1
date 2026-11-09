package defpackage;

import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public abstract class lm {
    public static final hm l = new hm();
    public static final hm m = new hm();
    public static final hm n = new hm();
    public static final hm o = new hm();
    public static final hm p = new hm();
    public static final hm q = new hm();
    public float a;
    public float b;
    public boolean c;
    public final mk d;
    public final lk e;
    public boolean f;
    public final float g;
    public long h;
    public final float i;
    public final ArrayList j;
    public final ArrayList k;

    public lm(mk mkVar) {
        lk lkVar = mk.r;
        this.a = 0.0f;
        this.b = Float.MAX_VALUE;
        this.c = false;
        this.f = false;
        this.g = -3.4028235E38f;
        this.h = 0L;
        this.j = new ArrayList();
        this.k = new ArrayList();
        this.d = mkVar;
        this.e = lkVar;
        if (lkVar == n || lkVar == o || lkVar == p) {
            this.i = 0.1f;
            return;
        }
        if (lkVar == q) {
            this.i = 0.00390625f;
        } else if (lkVar == l || lkVar == m) {
            this.i = 0.00390625f;
        } else {
            this.i = 1.0f;
        }
    }

    public final void a(float f) {
        this.e.getClass();
        mk mkVar = this.d;
        mkVar.p = f / 10000.0f;
        mkVar.invalidateSelf();
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

    public void removeEndListener(jm jmVar) {
        ArrayList arrayList = this.j;
        int iIndexOf = arrayList.indexOf(jmVar);
        if (iIndexOf >= 0) {
            arrayList.set(iIndexOf, null);
        }
    }

    public void removeUpdateListener(km kmVar) {
        ArrayList arrayList = this.k;
        int iIndexOf = arrayList.indexOf(kmVar);
        if (iIndexOf >= 0) {
            arrayList.set(iIndexOf, null);
        }
    }
}
