package defpackage;

import android.content.ContentResolver;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.provider.Settings;

/* JADX INFO: loaded from: classes.dex */
public final class vx extends nl {
    public final wl m;
    public ux n;

    public vx(Context context, e8 e8Var, wl wlVar, ux uxVar) {
        super(context, e8Var);
        this.m = wlVar;
        wlVar.b = this;
        this.n = uxVar;
        uxVar.a = this;
    }

    @Override // android.graphics.drawable.Drawable
    public final void draw(Canvas canvas) {
        Rect rect = new Rect();
        if (getBounds().isEmpty() || !isVisible() || !canvas.getClipBounds(rect)) {
            return;
        }
        canvas.save();
        wl wlVar = this.m;
        Rect bounds = getBounds();
        float fB = b();
        wlVar.a.a();
        wlVar.a(canvas, bounds, fB);
        wl wlVar2 = this.m;
        Paint paint = this.j;
        wlVar2.c(canvas, paint);
        int i = 0;
        while (true) {
            ux uxVar = this.n;
            int[] iArr = (int[]) uxVar.c;
            if (i >= iArr.length) {
                canvas.restore();
                return;
            }
            wl wlVar3 = this.m;
            float[] fArr = (float[]) uxVar.b;
            int i2 = i * 2;
            wlVar3.b(canvas, paint, fArr[i2], fArr[i2 + 1], iArr[i]);
            i++;
        }
    }

    @Override // defpackage.nl
    public final boolean f(boolean z, boolean z2, boolean z3) {
        boolean zF = super.f(z, z2, z3);
        if (!isRunning()) {
            this.n.a();
        }
        u3 u3Var = this.d;
        ContentResolver contentResolver = this.b.getContentResolver();
        u3Var.getClass();
        Settings.Global.getFloat(contentResolver, "animator_duration_scale", 1.0f);
        if (z && z3) {
            this.n.o();
        }
        return zF;
    }

    @Override // android.graphics.drawable.Drawable
    public final int getIntrinsicHeight() {
        return this.m.d();
    }

    @Override // android.graphics.drawable.Drawable
    public final int getIntrinsicWidth() {
        return this.m.e();
    }
}
