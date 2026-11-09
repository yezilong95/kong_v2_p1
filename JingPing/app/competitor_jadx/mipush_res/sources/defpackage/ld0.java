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
public class ld0 extends Drawable implements kv0 {
    public static final Paint b0;
    public kd0 E;
    public final iv0[] F;
    public final iv0[] G;
    public final BitSet H;
    public boolean I;
    public final Matrix J;
    public final Path K;
    public final Path L;
    public final RectF M;
    public final RectF N;
    public final Region O;
    public final Region P;
    public zu0 Q;
    public final Paint R;
    public final Paint S;
    public final wu0 T;
    public final z8 U;
    public final bv0 V;
    public PorterDuffColorFilter W;
    public PorterDuffColorFilter X;
    public int Y;
    public final RectF Z;
    public boolean a0;

    static {
        Paint paint = new Paint(1);
        b0 = paint;
        paint.setColor(-1);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT));
    }

    public ld0() {
        this(new zu0());
    }

    public void a() {
        invalidateSelf();
    }

    public final void b(RectF rectF, Path path) {
        kd0 kd0Var = this.E;
        this.V.a(kd0Var.a, kd0Var.i, rectF, this.U, path);
        if (this.E.h != 1.0f) {
            Matrix matrix = this.J;
            matrix.reset();
            float f = this.E.h;
            matrix.setScale(f, f, rectF.width() / 2.0f, rectF.height() / 2.0f);
            path.transform(matrix);
        }
        path.computeBounds(this.Z, true);
    }

    public final int c(int i) {
        kd0 kd0Var = this.E;
        float f = kd0Var.m + 0.0f + kd0Var.l;
        ts tsVar = kd0Var.b;
        return tsVar != null ? tsVar.b(i, f) : i;
    }

    public final void d(Canvas canvas) {
        if (this.H.cardinality() > 0) {
            Log.w("ld0", "Compatibility shadow requested but can't be drawn for all operations in this shape.");
        }
        int i = this.E.p;
        Path path = this.K;
        wu0 wu0Var = this.T;
        if (i != 0) {
            canvas.drawPath(path, wu0Var.a);
        }
        for (int i2 = 0; i2 < 4; i2++) {
            iv0 iv0Var = this.F[i2];
            int i3 = this.E.o;
            Matrix matrix = iv0.b;
            iv0Var.a(matrix, wu0Var, i3, canvas);
            this.G[i2].a(matrix, wu0Var, this.E.o, canvas);
        }
        if (this.a0) {
            int iSin = (int) (Math.sin(Math.toRadians(r0.q)) * ((double) this.E.p));
            int iH = h();
            canvas.translate(-iSin, -iH);
            canvas.drawPath(path, b0);
            canvas.translate(iSin, iH);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:24:0x00b9  */
    @Override // android.graphics.drawable.Drawable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void draw(android.graphics.Canvas r19) {
        /*
            Method dump skipped, instruction units count: 388
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.ld0.draw(android.graphics.Canvas):void");
    }

    public final void e(Canvas canvas, Paint paint, Path path, zu0 zu0Var, RectF rectF) {
        if (!zu0Var.f(rectF)) {
            canvas.drawPath(path, paint);
        } else {
            float fA = zu0Var.f.a(rectF) * this.E.i;
            canvas.drawRoundRect(rectF, fA, fA, paint);
        }
    }

    public void f(Canvas canvas) {
        Paint paint = this.S;
        Path path = this.L;
        zu0 zu0Var = this.Q;
        RectF rectF = this.N;
        rectF.set(g());
        float strokeWidth = j() ? paint.getStrokeWidth() / 2.0f : 0.0f;
        rectF.inset(strokeWidth, strokeWidth);
        e(canvas, paint, path, zu0Var, rectF);
    }

    public final RectF g() {
        RectF rectF = this.M;
        rectF.set(getBounds());
        return rectF;
    }

    @Override // android.graphics.drawable.Drawable
    public int getAlpha() {
        return this.E.k;
    }

    @Override // android.graphics.drawable.Drawable
    public final Drawable.ConstantState getConstantState() {
        return this.E;
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return -3;
    }

    @Override // android.graphics.drawable.Drawable
    public void getOutline(Outline outline) {
        kd0 kd0Var = this.E;
        if (kd0Var.n == 2) {
            return;
        }
        if (kd0Var.a.f(g())) {
            outline.setRoundRect(getBounds(), i() * this.E.i);
        } else {
            RectF rectFG = g();
            Path path = this.K;
            b(rectFG, path);
            qm.s0(outline, path);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public final boolean getPadding(Rect rect) {
        Rect rect2 = this.E.g;
        if (rect2 == null) {
            return super.getPadding(rect);
        }
        rect.set(rect2);
        return true;
    }

    @Override // android.graphics.drawable.Drawable
    public final Region getTransparentRegion() {
        Rect bounds = getBounds();
        Region region = this.O;
        region.set(bounds);
        RectF rectFG = g();
        Path path = this.K;
        b(rectFG, path);
        Region region2 = this.P;
        region2.setPath(path, region);
        region.op(region2, Region.Op.DIFFERENCE);
        return region;
    }

    public final int h() {
        return (int) (Math.cos(Math.toRadians(r0.q)) * ((double) this.E.p));
    }

    public final float i() {
        return this.E.a.e.a(g());
    }

    @Override // android.graphics.drawable.Drawable
    public final void invalidateSelf() {
        this.I = true;
        super.invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public boolean isStateful() {
        if (super.isStateful()) {
            return true;
        }
        ColorStateList colorStateList = this.E.e;
        if (colorStateList != null && colorStateList.isStateful()) {
            return true;
        }
        this.E.getClass();
        ColorStateList colorStateList2 = this.E.d;
        if (colorStateList2 != null && colorStateList2.isStateful()) {
            return true;
        }
        ColorStateList colorStateList3 = this.E.c;
        return colorStateList3 != null && colorStateList3.isStateful();
    }

    public final boolean j() {
        Paint.Style style = this.E.r;
        return (style == Paint.Style.FILL_AND_STROKE || style == Paint.Style.STROKE) && this.S.getStrokeWidth() > 0.0f;
    }

    public final void k(Context context) {
        this.E.b = new ts(context);
        v();
    }

    public final void l(float f) {
        kd0 kd0Var = this.E;
        if (kd0Var.m != f) {
            kd0Var.m = f;
            v();
        }
    }

    public final void m(ColorStateList colorStateList) {
        kd0 kd0Var = this.E;
        if (kd0Var.c != colorStateList) {
            kd0Var.c = colorStateList;
            onStateChange(getState());
        }
    }

    @Override // android.graphics.drawable.Drawable
    public Drawable mutate() {
        this.E = new kd0(this.E);
        return this;
    }

    public final void n(float f) {
        kd0 kd0Var = this.E;
        if (kd0Var.i != f) {
            kd0Var.i = f;
            this.I = true;
            invalidateSelf();
        }
    }

    public final void o() {
        this.E.r = Paint.Style.FILL;
        super.invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public void onBoundsChange(Rect rect) {
        this.I = true;
        super.onBoundsChange(rect);
    }

    @Override // android.graphics.drawable.Drawable, defpackage.uz0
    public boolean onStateChange(int[] iArr) {
        boolean z = t(iArr) || u();
        if (z) {
            invalidateSelf();
        }
        return z;
    }

    public final void p() {
        this.T.a(-12303292);
        this.E.getClass();
        super.invalidateSelf();
    }

    public final void q(int i) {
        kd0 kd0Var = this.E;
        if (kd0Var.n != i) {
            kd0Var.n = i;
            super.invalidateSelf();
        }
    }

    public final void r(ColorStateList colorStateList) {
        kd0 kd0Var = this.E;
        if (kd0Var.d != colorStateList) {
            kd0Var.d = colorStateList;
            onStateChange(getState());
        }
    }

    public final void s(float f) {
        this.E.j = f;
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i) {
        kd0 kd0Var = this.E;
        if (kd0Var.k != i) {
            kd0Var.k = i;
            super.invalidateSelf();
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
        this.E.getClass();
        super.invalidateSelf();
    }

    @Override // defpackage.kv0
    public final void setShapeAppearanceModel(zu0 zu0Var) {
        this.E.a = zu0Var;
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public final void setTint(int i) {
        setTintList(ColorStateList.valueOf(i));
    }

    @Override // android.graphics.drawable.Drawable
    public void setTintList(ColorStateList colorStateList) {
        this.E.e = colorStateList;
        u();
        super.invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public void setTintMode(PorterDuff.Mode mode) {
        kd0 kd0Var = this.E;
        if (kd0Var.f != mode) {
            kd0Var.f = mode;
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
        if (this.E.c == null || color2 == (colorForState2 = this.E.c.getColorForState(iArr, (color2 = (paint2 = this.R).getColor())))) {
            z = false;
        } else {
            paint2.setColor(colorForState2);
            z = true;
        }
        if (this.E.d == null || color == (colorForState = this.E.d.getColorForState(iArr, (color = (paint = this.S).getColor())))) {
            return z;
        }
        paint.setColor(colorForState);
        return true;
    }

    public final boolean u() {
        PorterDuffColorFilter porterDuffColorFilter;
        PorterDuffColorFilter porterDuffColorFilter2 = this.W;
        PorterDuffColorFilter porterDuffColorFilter3 = this.X;
        kd0 kd0Var = this.E;
        ColorStateList colorStateList = kd0Var.e;
        PorterDuff.Mode mode = kd0Var.f;
        Paint paint = this.R;
        if (colorStateList == null || mode == null) {
            int color = paint.getColor();
            int iC = c(color);
            this.Y = iC;
            porterDuffColorFilter = iC != color ? new PorterDuffColorFilter(iC, PorterDuff.Mode.SRC_IN) : null;
        } else {
            int iC2 = c(colorStateList.getColorForState(getState(), 0));
            this.Y = iC2;
            porterDuffColorFilter = new PorterDuffColorFilter(iC2, mode);
        }
        this.W = porterDuffColorFilter;
        this.E.getClass();
        this.X = null;
        this.E.getClass();
        return (Objects.equals(porterDuffColorFilter2, this.W) && Objects.equals(porterDuffColorFilter3, this.X)) ? false : true;
    }

    public final void v() {
        kd0 kd0Var = this.E;
        float f = kd0Var.m + 0.0f;
        kd0Var.o = (int) Math.ceil(0.75f * f);
        this.E.p = (int) Math.ceil(f * 0.25f);
        u();
        super.invalidateSelf();
    }

    public ld0(zu0 zu0Var) {
        this(new kd0(zu0Var));
    }

    public ld0(kd0 kd0Var) {
        bv0 bv0Var;
        this.F = new iv0[4];
        this.G = new iv0[4];
        this.H = new BitSet(8);
        this.J = new Matrix();
        this.K = new Path();
        this.L = new Path();
        this.M = new RectF();
        this.N = new RectF();
        this.O = new Region();
        this.P = new Region();
        Paint paint = new Paint(1);
        this.R = paint;
        Paint paint2 = new Paint(1);
        this.S = paint2;
        this.T = new wu0();
        if (Looper.getMainLooper().getThread() == Thread.currentThread()) {
            bv0Var = av0.a;
        } else {
            bv0Var = new bv0();
        }
        this.V = bv0Var;
        this.Z = new RectF();
        this.a0 = true;
        this.E = kd0Var;
        paint2.setStyle(Paint.Style.STROKE);
        paint.setStyle(Paint.Style.FILL);
        u();
        t(getState());
        this.U = new z8(24, this);
    }

    public ld0(Context context, AttributeSet attributeSet, int i, int i2) {
        this(zu0.c(context, attributeSet, i, i2).a());
    }
}
