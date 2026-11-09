package defpackage;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

/* JADX INFO: loaded from: classes.dex */
public abstract class aq {
    public final aa a;

    public aq(aa aaVar) {
        this.a = aaVar;
    }

    public abstract void a(Canvas canvas, Rect rect, float f, boolean z, boolean z2);

    public abstract void b(Canvas canvas, Paint paint, int i, int i2);

    public abstract void c(Canvas canvas, Paint paint, zp zpVar, int i);

    public abstract void d(Canvas canvas, Paint paint, float f, float f2, int i, int i2, int i3);

    public abstract int e();

    public abstract int f();
}
