package defpackage;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;

/* JADX INFO: loaded from: classes.dex */
public final class p3 extends Drawable.ConstantState {
    public final /* synthetic */ int a = 0;
    public final Object b;

    public p3(r8 r8Var) {
        this.b = r8Var;
    }

    @Override // android.graphics.drawable.Drawable.ConstantState
    public boolean canApplyTheme() {
        switch (this.a) {
            case 0:
                return ((Drawable.ConstantState) this.b).canApplyTheme();
            default:
                return super.canApplyTheme();
        }
    }

    @Override // android.graphics.drawable.Drawable.ConstantState
    public final int getChangingConfigurations() {
        switch (this.a) {
            case 0:
                return ((Drawable.ConstantState) this.b).getChangingConfigurations();
            default:
                return 0;
        }
    }

    @Override // android.graphics.drawable.Drawable.ConstantState
    public final Drawable newDrawable() {
        switch (this.a) {
            case 0:
                q3 q3Var = new q3(null);
                Drawable drawableNewDrawable = ((Drawable.ConstantState) this.b).newDrawable();
                q3Var.b = drawableNewDrawable;
                drawableNewDrawable.setCallback(q3Var.g);
                return q3Var;
            default:
                return (r8) this.b;
        }
    }

    public p3(Drawable.ConstantState constantState) {
        this.b = constantState;
    }

    @Override // android.graphics.drawable.Drawable.ConstantState
    public Drawable newDrawable(Resources resources) {
        switch (this.a) {
            case 0:
                q3 q3Var = new q3(null);
                Drawable drawableNewDrawable = ((Drawable.ConstantState) this.b).newDrawable(resources);
                q3Var.b = drawableNewDrawable;
                drawableNewDrawable.setCallback(q3Var.g);
                return q3Var;
            default:
                return super.newDrawable(resources);
        }
    }

    @Override // android.graphics.drawable.Drawable.ConstantState
    public Drawable newDrawable(Resources resources, Resources.Theme theme) {
        switch (this.a) {
            case 0:
                q3 q3Var = new q3(null);
                Drawable drawableNewDrawable = ((Drawable.ConstantState) this.b).newDrawable(resources, theme);
                q3Var.b = drawableNewDrawable;
                drawableNewDrawable.setCallback(q3Var.g);
                return q3Var;
            default:
                return super.newDrawable(resources, theme);
        }
    }
}
