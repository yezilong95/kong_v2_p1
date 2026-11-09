package defpackage;

import android.graphics.Rect;
import android.view.View;
import androidx.recyclerview.widget.n;
import androidx.viewpager.widget.ViewPager;
import io.nats.client.support.JsonUtils;

/* JADX INFO: loaded from: classes.dex */
public final class n41 implements vi0 {
    public final /* synthetic */ int E;
    public final Object F;
    public final Object G;

    public /* synthetic */ n41(Object obj, int i, Object obj2) {
        this.E = i;
        this.F = obj;
        this.G = obj2;
    }

    public void a(n nVar, qh0 qh0Var) {
        vv0 vv0Var = (vv0) this.F;
        n51 n51VarA = (n51) vv0Var.getOrDefault(nVar, null);
        if (n51VarA == null) {
            n51VarA = n51.a();
            vv0Var.put(nVar, n51VarA);
        }
        n51VarA.c = qh0Var;
        n51VarA.a |= 8;
    }

    public View b(int i, int i2, int i3, int i4) {
        m41 m41Var = (m41) this.F;
        int iD = m41Var.d();
        int iA = m41Var.a();
        int i5 = i2 > i ? 1 : -1;
        View view = null;
        while (i != i2) {
            View viewC = m41Var.c(i);
            int iB = m41Var.b(viewC);
            int iE = m41Var.e(viewC);
            l41 l41Var = (l41) this.G;
            l41Var.b = iD;
            l41Var.c = iA;
            l41Var.d = iB;
            l41Var.e = iE;
            if (i3 != 0) {
                l41Var.a = i3;
                if (l41Var.a()) {
                    return viewC;
                }
            }
            if (i4 != 0) {
                l41Var.a = i4;
                if (l41Var.a()) {
                    view = viewC;
                }
            }
            i += i5;
        }
        return view;
    }

    public boolean c(View view) {
        m41 m41Var = (m41) this.F;
        int iD = m41Var.d();
        int iA = m41Var.a();
        int iB = m41Var.b(view);
        int iE = m41Var.e(view);
        l41 l41Var = (l41) this.G;
        l41Var.b = iD;
        l41Var.c = iA;
        l41Var.d = iB;
        l41Var.e = iE;
        l41Var.a = 24579;
        return l41Var.a();
    }

    public qh0 d(n nVar, int i) {
        n51 n51Var;
        qh0 qh0Var;
        vv0 vv0Var = (vv0) this.F;
        int iE = vv0Var.e(nVar);
        if (iE >= 0 && (n51Var = (n51) vv0Var.j(iE)) != null) {
            int i2 = n51Var.a;
            if ((i2 & i) != 0) {
                int i3 = i2 & (~i);
                n51Var.a = i3;
                if (i == 4) {
                    qh0Var = n51Var.b;
                } else {
                    if (i != 8) {
                        throw new IllegalArgumentException("Must provide flag PRE or POST");
                    }
                    qh0Var = n51Var.c;
                }
                if ((i3 & 12) == 0) {
                    vv0Var.i(iE);
                    n51Var.a = 0;
                    n51Var.b = null;
                    n51Var.c = null;
                    n51.d.c(n51Var);
                }
                return qh0Var;
            }
        }
        return null;
    }

    public void e(n nVar) {
        n51 n51Var = (n51) ((vv0) this.F).getOrDefault(nVar, null);
        if (n51Var == null) {
            return;
        }
        n51Var.a &= -2;
    }

    public void f(n nVar) {
        cb0 cb0Var = (cb0) this.G;
        int iE = cb0Var.e() - 1;
        while (true) {
            if (iE < 0) {
                break;
            }
            if (nVar == cb0Var.f(iE)) {
                Object[] objArr = cb0Var.G;
                Object obj = objArr[iE];
                Object obj2 = cb0.I;
                if (obj != obj2) {
                    objArr[iE] = obj2;
                    cb0Var.E = true;
                }
            } else {
                iE--;
            }
        }
        n51 n51Var = (n51) ((vv0) this.F).remove(nVar);
        if (n51Var != null) {
            n51Var.a = 0;
            n51Var.b = null;
            n51Var.c = null;
            n51.d.c(n51Var);
        }
    }

    @Override // defpackage.vi0
    public s81 h(View view, s81 s81Var) {
        switch (this.E) {
            case 2:
                s81 s81VarJ = f51.j(view, s81Var);
                if (s81VarJ.a.m()) {
                    return s81VarJ;
                }
                int iB = s81VarJ.b();
                Rect rect = (Rect) this.F;
                rect.left = iB;
                rect.top = s81VarJ.d();
                rect.right = s81VarJ.c();
                rect.bottom = s81VarJ.a();
                ViewPager viewPager = (ViewPager) this.G;
                int childCount = viewPager.getChildCount();
                for (int i = 0; i < childCount; i++) {
                    s81 s81VarB = f51.b(viewPager.getChildAt(i), s81VarJ);
                    rect.left = Math.min(s81VarB.b(), rect.left);
                    rect.top = Math.min(s81VarB.d(), rect.top);
                    rect.right = Math.min(s81VarB.c(), rect.right);
                    rect.bottom = Math.min(s81VarB.a(), rect.bottom);
                }
                return s81VarJ.f(rect.left, rect.top, rect.right, rect.bottom);
            default:
                o61 o61Var = new o61();
                o61 o61Var2 = (o61) this.G;
                o61Var.a = o61Var2.a;
                o61Var.b = o61Var2.b;
                o61Var.c = o61Var2.c;
                o61Var.d = o61Var2.d;
                return ((n61) this.F).o(view, s81Var, o61Var);
        }
    }

    public String toString() {
        switch (this.E) {
            case 4:
                return "Bounds{lower=" + ((m40) this.F) + " upper=" + ((m40) this.G) + JsonUtils.CLOSE;
            default:
                return super.toString();
        }
    }

    public n41() {
        this.E = 1;
        this.F = new vv0();
        this.G = new cb0();
    }

    public n41(m41 m41Var) {
        this.E = 0;
        this.F = m41Var;
        l41 l41Var = new l41();
        l41Var.a = 0;
        this.G = l41Var;
    }

    public n41(ViewPager viewPager) {
        this.E = 2;
        this.G = viewPager;
        this.F = new Rect();
    }
}
