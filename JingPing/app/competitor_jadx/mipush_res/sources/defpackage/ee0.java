package defpackage;

import android.view.ActionProvider;
import android.view.View;

/* JADX INFO: loaded from: classes.dex */
public final class ee0 extends m3 implements ActionProvider.VisibilityListener {
    public final ActionProvider b;
    public final /* synthetic */ he0 c;
    public p50 d;

    public ee0(he0 he0Var, ActionProvider actionProvider) {
        this.c = he0Var;
        this.b = actionProvider;
    }

    @Override // defpackage.m3
    public final boolean a() {
        return this.b.isVisible();
    }

    @Override // defpackage.m3
    public final View b(de0 de0Var) {
        return this.b.onCreateActionView(de0Var);
    }

    @Override // defpackage.m3
    public final boolean c() {
        return this.b.overridesItemVisibility();
    }

    @Override // defpackage.m3
    public final void d(p50 p50Var) {
        this.d = p50Var;
        this.b.setVisibilityListener(this);
    }

    @Override // android.view.ActionProvider.VisibilityListener
    public final void onActionProviderVisibilityChanged(boolean z) {
        p50 p50Var = this.d;
        if (p50Var != null) {
            zd0 zd0Var = ((de0) p50Var.F).n;
            zd0Var.h = true;
            zd0Var.p(true);
        }
    }
}
