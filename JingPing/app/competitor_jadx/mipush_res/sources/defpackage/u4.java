package defpackage;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;

/* JADX INFO: loaded from: classes.dex */
public final class u4 extends Drawable.ConstantState {
    public final /* synthetic */ int a = 0;
    public final Object b;

    public u4(qa qaVar) {
        this.b = qaVar;
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
                v4 v4Var = new v4(null);
                Drawable drawableNewDrawable = ((Drawable.ConstantState) this.b).newDrawable();
                v4Var.E = drawableNewDrawable;
                drawableNewDrawable.setCallback(v4Var.J);
                return v4Var;
            default:
                return (qa) this.b;
        }
    }

    public u4(Drawable.ConstantState constantState) {
        this.b = constantState;
    }

    @Override // android.graphics.drawable.Drawable.ConstantState
    public Drawable newDrawable(Resources resources) {
        switch (this.a) {
            case 0:
                v4 v4Var = new v4(null);
                Drawable drawableNewDrawable = ((Drawable.ConstantState) this.b).newDrawable(resources);
                v4Var.E = drawableNewDrawable;
                drawableNewDrawable.setCallback(v4Var.J);
                return v4Var;
            default:
                return super.newDrawable(resources);
        }
    }

    @Override // android.graphics.drawable.Drawable.ConstantState
    public Drawable newDrawable(Resources resources, Resources.Theme theme) {
        switch (this.a) {
            case 0:
                v4 v4Var = new v4(null);
                Drawable drawableNewDrawable = ((Drawable.ConstantState) this.b).newDrawable(resources, theme);
                v4Var.E = drawableNewDrawable;
                drawableNewDrawable.setCallback(v4Var.J);
                return v4Var;
            default:
                return super.newDrawable(resources, theme);
        }
    }
}
