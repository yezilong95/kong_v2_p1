package defpackage;

import android.os.Build;
import android.view.View;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public class p81 {
    public static final s81 b;
    public final s81 a;

    static {
        int i = Build.VERSION.SDK_INT;
        b = (i >= 30 ? new i81() : i >= 29 ? new h81() : new g81()).b().a.a().a.b().a.c();
    }

    public p81(s81 s81Var) {
        this.a = s81Var;
    }

    public s81 a() {
        return this.a;
    }

    public s81 b() {
        return this.a;
    }

    public s81 c() {
        return this.a;
    }

    public zo e() {
        return null;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof p81)) {
            return false;
        }
        p81 p81Var = (p81) obj;
        return n() == p81Var.n() && m() == p81Var.m() && Objects.equals(j(), p81Var.j()) && Objects.equals(h(), p81Var.h()) && Objects.equals(e(), p81Var.e());
    }

    public m40 f(int i) {
        return m40.e;
    }

    public m40 g() {
        return j();
    }

    public m40 h() {
        return m40.e;
    }

    public int hashCode() {
        return Objects.hash(Boolean.valueOf(n()), Boolean.valueOf(m()), j(), h(), e());
    }

    public m40 i() {
        return j();
    }

    public m40 j() {
        return m40.e;
    }

    public m40 k() {
        return j();
    }

    public s81 l(int i, int i2, int i3, int i4) {
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

    public void o(m40[] m40VarArr) {
    }

    public void p(s81 s81Var) {
    }

    public void q(m40 m40Var) {
    }
}
