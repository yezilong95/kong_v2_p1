package defpackage;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.VectorDrawable;

/* JADX INFO: loaded from: classes.dex */
public final class uq0 extends Drawable.ConstantState {
    public final Drawable.ConstantState a;

    public uq0(Drawable.ConstantState constantState) {
        this.a = constantState;
    }

    @Override // android.graphics.drawable.Drawable.ConstantState
    public final boolean canApplyTheme() {
        return this.a.canApplyTheme();
    }

    @Override // android.graphics.drawable.Drawable.ConstantState
    public int getChangingConfigurations() {
        return this.a.getChangingConfigurations();
    }

    @Override // android.graphics.drawable.Drawable.ConstantState
    public final Drawable newDrawable() {
        vq0 vq0Var = new vq0();
        vq0Var.b = (VectorDrawable) this.a.newDrawable();
        return vq0Var;
    }

    @Override // android.graphics.drawable.Drawable.ConstantState
    public final Drawable newDrawable(Resources resources) {
        vq0 vq0Var = new vq0();
        vq0Var.b = (VectorDrawable) this.a.newDrawable(resources);
        return vq0Var;
    }

    @Override // android.graphics.drawable.Drawable.ConstantState
    public final Drawable newDrawable(Resources resources, Resources.Theme theme) {
        vq0 vq0Var = new vq0();
        vq0Var.b = (VectorDrawable) this.a.newDrawable(resources, theme);
        return vq0Var;
    }
}
