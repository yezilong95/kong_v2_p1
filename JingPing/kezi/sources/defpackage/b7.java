package defpackage;

import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public final class b7 extends ux {
    public final /* synthetic */ int d;
    public final /* synthetic */ Object e;

    public /* synthetic */ b7(int i, Object obj) {
        this.d = i;
        this.e = obj;
    }

    @Override // defpackage.ux
    public final void b() {
        switch (this.d) {
            case 0:
                ((c7) this.e).clear();
                break;
            default:
                ((f7) this.e).clear();
                break;
        }
    }

    @Override // defpackage.ux
    public final Object c(int i, int i2) {
        switch (this.d) {
            case 0:
                return ((c7) this.e).c[(i << 1) + i2];
            default:
                return ((f7) this.e).c[i];
        }
    }

    @Override // defpackage.ux
    public final Map d() {
        switch (this.d) {
            case 0:
                return (c7) this.e;
            default:
                throw new UnsupportedOperationException("not a map");
        }
    }

    @Override // defpackage.ux
    public final int e() {
        switch (this.d) {
            case 0:
                return ((c7) this.e).d;
            default:
                return ((f7) this.e).d;
        }
    }

    @Override // defpackage.ux
    public final int f(Object obj) {
        switch (this.d) {
            case 0:
                return ((c7) this.e).e(obj);
            default:
                f7 f7Var = (f7) this.e;
                return obj == null ? f7Var.d() : f7Var.c(obj.hashCode(), obj);
        }
    }

    @Override // defpackage.ux
    public final int g(Object obj) {
        switch (this.d) {
            case 0:
                return ((c7) this.e).g(obj);
            default:
                f7 f7Var = (f7) this.e;
                return obj == null ? f7Var.d() : f7Var.c(obj.hashCode(), obj);
        }
    }

    @Override // defpackage.ux
    public final void h(Object obj, Object obj2) {
        switch (this.d) {
            case 0:
                ((c7) this.e).put(obj, obj2);
                break;
            default:
                ((f7) this.e).add(obj);
                break;
        }
    }

    @Override // defpackage.ux
    public final void i(int i) {
        switch (this.d) {
            case 0:
                ((c7) this.e).i(i);
                break;
            default:
                ((f7) this.e).e(i);
                break;
        }
    }

    @Override // defpackage.ux
    public final Object j(int i, Object obj) {
        switch (this.d) {
            case 0:
                int i2 = (i << 1) + 1;
                Object[] objArr = ((c7) this.e).c;
                Object obj2 = objArr[i2];
                objArr[i2] = obj;
                return obj2;
            default:
                throw new UnsupportedOperationException("not a map");
        }
    }
}
