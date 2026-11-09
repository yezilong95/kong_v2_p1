package defpackage;

import android.graphics.RectF;
import android.graphics.drawable.Drawable;

/* JADX INFO: loaded from: classes.dex */
public final class ji extends t50 {
    public final RectF s;

    public ji(ki0 ki0Var, RectF rectF) {
        super(ki0Var);
        this.s = rectF;
    }

    @Override // defpackage.t50, android.graphics.drawable.Drawable.ConstantState
    public final Drawable newDrawable() {
        ki kiVar = new ki(this);
        kiVar.y = this;
        kiVar.invalidateSelf();
        return kiVar;
    }

    public ji(ji jiVar) {
        super(jiVar);
        this.s = jiVar.s;
    }
}
