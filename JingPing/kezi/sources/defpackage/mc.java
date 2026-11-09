package defpackage;

import android.R;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Outline;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.RippleDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.text.SpannableStringBuilder;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;
import com.google.android.material.chip.Chip;
import java.lang.ref.WeakReference;
import java.util.Arrays;
import org.java_websocket.extensions.ExtensionRequestData;

/* JADX INFO: loaded from: classes.dex */
public final class mc extends u50 implements Drawable.Callback, gn0 {
    public static final int[] G0 = {R.attr.state_enabled};
    public static final ShapeDrawable H0 = new ShapeDrawable(new OvalShape());
    public float A;
    public ColorStateList A0;
    public float B;
    public WeakReference B0;
    public ColorStateList C;
    public TextUtils.TruncateAt C0;
    public float D;
    public boolean D0;
    public ColorStateList E;
    public int E0;
    public CharSequence F;
    public boolean F0;
    public boolean G;
    public Drawable H;
    public ColorStateList I;
    public float J;
    public boolean K;
    public boolean L;
    public Drawable M;
    public RippleDrawable N;
    public ColorStateList O;
    public float P;
    public SpannableStringBuilder Q;
    public boolean R;
    public boolean S;
    public Drawable T;
    public ColorStateList U;
    public x70 V;
    public x70 W;
    public float X;
    public float Y;
    public float Z;
    public float a0;
    public float b0;
    public float c0;
    public float d0;
    public float e0;
    public final Context f0;
    public final Paint g0;
    public final Paint.FontMetrics h0;
    public final RectF i0;
    public final PointF j0;
    public final Path k0;
    public final hn0 l0;
    public int m0;
    public int n0;
    public int o0;
    public int p0;
    public int q0;
    public int r0;
    public boolean s0;
    public int t0;
    public int u0;
    public ColorFilter v0;
    public PorterDuffColorFilter w0;
    public ColorStateList x0;
    public ColorStateList y;
    public PorterDuff.Mode y0;
    public ColorStateList z;
    public int[] z0;

    public mc(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i, Chip.y);
        this.B = -1.0f;
        this.g0 = new Paint(1);
        this.h0 = new Paint.FontMetrics();
        this.i0 = new RectF();
        this.j0 = new PointF();
        this.k0 = new Path();
        this.u0 = 255;
        this.y0 = PorterDuff.Mode.SRC_IN;
        this.B0 = new WeakReference(null);
        k(context);
        this.f0 = context;
        hn0 hn0Var = new hn0(this);
        this.l0 = hn0Var;
        this.F = ExtensionRequestData.EMPTY_VALUE;
        hn0Var.a.density = context.getResources().getDisplayMetrics().density;
        int[] iArr = G0;
        setState(iArr);
        if (!Arrays.equals(this.z0, iArr)) {
            this.z0 = iArr;
            if (b0()) {
                E(getState(), iArr);
            }
        }
        this.D0 = true;
        int[] iArr2 = lf0.a;
        H0.setTint(-1);
    }

    public static boolean B(ColorStateList colorStateList) {
        return colorStateList != null && colorStateList.isStateful();
    }

    public static boolean C(Drawable drawable) {
        return drawable != null && drawable.isStateful();
    }

    public static void c0(Drawable drawable) {
        if (drawable != null) {
            drawable.setCallback(null);
        }
    }

    public final float A() {
        return this.F0 ? i() : this.B;
    }

    public final void D() {
        lc lcVar = (lc) this.B0.get();
        if (lcVar != null) {
            Chip chip = (Chip) lcVar;
            chip.c(chip.r);
            chip.requestLayout();
            chip.invalidateOutline();
        }
    }

    public final boolean E(int[] iArr, int[] iArr2) {
        boolean z;
        boolean z2;
        ColorStateList colorStateList;
        boolean state = true;
        boolean zOnStateChange = super.onStateChange(iArr);
        ColorStateList colorStateList2 = this.y;
        int iC = c(colorStateList2 != null ? colorStateList2.getColorForState(iArr, this.m0) : 0);
        if (this.m0 != iC) {
            this.m0 = iC;
            zOnStateChange = true;
        }
        ColorStateList colorStateList3 = this.z;
        int iC2 = c(colorStateList3 != null ? colorStateList3.getColorForState(iArr, this.n0) : 0);
        if (this.n0 != iC2) {
            this.n0 = iC2;
            zOnStateChange = true;
        }
        int iC3 = je.c(iC2, iC);
        if ((this.o0 != iC3) | (this.b.c == null)) {
            this.o0 = iC3;
            m(ColorStateList.valueOf(iC3));
            zOnStateChange = true;
        }
        ColorStateList colorStateList4 = this.C;
        int colorForState = colorStateList4 != null ? colorStateList4.getColorForState(iArr, this.p0) : 0;
        if (this.p0 != colorForState) {
            this.p0 = colorForState;
            zOnStateChange = true;
        }
        int colorForState2 = (this.A0 == null || !lf0.d(iArr)) ? 0 : this.A0.getColorForState(iArr, this.q0);
        if (this.q0 != colorForState2) {
            this.q0 = colorForState2;
        }
        en0 en0Var = this.l0.g;
        int colorForState3 = (en0Var == null || (colorStateList = en0Var.j) == null) ? 0 : colorStateList.getColorForState(iArr, this.r0);
        if (this.r0 != colorForState3) {
            this.r0 = colorForState3;
            zOnStateChange = true;
        }
        int[] state2 = getState();
        if (state2 == null) {
            z = false;
        } else {
            int length = state2.length;
            int i = 0;
            while (true) {
                if (i >= length) {
                    break;
                }
                if (state2[i] != 16842912) {
                    i++;
                } else if (this.R) {
                    z = true;
                }
            }
            z = false;
        }
        if (this.s0 == z || this.T == null) {
            z2 = false;
        } else {
            float fY = y();
            this.s0 = z;
            if (fY != y()) {
                zOnStateChange = true;
                z2 = true;
            } else {
                zOnStateChange = true;
                z2 = false;
            }
        }
        ColorStateList colorStateList5 = this.x0;
        int colorForState4 = colorStateList5 != null ? colorStateList5.getColorForState(iArr, this.t0) : 0;
        if (this.t0 != colorForState4) {
            this.t0 = colorForState4;
            ColorStateList colorStateList6 = this.x0;
            PorterDuff.Mode mode = this.y0;
            this.w0 = (colorStateList6 == null || mode == null) ? null : new PorterDuffColorFilter(colorStateList6.getColorForState(getState(), 0), mode);
        } else {
            state = zOnStateChange;
        }
        if (C(this.H)) {
            state |= this.H.setState(iArr);
        }
        if (C(this.T)) {
            state |= this.T.setState(iArr);
        }
        if (C(this.M)) {
            int[] iArr3 = new int[iArr.length + iArr2.length];
            System.arraycopy(iArr, 0, iArr3, 0, iArr.length);
            System.arraycopy(iArr2, 0, iArr3, iArr.length, iArr2.length);
            state |= this.M.setState(iArr3);
        }
        int[] iArr4 = lf0.a;
        if (C(this.N)) {
            state |= this.N.setState(iArr2);
        }
        if (state) {
            invalidateSelf();
        }
        if (z2) {
            D();
        }
        return state;
    }

    public final void F(boolean z) {
        if (this.R != z) {
            this.R = z;
            float fY = y();
            if (!z && this.s0) {
                this.s0 = false;
            }
            float fY2 = y();
            invalidateSelf();
            if (fY != fY2) {
                D();
            }
        }
    }

    public final void G(Drawable drawable) {
        if (this.T != drawable) {
            float fY = y();
            this.T = drawable;
            float fY2 = y();
            c0(this.T);
            w(this.T);
            invalidateSelf();
            if (fY != fY2) {
                D();
            }
        }
    }

    public final void H(ColorStateList colorStateList) {
        Drawable drawable;
        if (this.U != colorStateList) {
            this.U = colorStateList;
            if (this.S && (drawable = this.T) != null && this.R) {
                hl.h(drawable, colorStateList);
            }
            onStateChange(getState());
        }
    }

    public final void I(boolean z) {
        if (this.S != z) {
            boolean Z = Z();
            this.S = z;
            boolean Z2 = Z();
            if (Z != Z2) {
                if (Z2) {
                    w(this.T);
                } else {
                    c0(this.T);
                }
                invalidateSelf();
                D();
            }
        }
    }

    public final void J(float f) {
        if (this.B != f) {
            this.B = f;
            ii0 ii0VarG = this.b.a.g();
            ii0VarG.c(f);
            setShapeAppearanceModel(ii0VarG.a());
        }
    }

    public final void K(Drawable drawable) {
        Drawable drawable2 = this.H;
        Drawable drawableQ = drawable2 != null ? nv0.Q(drawable2) : null;
        if (drawableQ != drawable) {
            float fY = y();
            this.H = drawable != null ? drawable.mutate() : null;
            float fY2 = y();
            c0(drawableQ);
            if (a0()) {
                w(this.H);
            }
            invalidateSelf();
            if (fY != fY2) {
                D();
            }
        }
    }

    public final void L(float f) {
        if (this.J != f) {
            float fY = y();
            this.J = f;
            float fY2 = y();
            invalidateSelf();
            if (fY != fY2) {
                D();
            }
        }
    }

    public final void M(ColorStateList colorStateList) {
        this.K = true;
        if (this.I != colorStateList) {
            this.I = colorStateList;
            if (a0()) {
                hl.h(this.H, colorStateList);
            }
            onStateChange(getState());
        }
    }

    public final void N(boolean z) {
        if (this.G != z) {
            boolean zA0 = a0();
            this.G = z;
            boolean zA02 = a0();
            if (zA0 != zA02) {
                if (zA02) {
                    w(this.H);
                } else {
                    c0(this.H);
                }
                invalidateSelf();
                D();
            }
        }
    }

    public final void O(ColorStateList colorStateList) {
        if (this.C != colorStateList) {
            this.C = colorStateList;
            if (this.F0) {
                r(colorStateList);
            }
            onStateChange(getState());
        }
    }

    public final void P(float f) {
        if (this.D != f) {
            this.D = f;
            this.g0.setStrokeWidth(f);
            if (this.F0) {
                s(f);
            }
            invalidateSelf();
        }
    }

    public final void Q(Drawable drawable) {
        Drawable drawable2 = this.M;
        Drawable drawableQ = drawable2 != null ? nv0.Q(drawable2) : null;
        if (drawableQ != drawable) {
            float fZ = z();
            this.M = drawable != null ? drawable.mutate() : null;
            int[] iArr = lf0.a;
            this.N = new RippleDrawable(lf0.c(this.E), this.M, H0);
            float fZ2 = z();
            c0(drawableQ);
            if (b0()) {
                w(this.M);
            }
            invalidateSelf();
            if (fZ != fZ2) {
                D();
            }
        }
    }

    public final void R(float f) {
        if (this.d0 != f) {
            this.d0 = f;
            invalidateSelf();
            if (b0()) {
                D();
            }
        }
    }

    public final void S(float f) {
        if (this.P != f) {
            this.P = f;
            invalidateSelf();
            if (b0()) {
                D();
            }
        }
    }

    public final void T(float f) {
        if (this.c0 != f) {
            this.c0 = f;
            invalidateSelf();
            if (b0()) {
                D();
            }
        }
    }

    public final void U(ColorStateList colorStateList) {
        if (this.O != colorStateList) {
            this.O = colorStateList;
            if (b0()) {
                hl.h(this.M, colorStateList);
            }
            onStateChange(getState());
        }
    }

    public final void V(boolean z) {
        if (this.L != z) {
            boolean zB0 = b0();
            this.L = z;
            boolean zB02 = b0();
            if (zB0 != zB02) {
                if (zB02) {
                    w(this.M);
                } else {
                    c0(this.M);
                }
                invalidateSelf();
                D();
            }
        }
    }

    public final void W(float f) {
        if (this.Z != f) {
            float fY = y();
            this.Z = f;
            float fY2 = y();
            invalidateSelf();
            if (fY != fY2) {
                D();
            }
        }
    }

    public final void X(float f) {
        if (this.Y != f) {
            float fY = y();
            this.Y = f;
            float fY2 = y();
            invalidateSelf();
            if (fY != fY2) {
                D();
            }
        }
    }

    public final void Y(ColorStateList colorStateList) {
        if (this.E != colorStateList) {
            this.E = colorStateList;
            this.A0 = null;
            onStateChange(getState());
        }
    }

    public final boolean Z() {
        return this.S && this.T != null && this.s0;
    }

    @Override // defpackage.u50, defpackage.gn0
    public final void a() {
        D();
        invalidateSelf();
    }

    public final boolean a0() {
        return this.G && this.H != null;
    }

    public final boolean b0() {
        return this.L && this.M != null;
    }

    @Override // defpackage.u50, android.graphics.drawable.Drawable
    public final void draw(Canvas canvas) {
        int i;
        Canvas canvas2;
        int iSaveLayerAlpha;
        int i2;
        Rect bounds = getBounds();
        if (bounds.isEmpty() || (i = this.u0) == 0) {
            return;
        }
        if (i < 255) {
            canvas2 = canvas;
            iSaveLayerAlpha = canvas2.saveLayerAlpha(bounds.left, bounds.top, bounds.right, bounds.bottom, i);
        } else {
            canvas2 = canvas;
            iSaveLayerAlpha = 0;
        }
        boolean z = this.F0;
        Paint paint = this.g0;
        RectF rectF = this.i0;
        if (!z) {
            paint.setColor(this.m0);
            paint.setStyle(Paint.Style.FILL);
            rectF.set(bounds);
            canvas2.drawRoundRect(rectF, A(), A(), paint);
        }
        if (!this.F0) {
            paint.setColor(this.n0);
            paint.setStyle(Paint.Style.FILL);
            ColorFilter colorFilter = this.v0;
            if (colorFilter == null) {
                colorFilter = this.w0;
            }
            paint.setColorFilter(colorFilter);
            rectF.set(bounds);
            canvas2.drawRoundRect(rectF, A(), A(), paint);
        }
        if (this.F0) {
            super.draw(canvas);
        }
        if (this.D > 0.0f && !this.F0) {
            paint.setColor(this.p0);
            paint.setStyle(Paint.Style.STROKE);
            if (!this.F0) {
                ColorFilter colorFilter2 = this.v0;
                if (colorFilter2 == null) {
                    colorFilter2 = this.w0;
                }
                paint.setColorFilter(colorFilter2);
            }
            float f = bounds.left;
            float f2 = this.D / 2.0f;
            rectF.set(f + f2, bounds.top + f2, bounds.right - f2, bounds.bottom - f2);
            float f3 = this.B - (this.D / 2.0f);
            canvas2.drawRoundRect(rectF, f3, f3, paint);
        }
        paint.setColor(this.q0);
        paint.setStyle(Paint.Style.FILL);
        rectF.set(bounds);
        if (this.F0) {
            RectF rectF2 = new RectF(bounds);
            Path path = this.k0;
            t50 t50Var = this.b;
            this.s.a(t50Var.a, t50Var.i, rectF2, this.r, path);
            e(canvas2, paint, path, this.b.a, g());
        } else {
            canvas2.drawRoundRect(rectF, A(), A(), paint);
        }
        if (a0()) {
            x(bounds, rectF);
            float f4 = rectF.left;
            float f5 = rectF.top;
            canvas2.translate(f4, f5);
            this.H.setBounds(0, 0, (int) rectF.width(), (int) rectF.height());
            this.H.draw(canvas2);
            canvas2.translate(-f4, -f5);
        }
        if (Z()) {
            x(bounds, rectF);
            float f6 = rectF.left;
            float f7 = rectF.top;
            canvas2.translate(f6, f7);
            this.T.setBounds(0, 0, (int) rectF.width(), (int) rectF.height());
            this.T.draw(canvas2);
            canvas2.translate(-f6, -f7);
        }
        if (this.D0 && this.F != null) {
            PointF pointF = this.j0;
            pointF.set(0.0f, 0.0f);
            Paint.Align align = Paint.Align.LEFT;
            CharSequence charSequence = this.F;
            hn0 hn0Var = this.l0;
            if (charSequence != null) {
                float fY = y() + this.X + this.a0;
                if (il.a(this) == 0) {
                    pointF.x = bounds.left + fY;
                } else {
                    pointF.x = bounds.right - fY;
                    align = Paint.Align.RIGHT;
                }
                float fCenterY = bounds.centerY();
                TextPaint textPaint = hn0Var.a;
                Paint.FontMetrics fontMetrics = this.h0;
                textPaint.getFontMetrics(fontMetrics);
                pointF.y = fCenterY - ((fontMetrics.descent + fontMetrics.ascent) / 2.0f);
            }
            rectF.setEmpty();
            if (this.F != null) {
                float fY2 = y() + this.X + this.a0;
                float fZ = z() + this.e0 + this.b0;
                if (il.a(this) == 0) {
                    rectF.left = bounds.left + fY2;
                    rectF.right = bounds.right - fZ;
                } else {
                    rectF.left = bounds.left + fZ;
                    rectF.right = bounds.right - fY2;
                }
                rectF.top = bounds.top;
                rectF.bottom = bounds.bottom;
            }
            en0 en0Var = hn0Var.g;
            TextPaint textPaint2 = hn0Var.a;
            if (en0Var != null) {
                textPaint2.drawableState = getState();
                hn0Var.g.e(this.f0, textPaint2, hn0Var.b);
            }
            textPaint2.setTextAlign(align);
            boolean z2 = Math.round(hn0Var.a(this.F.toString())) > Math.round(rectF.width());
            if (z2) {
                int iSave = canvas2.save();
                canvas2.clipRect(rectF);
                i2 = iSave;
            } else {
                i2 = 0;
            }
            CharSequence charSequenceEllipsize = this.F;
            if (z2 && this.C0 != null) {
                charSequenceEllipsize = TextUtils.ellipsize(charSequenceEllipsize, textPaint2, rectF.width(), this.C0);
            }
            canvas.drawText(charSequenceEllipsize, 0, charSequenceEllipsize.length(), pointF.x, pointF.y, textPaint2);
            canvas2 = canvas;
            if (z2) {
                canvas2.restoreToCount(i2);
            }
        }
        if (b0()) {
            rectF.setEmpty();
            if (b0()) {
                float f8 = this.e0 + this.d0;
                if (il.a(this) == 0) {
                    float f9 = bounds.right - f8;
                    rectF.right = f9;
                    rectF.left = f9 - this.P;
                } else {
                    float f10 = bounds.left + f8;
                    rectF.left = f10;
                    rectF.right = f10 + this.P;
                }
                float fExactCenterY = bounds.exactCenterY();
                float f11 = this.P;
                float f12 = fExactCenterY - (f11 / 2.0f);
                rectF.top = f12;
                rectF.bottom = f12 + f11;
            }
            float f13 = rectF.left;
            float f14 = rectF.top;
            canvas2.translate(f13, f14);
            this.M.setBounds(0, 0, (int) rectF.width(), (int) rectF.height());
            int[] iArr = lf0.a;
            this.N.setBounds(this.M.getBounds());
            this.N.jumpToCurrentState();
            this.N.draw(canvas2);
            canvas2.translate(-f13, -f14);
        }
        if (this.u0 < 255) {
            canvas2.restoreToCount(iSaveLayerAlpha);
        }
    }

    @Override // defpackage.u50, android.graphics.drawable.Drawable
    public final int getAlpha() {
        return this.u0;
    }

    @Override // android.graphics.drawable.Drawable
    public final ColorFilter getColorFilter() {
        return this.v0;
    }

    @Override // android.graphics.drawable.Drawable
    public final int getIntrinsicHeight() {
        return (int) this.A;
    }

    @Override // android.graphics.drawable.Drawable
    public final int getIntrinsicWidth() {
        return Math.min(Math.round(z() + this.l0.a(this.F.toString()) + y() + this.X + this.a0 + this.b0 + this.e0), this.E0);
    }

    @Override // defpackage.u50, android.graphics.drawable.Drawable
    public final int getOpacity() {
        return -3;
    }

    @Override // defpackage.u50, android.graphics.drawable.Drawable
    public final void getOutline(Outline outline) {
        Outline outline2;
        if (this.F0) {
            super.getOutline(outline);
            return;
        }
        Rect bounds = getBounds();
        if (bounds.isEmpty()) {
            outline2 = outline;
            outline2.setRoundRect(0, 0, getIntrinsicWidth(), (int) this.A, this.B);
        } else {
            outline.setRoundRect(bounds, this.B);
            outline2 = outline;
        }
        outline2.setAlpha(this.u0 / 255.0f);
    }

    @Override // android.graphics.drawable.Drawable.Callback
    public final void invalidateDrawable(Drawable drawable) {
        Drawable.Callback callback = getCallback();
        if (callback != null) {
            callback.invalidateDrawable(this);
        }
    }

    @Override // defpackage.u50, android.graphics.drawable.Drawable
    public final boolean isStateful() {
        ColorStateList colorStateList;
        if (B(this.y) || B(this.z) || B(this.C)) {
            return true;
        }
        en0 en0Var = this.l0.g;
        if (en0Var == null || (colorStateList = en0Var.j) == null || !colorStateList.isStateful()) {
            return (this.S && this.T != null && this.R) || C(this.H) || C(this.T) || B(this.x0);
        }
        return true;
    }

    @Override // android.graphics.drawable.Drawable
    public final boolean onLayoutDirectionChanged(int i) {
        boolean zOnLayoutDirectionChanged = super.onLayoutDirectionChanged(i);
        if (a0()) {
            zOnLayoutDirectionChanged |= il.b(this.H, i);
        }
        if (Z()) {
            zOnLayoutDirectionChanged |= il.b(this.T, i);
        }
        if (b0()) {
            zOnLayoutDirectionChanged |= il.b(this.M, i);
        }
        if (!zOnLayoutDirectionChanged) {
            return true;
        }
        invalidateSelf();
        return true;
    }

    @Override // android.graphics.drawable.Drawable
    public final boolean onLevelChange(int i) {
        boolean zOnLevelChange = super.onLevelChange(i);
        if (a0()) {
            zOnLevelChange |= this.H.setLevel(i);
        }
        if (Z()) {
            zOnLevelChange |= this.T.setLevel(i);
        }
        if (b0()) {
            zOnLevelChange |= this.M.setLevel(i);
        }
        if (zOnLevelChange) {
            invalidateSelf();
        }
        return zOnLevelChange;
    }

    @Override // defpackage.u50, android.graphics.drawable.Drawable, defpackage.gn0
    public final boolean onStateChange(int[] iArr) {
        if (this.F0) {
            super.onStateChange(iArr);
        }
        return E(iArr, this.z0);
    }

    @Override // android.graphics.drawable.Drawable.Callback
    public final void scheduleDrawable(Drawable drawable, Runnable runnable, long j) {
        Drawable.Callback callback = getCallback();
        if (callback != null) {
            callback.scheduleDrawable(this, runnable, j);
        }
    }

    @Override // defpackage.u50, android.graphics.drawable.Drawable
    public final void setAlpha(int i) {
        if (this.u0 != i) {
            this.u0 = i;
            invalidateSelf();
        }
    }

    @Override // defpackage.u50, android.graphics.drawable.Drawable
    public final void setColorFilter(ColorFilter colorFilter) {
        if (this.v0 != colorFilter) {
            this.v0 = colorFilter;
            invalidateSelf();
        }
    }

    @Override // defpackage.u50, android.graphics.drawable.Drawable
    public final void setTintList(ColorStateList colorStateList) {
        if (this.x0 != colorStateList) {
            this.x0 = colorStateList;
            onStateChange(getState());
        }
    }

    @Override // defpackage.u50, android.graphics.drawable.Drawable
    public final void setTintMode(PorterDuff.Mode mode) {
        if (this.y0 != mode) {
            this.y0 = mode;
            ColorStateList colorStateList = this.x0;
            this.w0 = (colorStateList == null || mode == null) ? null : new PorterDuffColorFilter(colorStateList.getColorForState(getState(), 0), mode);
            invalidateSelf();
        }
    }

    @Override // android.graphics.drawable.Drawable
    public final boolean setVisible(boolean z, boolean z2) {
        boolean visible = super.setVisible(z, z2);
        if (a0()) {
            visible |= this.H.setVisible(z, z2);
        }
        if (Z()) {
            visible |= this.T.setVisible(z, z2);
        }
        if (b0()) {
            visible |= this.M.setVisible(z, z2);
        }
        if (visible) {
            invalidateSelf();
        }
        return visible;
    }

    @Override // android.graphics.drawable.Drawable.Callback
    public final void unscheduleDrawable(Drawable drawable, Runnable runnable) {
        Drawable.Callback callback = getCallback();
        if (callback != null) {
            callback.unscheduleDrawable(this, runnable);
        }
    }

    public final void w(Drawable drawable) {
        if (drawable == null) {
            return;
        }
        drawable.setCallback(this);
        il.b(drawable, il.a(this));
        drawable.setLevel(getLevel());
        drawable.setVisible(isVisible(), false);
        if (drawable == this.M) {
            if (drawable.isStateful()) {
                drawable.setState(this.z0);
            }
            hl.h(drawable, this.O);
            return;
        }
        Drawable drawable2 = this.H;
        if (drawable == drawable2 && this.K) {
            hl.h(drawable2, this.I);
        }
        if (drawable.isStateful()) {
            drawable.setState(getState());
        }
    }

    public final void x(Rect rect, RectF rectF) {
        rectF.setEmpty();
        if (a0() || Z()) {
            float f = this.X + this.Y;
            Drawable drawable = this.s0 ? this.T : this.H;
            float intrinsicWidth = this.J;
            if (intrinsicWidth <= 0.0f && drawable != null) {
                intrinsicWidth = drawable.getIntrinsicWidth();
            }
            if (il.a(this) == 0) {
                float f2 = rect.left + f;
                rectF.left = f2;
                rectF.right = f2 + intrinsicWidth;
            } else {
                float f3 = rect.right - f;
                rectF.right = f3;
                rectF.left = f3 - intrinsicWidth;
            }
            Drawable drawable2 = this.s0 ? this.T : this.H;
            float fCeil = this.J;
            if (fCeil <= 0.0f && drawable2 != null) {
                fCeil = (float) Math.ceil(rp0.t(this.f0, 24));
                if (drawable2.getIntrinsicHeight() <= fCeil) {
                    fCeil = drawable2.getIntrinsicHeight();
                }
            }
            float fExactCenterY = rect.exactCenterY() - (fCeil / 2.0f);
            rectF.top = fExactCenterY;
            rectF.bottom = fExactCenterY + fCeil;
        }
    }

    public final float y() {
        if (!a0() && !Z()) {
            return 0.0f;
        }
        float f = this.Y;
        Drawable drawable = this.s0 ? this.T : this.H;
        float intrinsicWidth = this.J;
        if (intrinsicWidth <= 0.0f && drawable != null) {
            intrinsicWidth = drawable.getIntrinsicWidth();
        }
        return intrinsicWidth + f + this.Z;
    }

    public final float z() {
        if (b0()) {
            return this.c0 + this.P + this.d0;
        }
        return 0.0f;
    }
}
