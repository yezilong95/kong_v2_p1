package defpackage;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Outline;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Region;
import android.graphics.drawable.Drawable;
import android.os.Looper;
import android.util.AttributeSet;
import android.util.Log;
import java.util.BitSet;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public class u50 extends Drawable implements vi0 {
    public static final Paint x;
    public t50 b;
    public final ti0[] c;
    public final ti0[] d;
    public final BitSet e;
    public boolean f;
    public final Matrix g;
    public final Path h;
    public final Path i;
    public final RectF j;
    public final RectF k;
    public final Region l;
    public final Region m;
    public ki0 n;
    public final Paint o;
    public final Paint p;
    public final hi0 q;
    public final o70 r;
    public final mi0 s;
    public PorterDuffColorFilter t;
    public PorterDuffColorFilter u;
    public final RectF v;
    public boolean w;

    static {
        Paint paint = new Paint(1);
        x = paint;
        paint.setColor(-1);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT));
    }

    public u50() {
        this(new ki0());
    }

    public void a() {
        invalidateSelf();
    }

    public final void b(RectF rectF, Path path) {
        t50 t50Var = this.b;
        this.s.a(t50Var.a, t50Var.i, rectF, this.r, path);
        if (this.b.h != 1.0f) {
            Matrix matrix = this.g;
            matrix.reset();
            float f = this.b.h;
            matrix.setScale(f, f, rectF.width() / 2.0f, rectF.height() / 2.0f);
            path.transform(matrix);
        }
        path.computeBounds(this.v, true);
    }

    public final int c(int i) {
        t50 t50Var = this.b;
        float f = t50Var.m + 0.0f + t50Var.l;
        bn bnVar = t50Var.b;
        return bnVar != null ? bnVar.b(i, f) : i;
    }

    public final void d(Canvas canvas) {
        if (this.e.cardinality() > 0) {
            Log.w("u50", "Compatibility shadow requested but can't be drawn for all operations in this shape.");
        }
        int i = this.b.p;
        Path path = this.h;
        hi0 hi0Var = this.q;
        if (i != 0) {
            canvas.drawPath(path, hi0Var.a);
        }
        for (int i2 = 0; i2 < 4; i2++) {
            ti0 ti0Var = this.c[i2];
            int i3 = this.b.o;
            Matrix matrix = ti0.b;
            ti0Var.a(matrix, hi0Var, i3, canvas);
            this.d[i2].a(matrix, hi0Var, this.b.o, canvas);
        }
        if (this.w) {
            int iSin = (int) (Math.sin(Math.toRadians(r0.q)) * ((double) this.b.p));
            int iH = h();
            canvas.translate(-iSin, -iH);
            canvas.drawPath(path, x);
            canvas.translate(iSin, iH);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:24:0x00b9  */
    @Override // android.graphics.drawable.Drawable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void draw(android.graphics.Canvas r19) {
        /*
            Method dump skipped, instruction units count: 388
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.u50.draw(android.graphics.Canvas):void");
    }

    public final void e(Canvas canvas, Paint paint, Path path, ki0 ki0Var, RectF rectF) {
        if (!ki0Var.f(rectF)) {
            canvas.drawPath(path, paint);
        } else {
            float fA = ki0Var.f.a(rectF) * this.b.i;
            canvas.drawRoundRect(rectF, fA, fA, paint);
        }
    }

    public void f(Canvas canvas) {
        Paint paint = this.p;
        Path path = this.i;
        ki0 ki0Var = this.n;
        RectF rectF = this.k;
        rectF.set(g());
        float strokeWidth = j() ? paint.getStrokeWidth() / 2.0f : 0.0f;
        rectF.inset(strokeWidth, strokeWidth);
        e(canvas, paint, path, ki0Var, rectF);
    }

    public final RectF g() {
        RectF rectF = this.j;
        rectF.set(getBounds());
        return rectF;
    }

    @Override // android.graphics.drawable.Drawable
    public int getAlpha() {
        return this.b.k;
    }

    @Override // android.graphics.drawable.Drawable
    public final Drawable.ConstantState getConstantState() {
        return this.b;
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return -3;
    }

    @Override // android.graphics.drawable.Drawable
    public void getOutline(Outline outline) {
        t50 t50Var = this.b;
        if (t50Var.n == 2) {
            return;
        }
        if (t50Var.a.f(g())) {
            outline.setRoundRect(getBounds(), i() * this.b.i);
        } else {
            RectF rectFG = g();
            Path path = this.h;
            b(rectFG, path);
            k9.H(outline, path);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public final boolean getPadding(Rect rect) {
        Rect rect2 = this.b.g;
        if (rect2 == null) {
            return super.getPadding(rect);
        }
        rect.set(rect2);
        return true;
    }

    @Override // android.graphics.drawable.Drawable
    public final Region getTransparentRegion() {
        Rect bounds = getBounds();
        Region region = this.l;
        region.set(bounds);
        RectF rectFG = g();
        Path path = this.h;
        b(rectFG, path);
        Region region2 = this.m;
        region2.setPath(path, region);
        region.op(region2, Region.Op.DIFFERENCE);
        return region;
    }

    public final int h() {
        return (int) (Math.cos(Math.toRadians(r0.q)) * ((double) this.b.p));
    }

    public final float i() {
        return this.b.a.e.a(g());
    }

    @Override // android.graphics.drawable.Drawable
    public final void invalidateSelf() {
        this.f = true;
        super.invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public boolean isStateful() {
        if (super.isStateful()) {
            return true;
        }
        ColorStateList colorStateList = this.b.e;
        if (colorStateList != null && colorStateList.isStateful()) {
            return true;
        }
        this.b.getClass();
        ColorStateList colorStateList2 = this.b.d;
        if (colorStateList2 != null && colorStateList2.isStateful()) {
            return true;
        }
        ColorStateList colorStateList3 = this.b.c;
        return colorStateList3 != null && colorStateList3.isStateful();
    }

    public final boolean j() {
        Paint.Style style = this.b.r;
        return (style == Paint.Style.FILL_AND_STROKE || style == Paint.Style.STROKE) && this.p.getStrokeWidth() > 0.0f;
    }

    public final void k(Context context) {
        this.b.b = new bn(context);
        v();
    }

    public final void l(float f) {
        t50 t50Var = this.b;
        if (t50Var.m != f) {
            t50Var.m = f;
            v();
        }
    }

    public final void m(ColorStateList colorStateList) {
        t50 t50Var = this.b;
        if (t50Var.c != colorStateList) {
            t50Var.c = colorStateList;
            onStateChange(getState());
        }
    }

    @Override // android.graphics.drawable.Drawable
    public Drawable mutate() {
        this.b = new t50(this.b);
        return this;
    }

    public final void n(float f) {
        t50 t50Var = this.b;
        if (t50Var.i != f) {
            t50Var.i = f;
            this.f = true;
            invalidateSelf();
        }
    }

    public final void o() {
        this.b.r = Paint.Style.FILL;
        super.invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public void onBoundsChange(Rect rect) {
        this.f = true;
        super.onBoundsChange(rect);
    }

    @Override // android.graphics.drawable.Drawable, defpackage.gn0
    public boolean onStateChange(int[] iArr) {
        boolean z = t(iArr) || u();
        if (z) {
            invalidateSelf();
        }
        return z;
    }

    public final void p() {
        this.q.a(-12303292);
        this.b.getClass();
        super.invalidateSelf();
    }

    public final void q(int i) {
        t50 t50Var = this.b;
        if (t50Var.n != i) {
            t50Var.n = i;
            super.invalidateSelf();
        }
    }

    public final void r(ColorStateList colorStateList) {
        t50 t50Var = this.b;
        if (t50Var.d != colorStateList) {
            t50Var.d = colorStateList;
            onStateChange(getState());
        }
    }

    public final void s(float f) {
        this.b.j = f;
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i) {
        t50 t50Var = this.b;
        if (t50Var.k != i) {
            t50Var.k = i;
            super.invalidateSelf();
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
        this.b.getClass();
        super.invalidateSelf();
    }

    @Override // defpackage.vi0
    public final void setShapeAppearanceModel(ki0 ki0Var) {
        this.b.a = ki0Var;
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public final void setTint(int i) {
        setTintList(ColorStateList.valueOf(i));
    }

    @Override // android.graphics.drawable.Drawable
    public void setTintList(ColorStateList colorStateList) {
        this.b.e = colorStateList;
        u();
        super.invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public void setTintMode(PorterDuff.Mode mode) {
        t50 t50Var = this.b;
        if (t50Var.f != mode) {
            t50Var.f = mode;
            u();
            super.invalidateSelf();
        }
    }

    public final boolean t(int[] iArr) {
        boolean z;
        Paint paint;
        int color;
        int colorForState;
        Paint paint2;
        int color2;
        int colorForState2;
        if (this.b.c == null || color2 == (colorForState2 = this.b.c.getColorForState(iArr, (color2 = (paint2 = this.o).getColor())))) {
            z = false;
        } else {
            paint2.setColor(colorForState2);
            z = true;
        }
        if (this.b.d == null || color == (colorForState = this.b.d.getColorForState(iArr, (color = (paint = this.p).getColor())))) {
            return z;
        }
        paint.setColor(colorForState);
        return true;
    }

    public final boolean u() {
        PorterDuffColorFilter porterDuffColorFilter;
        PorterDuffColorFilter porterDuffColorFilter2 = this.t;
        PorterDuffColorFilter porterDuffColorFilter3 = this.u;
        t50 t50Var = this.b;
        ColorStateList colorStateList = t50Var.e;
        PorterDuff.Mode mode = t50Var.f;
        Paint paint = this.o;
        if (colorStateList == null || mode == null) {
            int color = paint.getColor();
            int iC = c(color);
            porterDuffColorFilter = iC != color ? new PorterDuffColorFilter(iC, PorterDuff.Mode.SRC_IN) : null;
        } else {
            porterDuffColorFilter = new PorterDuffColorFilter(c(colorStateList.getColorForState(getState(), 0)), mode);
        }
        this.t = porterDuffColorFilter;
        this.b.getClass();
        this.u = null;
        this.b.getClass();
        return (Objects.equals(porterDuffColorFilter2, this.t) && Objects.equals(porterDuffColorFilter3, this.u)) ? false : true;
    }

    public final void v() {
        t50 t50Var = this.b;
        float f = t50Var.m + 0.0f;
        t50Var.o = (int) Math.ceil(0.75f * f);
        this.b.p = (int) Math.ceil(f * 0.25f);
        u();
        super.invalidateSelf();
    }

    public u50(ki0 ki0Var) {
        this(new t50(ki0Var));
    }

    public u50(t50 t50Var) {
        mi0 mi0Var;
        this.c = new ti0[4];
        this.d = new ti0[4];
        this.e = new BitSet(8);
        this.g = new Matrix();
        this.h = new Path();
        this.i = new Path();
        this.j = new RectF();
        this.k = new RectF();
        this.l = new Region();
        this.m = new Region();
        Paint paint = new Paint(1);
        this.o = paint;
        Paint paint2 = new Paint(1);
        this.p = paint2;
        this.q = new hi0();
        if (Looper.getMainLooper().getThread() == Thread.currentThread()) {
            mi0Var = li0.a;
        } else {
            mi0Var = new mi0();
        }
        this.s = mi0Var;
        this.v = new RectF();
        this.w = true;
        this.b = t50Var;
        paint2.setStyle(Paint.Style.STROKE);
        paint.setStyle(Paint.Style.FILL);
        u();
        t(getState());
        this.r = new o70(27, this);
    }

    public u50(Context context, AttributeSet attributeSet, int i, int i2) {
        this(ki0.c(context, attributeSet, i, i2).a());
    }
}
