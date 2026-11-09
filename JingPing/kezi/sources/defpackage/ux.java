package defpackage;

import java.lang.reflect.Array;
import java.util.Map;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public abstract class ux {
    public Object a;
    public Object b;
    public Object c;

    public ux(int i) {
        this.b = new float[i * 2];
        this.c = new int[i];
    }

    public static boolean k(Set set, Object obj) {
        if (set == obj) {
            return true;
        }
        if (!(obj instanceof Set)) {
            return false;
        }
        Set set2 = (Set) obj;
        try {
            if (set.size() == set2.size()) {
                return set.containsAll(set2);
            }
            return false;
        } catch (ClassCastException | NullPointerException unused) {
            return false;
        }
    }

    public abstract void a();

    public abstract void b();

    public abstract Object c(int i, int i2);

    public abstract Map d();

    public abstract int e();

    public abstract int f(Object obj);

    public abstract int g(Object obj);

    public abstract void h(Object obj, Object obj2);

    public abstract void i(int i);

    public abstract Object j(int i, Object obj);

    public abstract void l();

    public abstract void m(d8 d8Var);

    public abstract void n();

    public abstract void o();

    public Object[] p(Object[] objArr, int i) {
        int iE = e();
        if (objArr.length < iE) {
            objArr = (Object[]) Array.newInstance(objArr.getClass().getComponentType(), iE);
        }
        for (int i2 = 0; i2 < iE; i2++) {
            objArr[i2] = c(i2, i);
        }
        if (objArr.length > iE) {
            objArr[iE] = null;
        }
        return objArr;
    }

    public abstract void q();
}
