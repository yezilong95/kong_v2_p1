package defpackage;

import android.graphics.Canvas;
import android.graphics.RectF;
import android.graphics.Region;
import android.graphics.drawable.Drawable;
import android.os.Build;

/* JADX INFO: loaded from: classes.dex */
public final class jk extends ld0 {
    public static final /* synthetic */ int d0 = 0;
    public ik c0;

    @Override // defpackage.ld0
    public final void f(Canvas canvas) {
        if (this.c0.s.isEmpty()) {
            super.f(canvas);
            return;
        }
        canvas.save();
        if (Build.VERSION.SDK_INT >= 26) {
            canvas.clipOutRect(this.c0.s);
        } else {
            canvas.clipRect(this.c0.s, Region.Op.DIFFERENCE);
        }
        super.f(canvas);
        canvas.restore();
    }

    @Override // defpackage.ld0, android.graphics.drawable.Drawable
    public final Drawable mutate() {
        this.c0 = new ik(this.c0);
        return this;
    }

    public final void w(float f, float f2, float f3, float f4) {
        RectF rectF = this.c0.s;
        if (f == rectF.left && f2 == rectF.top && f3 == rectF.right && f4 == rectF.bottom) {
            return;
        }
        rectF.set(f, f2, f3, f4);
        invalidateSelf();
    }
}
