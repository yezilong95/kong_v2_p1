package defpackage;

import android.view.ActionProvider;
import android.view.View;

/* JADX INFO: loaded from: classes.dex */
public final class n60 extends r1 implements ActionProvider.VisibilityListener {
    public final ActionProvider b;
    public final /* synthetic */ q60 c;
    public o70 d;

    public n60(q60 q60Var, ActionProvider actionProvider) {
        this.c = q60Var;
        this.b = actionProvider;
    }

    @Override // defpackage.r1
    public final boolean a() {
        return this.b.isVisible();
    }

    @Override // defpackage.r1
    public final View b(m60 m60Var) {
        return this.b.onCreateActionView(m60Var);
    }

    @Override // defpackage.r1
    public final boolean c() {
        return this.b.overridesItemVisibility();
    }

    @Override // defpackage.r1
    public final void d(o70 o70Var) {
        this.d = o70Var;
        this.b.setVisibilityListener(this);
    }

    @Override // android.view.ActionProvider.VisibilityListener
    public final void onActionProviderVisibilityChanged(boolean z) {
        o70 o70Var = this.d;
        if (o70Var != null) {
            i60 i60Var = ((m60) o70Var.c).n;
            i60Var.h = true;
            i60Var.p(true);
        }
    }
}
