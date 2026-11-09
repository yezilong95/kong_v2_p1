package defpackage;

import android.graphics.RectF;
import android.graphics.drawable.Drawable;

/* JADX INFO: loaded from: classes.dex */
public final class ik extends kd0 {
    public final RectF s;

    public ik(zu0 zu0Var, RectF rectF) {
        super(zu0Var);
        this.s = rectF;
    }

    @Override // defpackage.kd0, android.graphics.drawable.Drawable.ConstantState
    public final Drawable newDrawable() {
        jk jkVar = new jk(this);
        jkVar.c0 = this;
        jkVar.invalidateSelf();
        return jkVar;
    }

    public ik(ik ikVar) {
        super(ikVar);
        this.s = ikVar.s;
    }
}
