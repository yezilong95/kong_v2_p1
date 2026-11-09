package androidx.drawerlayout.widget;

import android.view.View;
import androidx.drawerlayout.widget.DrawerLayout;
import defpackage.f;
import defpackage.k51;
import defpackage.ma0;

/* JADX INFO: loaded from: classes.dex */
public final class b extends ma0 {
    public final int q;
    public k51 r;
    public final f s = new f(10, this);
    public final /* synthetic */ DrawerLayout t;

    public b(DrawerLayout drawerLayout, int i) {
        this.t = drawerLayout;
        this.q = i;
    }

    @Override // defpackage.ma0
    public final int D(View view) {
        if (DrawerLayout.l(view)) {
            return view.getWidth();
        }
        return 0;
    }

    @Override // defpackage.ma0
    public final void c0(int i, int i2) {
        int i3 = i & 1;
        DrawerLayout drawerLayout = this.t;
        View viewD = i3 == 1 ? drawerLayout.d(3) : drawerLayout.d(5);
        if (viewD == null || drawerLayout.g(viewD) != 0) {
            return;
        }
        this.r.b(viewD, i2);
    }

    @Override // defpackage.ma0
    public final void d0() {
        this.t.postDelayed(this.s, 160L);
    }

    @Override // defpackage.ma0
    public final void e0(View view, int i) {
        ((DrawerLayout.LayoutParams) view.getLayoutParams()).c = false;
        int i2 = this.q == 3 ? 5 : 3;
        DrawerLayout drawerLayout = this.t;
        View viewD = drawerLayout.d(i2);
        if (viewD != null) {
            drawerLayout.b(viewD, true);
        }
    }

    @Override // defpackage.ma0
    public final void f0(int i) {
        this.t.r(this.r.t, i);
    }

    @Override // defpackage.ma0
    public final void g0(View view, int i, int i2) {
        int width = view.getWidth();
        DrawerLayout drawerLayout = this.t;
        float width2 = (drawerLayout.a(view, 3) ? i + width : drawerLayout.getWidth() - i) / width;
        drawerLayout.o(view, width2);
        view.setVisibility(width2 == 0.0f ? 4 : 0);
        drawerLayout.invalidate();
    }

    @Override // defpackage.ma0
    public final void h0(View view, float f, float f2) {
        int i;
        DrawerLayout drawerLayout = this.t;
        int[] iArr = DrawerLayout.k0;
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
        this.r.q(i, view.getTop());
        drawerLayout.invalidate();
    }

    @Override // defpackage.ma0
    public final int k(View view, int i) {
        DrawerLayout drawerLayout = this.t;
        if (drawerLayout.a(view, 3)) {
            return Math.max(-view.getWidth(), Math.min(i, 0));
        }
        int width = drawerLayout.getWidth();
        return Math.max(width - view.getWidth(), Math.min(i, width));
    }

    @Override // defpackage.ma0
    public final int l(View view, int i) {
        return view.getTop();
    }

    @Override // defpackage.ma0
    public final boolean x0(View view, int i) {
        DrawerLayout drawerLayout = this.t;
        return DrawerLayout.l(view) && drawerLayout.a(view, this.q) && drawerLayout.g(view) == 0;
    }
}
