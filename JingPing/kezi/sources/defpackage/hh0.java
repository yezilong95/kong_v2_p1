package defpackage;

import android.view.View;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.search.SearchView;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class hh0 implements ht0, ha0 {
    public final /* synthetic */ SearchView b;

    public /* synthetic */ hh0(SearchView searchView) {
        this.b = searchView;
    }

    @Override // defpackage.ha0
    public xu0 e(View view, xu0 xu0Var) {
        SearchView.e(this.b, xu0Var);
        return xu0Var;
    }

    @Override // defpackage.ht0
    public xu0 f(View view, xu0 xu0Var, it0 it0Var) {
        MaterialToolbar materialToolbar = this.b.h;
        boolean zI = rp0.I(materialToolbar);
        materialToolbar.setPadding(xu0Var.b() + (zI ? it0Var.c : it0Var.a), it0Var.b, xu0Var.c() + (zI ? it0Var.a : it0Var.c), it0Var.d);
        return xu0Var;
    }
}
