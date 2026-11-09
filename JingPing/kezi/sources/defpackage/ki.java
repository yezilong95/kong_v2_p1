package defpackage;

import android.graphics.Canvas;
import android.graphics.RectF;
import android.graphics.Region;
import android.graphics.drawable.Drawable;
import android.os.Build;

/* JADX INFO: loaded from: classes.dex */
public final class ki extends u50 {
    public static final /* synthetic */ int z = 0;
    public ji y;

    @Override // defpackage.u50
    public final void f(Canvas canvas) {
        if (this.y.s.isEmpty()) {
            super.f(canvas);
            return;
        }
        canvas.save();
        if (Build.VERSION.SDK_INT >= 26) {
            canvas.clipOutRect(this.y.s);
        } else {
            canvas.clipRect(this.y.s, Region.Op.DIFFERENCE);
        }
        super.f(canvas);
        canvas.restore();
    }

    @Override // defpackage.u50, android.graphics.drawable.Drawable
    public final Drawable mutate() {
        this.y = new ji(this.y);
        return this;
    }

    public final void w(float f, float f2, float f3, float f4) {
        RectF rectF = this.y.s;
        if (f == rectF.left && f2 == rectF.top && f3 == rectF.right && f4 == rectF.bottom) {
            return;
        }
        rectF.set(f, f2, f3, f4);
        invalidateSelf();
    }
}
