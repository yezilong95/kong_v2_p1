package defpackage;

import android.os.Build;
import android.view.View;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public class uu0 {
    public static final xu0 b;
    public final xu0 a;

    static {
        int i = Build.VERSION.SDK_INT;
        b = (i >= 30 ? new nu0() : i >= 29 ? new mu0() : new lu0()).b().a.a().a.b().a.c();
    }

    public uu0(xu0 xu0Var) {
        this.a = xu0Var;
    }

    public xu0 a() {
        return this.a;
    }

    public xu0 b() {
        return this.a;
    }

    public xu0 c() {
        return this.a;
    }

    public cl e() {
        return null;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof uu0)) {
            return false;
        }
        uu0 uu0Var = (uu0) obj;
        return n() == uu0Var.n() && m() == uu0Var.m() && Objects.equals(j(), uu0Var.j()) && Objects.equals(h(), uu0Var.h()) && Objects.equals(e(), uu0Var.e());
    }

    public jy f(int i) {
        return jy.e;
    }

    public jy g() {
        return j();
    }

    public jy h() {
        return jy.e;
    }

    public int hashCode() {
        return Objects.hash(Boolean.valueOf(n()), Boolean.valueOf(m()), j(), h(), e());
    }

    public jy i() {
        return j();
    }

    public jy j() {
        return jy.e;
    }

    public jy k() {
        return j();
    }

    public xu0 l(int i, int i2, int i3, int i4) {
        return b;
    }

    public boolean m() {
        return false;
    }

    public boolean n() {
        return false;
    }

    public void d(View view) {
    }

    public void o(jy[] jyVarArr) {
    }

    public void p(xu0 xu0Var) {
    }

    public void q(jy jyVar) {
    }
}
