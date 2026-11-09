package defpackage;

import android.view.View;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.search.SearchView;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class zr0 implements n61, vi0 {
    public final /* synthetic */ SearchView E;

    public /* synthetic */ zr0(SearchView searchView) {
        this.E = searchView;
    }

    @Override // defpackage.vi0
    public s81 h(View view, s81 s81Var) {
        SearchView.e(this.E, s81Var);
        return s81Var;
    }

    @Override // defpackage.n61
    public s81 o(View view, s81 s81Var, o61 o61Var) {
        MaterialToolbar materialToolbar = this.E.K;
        boolean zD0 = fa0.d0(materialToolbar);
        materialToolbar.setPadding(s81Var.b() + (zD0 ? o61Var.c : o61Var.a), o61Var.b, s81Var.c() + (zD0 ? o61Var.a : o61Var.c), o61Var.d);
        return s81Var;
    }
}
