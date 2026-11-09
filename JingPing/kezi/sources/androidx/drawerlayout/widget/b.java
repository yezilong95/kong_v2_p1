package androidx.drawerlayout.widget;

import android.view.View;
import androidx.drawerlayout.widget.DrawerLayout;
import defpackage.cs0;
import defpackage.v6;
import defpackage.xi;

/* JADX INFO: loaded from: classes.dex */
public final class b extends xi {
    public final int r;
    public cs0 s;
    public final v6 t = new v6(6, this);
    public final /* synthetic */ DrawerLayout u;

    public b(DrawerLayout drawerLayout, int i) {
        this.u = drawerLayout;
        this.r = i;
    }

    @Override // defpackage.xi
    public final int C(View view) {
        if (DrawerLayout.l(view)) {
            return view.getWidth();
        }
        return 0;
    }

    @Override // defpackage.xi
    public final void H(int i, int i2) {
        int i3 = i & 1;
        DrawerLayout drawerLayout = this.u;
        View viewD = i3 == 1 ? drawerLayout.d(3) : drawerLayout.d(5);
        if (viewD == null || drawerLayout.g(viewD) != 0) {
            return;
        }
        this.s.b(viewD, i2);
    }

    @Override // defpackage.xi
    public final void I() {
        this.u.postDelayed(this.t, 160L);
    }

    @Override // defpackage.xi
    public final void K(View view, int i) {
        ((DrawerLayout.LayoutParams) view.getLayoutParams()).c = false;
        int i2 = this.r == 3 ? 5 : 3;
        DrawerLayout drawerLayout = this.u;
        View viewD = drawerLayout.d(i2);
        if (viewD != null) {
            drawerLayout.b(viewD, true);
        }
    }

    @Override // defpackage.xi
    public final void L(int i) {
        this.u.r(this.s.t, i);
    }

    @Override // defpackage.xi
    public final void M(View view, int i, int i2) {
        int width = view.getWidth();
        DrawerLayout drawerLayout = this.u;
        float width2 = (drawerLayout.a(view, 3) ? i + width : drawerLayout.getWidth() - i) / width;
        drawerLayout.o(view, width2);
        view.setVisibility(width2 == 0.0f ? 4 : 0);
        drawerLayout.invalidate();
    }

    @Override // defpackage.xi
    public final void N(View view, float f, float f2) {
        int i;
        DrawerLayout drawerLayout = this.u;
        int[] iArr = DrawerLayout.E;
        float f3 = ((DrawerLayout.LayoutParams) view.getLayoutParams()).b;
        int width = view.getWidth();
        if (drawerLayout.a(view, 3)) {
            i = (f > 0.0f || (f == 0.0f && f3 > 0.5f)) ? 0 : -width;
        } else {
            int width2 = drawerLayout.getWidth();
            if (f < 0.0f || (f == 0.0f && f3 > 0.5f)) {
                width2 -= width;
            }
            i = width2;
        }
        this.s.q(i, view.getTop());
        drawerLayout.invalidate();
    }

    @Override // defpackage.xi
    public final boolean S(View view, int i) {
        DrawerLayout drawerLayout = this.u;
        return DrawerLayout.l(view) && drawerLayout.a(view, this.r) && drawerLayout.g(view) == 0;
    }

    @Override // defpackage.xi
    public final int g(View view, int i) {
        DrawerLayout drawerLayout = this.u;
        if (drawerLayout.a(view, 3)) {
            return Math.max(-view.getWidth(), Math.min(i, 0));
        }
        int width = drawerLayout.getWidth();
        return Math.max(width - view.getWidth(), Math.min(i, width));
    }

    @Override // defpackage.xi
    public final int h(View view, int i) {
        return view.getTop();
    }
}
