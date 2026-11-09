package defpackage;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.VectorDrawable;

/* JADX INFO: loaded from: classes.dex */
public final class z31 extends Drawable.ConstantState {
    public final Drawable.ConstantState a;

    public z31(Drawable.ConstantState constantState) {
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
        a41 a41Var = new a41();
        a41Var.E = (VectorDrawable) this.a.newDrawable();
        return a41Var;
    }

    @Override // android.graphics.drawable.Drawable.ConstantState
    public final Drawable newDrawable(Resources resources) {
        a41 a41Var = new a41();
        a41Var.E = (VectorDrawable) this.a.newDrawable(resources);
        return a41Var;
    }

    @Override // android.graphics.drawable.Drawable.ConstantState
    public final Drawable newDrawable(Resources resources, Resources.Theme theme) {
        a41 a41Var = new a41();
        a41Var.E = (VectorDrawable) this.a.newDrawable(resources, theme);
        return a41Var;
    }
}
