package defpackage;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

/* JADX INFO: loaded from: classes.dex */
public abstract class wl {
    public final e8 a;
    public nl b;

    public wl(e8 e8Var) {
        this.a = e8Var;
    }

    public abstract void a(Canvas canvas, Rect rect, float f);

    public abstract void b(Canvas canvas, Paint paint, float f, float f2, int i);

    public abstract void c(Canvas canvas, Paint paint);

    public abstract int d();

    public abstract int e();
}
