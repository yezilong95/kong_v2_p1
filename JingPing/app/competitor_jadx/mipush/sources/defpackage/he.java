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

/* JADX INFO: loaded from: classes.dex */
public final class he extends ld0 implements Drawable.Callback, uz0 {
    public static final int[] k1 = {R.attr.state_enabled};
    public static final ShapeDrawable l1 = new ShapeDrawable(new OvalShape());
    public rf0 A0;
    public float B0;
    public float C0;
    public float D0;
    public float E0;
    public float F0;
    public float G0;
    public float H0;
    public float I0;
    public final Context J0;
    public final Paint K0;
    public final Paint.FontMetrics L0;
    public final RectF M0;
    public final PointF N0;
    public final Path O0;
    public final vz0 P0;
    public int Q0;
    public int R0;
    public int S0;
    public int T0;
    public int U0;
    public int V0;
    public boolean W0;
    public int X0;
    public int Y0;
    public ColorFilter Z0;
    public PorterDuffColorFilter a1;
    public ColorStateList b1;
    public ColorStateList c0;
    public PorterDuff.Mode c1;
    public ColorStateList d0;
    public int[] d1;
    public float e0;
    public ColorStateList e1;
    public float f0;
    public WeakReference f1;
    public ColorStateList g0;
    public TextUtils.TruncateAt g1;
    public float h0;
    public boolean h1;
    public ColorStateList i0;
    public int i1;
    public CharSequence j0;
    public boolean j1;
    public boolean k0;
    public Drawable l0;
    public ColorStateList m0;
    public float n0;
    public boolean o0;
    public boolean p0;
    public Drawable q0;
    public RippleDrawable r0;
    public ColorStateList s0;
    public float t0;
    public SpannableStringBuilder u0;
    public boolean v0;
    public boolean w0;
    public Drawable x0;
    public ColorStateList y0;
    public rf0 z0;

    public he(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i, Chip.e0);
        this.f0 = -1.0f;
        this.K0 = new Paint(1);
        this.L0 = new Paint.FontMetrics();
        this.M0 = new RectF();
        this.N0 = new PointF();
        this.O0 = new Path();
        this.Y0 = 255;
        this.c1 = PorterDuff.Mode.SRC_IN;
        this.f1 = new WeakReference(null);
        k(context);
        this.J0 = context;
        vz0 vz0Var = new vz0(this);
        this.P0 = vz0Var;
        this.j0 = "";
        vz0Var.a.density = context.getResources().getDisplayMetrics().density;
        int[] iArr = k1;
        setState(iArr);
        if (!Arrays.equals(this.d1, iArr)) {
            this.d1 = iArr;
            if (b0()) {
                E(getState(), iArr);
            }
        }
        this.h1 = true;
        int[] iArr2 = gp0.a;
        l1.setTint(-1);
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
        return this.j1 ? i() : this.f0;
    }

    public final void D() {
        ge geVar = (ge) this.f1.get();
        if (geVar != null) {
            Chip chip = (Chip) geVar;
            chip.c(chip.U);
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
        ColorStateList colorStateList2 = this.c0;
        int iC = c(colorStateList2 != null ? colorStateList2.getColorForState(iArr, this.Q0) : 0);
        if (this.Q0 != iC) {
            this.Q0 = iC;
            zOnStateChange = true;
        }
        ColorStateList colorStateList3 = this.d0;
        int iC2 = c(colorStateList3 != null ? colorStateList3.getColorForState(iArr, this.R0) : 0);
        if (this.R0 != iC2) {
            this.R0 = iC2;
            zOnStateChange = true;
        }
        int iC3 = dg.c(iC2, iC);
        if ((this.S0 != iC3) | (this.E.c == null)) {
            this.S0 = iC3;
            m(ColorStateList.valueOf(iC3));
            zOnStateChange = true;
        }
        ColorStateList colorStateList4 = this.g0;
        int colorForState = colorStateList4 != null ? colorStateList4.getColorForState(iArr, this.T0) : 0;
        if (this.T0 != colorForState) {
            this.T0 = colorForState;
            zOnStateChange = true;
        }
        int colorForState2 = (this.e1 == null || !gp0.d(iArr)) ? 0 : this.e1.getColorForState(iArr, this.U0);
        if (this.U0 != colorForState2) {
            this.U0 = colorForState2;
        }
        sz0 sz0Var = this.P0.g;
        int colorForState3 = (sz0Var == null || (colorStateList = sz0Var.j) == null) ? 0 : colorStateList.getColorForState(iArr, this.V0);
        if (this.V0 != colorForState3) {
            this.V0 = colorForState3;
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
                } else if (this.v0) {
                    z = true;
                }
            }
            z = false;
        }
        if (this.W0 == z || this.x0 == null) {
            z2 = false;
        } else {
            float fY = y();
            this.W0 = z;
            if (fY != y()) {
                zOnStateChange = true;
                z2 = true;
            } else {
                zOnStateChange = true;
                z2 = false;
            }
        }
        ColorStateList colorStateList5 = this.b1;
        int colorForState4 = colorStateList5 != null ? colorStateList5.getColorForState(iArr, this.X0) : 0;
        if (this.X0 != colorForState4) {
            this.X0 = colorForState4;
            ColorStateList colorStateList6 = this.b1;
            PorterDuff.Mode mode = this.c1;
            this.a1 = (colorStateList6 == null || mode == null) ? null : new PorterDuffColorFilter(colorStateList6.getColorForState(getState(), 0), mode);
        } else {
            state = zOnStateChange;
        }
        if (C(this.l0)) {
            state |= this.l0.setState(iArr);
        }
        if (C(this.x0)) {
            state |= this.x0.setState(iArr);
        }
        if (C(this.q0)) {
            int[] iArr3 = new int[iArr.length + iArr2.length];
            System.arraycopy(iArr, 0, iArr3, 0, iArr.length);
            System.arraycopy(iArr2, 0, iArr3, iArr.length, iArr2.length);
            state |= this.q0.setState(iArr3);
        }
        int[] iArr4 = gp0.a;
        if (C(this.r0)) {
            state |= this.r0.setState(iArr2);
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
        if (this.v0 != z) {
            this.v0 = z;
            float fY = y();
            if (!z && this.W0) {
                this.W0 = false;
            }
            float fY2 = y();
            invalidateSelf();
            if (fY != fY2) {
                D();
            }
        }
    }

    public final void G(Drawable drawable) {
        if (this.x0 != drawable) {
            float fY = y();
            this.x0 = drawable;
            float fY2 = y();
            c0(this.x0);
            w(this.x0);
            invalidateSelf();
            if (fY != fY2) {
                D();
            }
        }
    }

    public final void H(ColorStateList colorStateList) {
        Drawable drawable;
        if (this.y0 != colorStateList) {
            this.y0 = colorStateList;
            if (this.w0 && (drawable = this.x0) != null && this.v0) {
                ip.h(drawable, colorStateList);
            }
            onStateChange(getState());
        }
    }

    public final void I(boolean z) {
        if (this.w0 != z) {
            boolean Z = Z();
            this.w0 = z;
            boolean Z2 = Z();
            if (Z != Z2) {
                if (Z2) {
                    w(this.x0);
                } else {
                    c0(this.x0);
                }
                invalidateSelf();
                D();
            }
        }
    }

    public final void J(float f) {
        if (this.f0 != f) {
            this.f0 = f;
            xu0 xu0VarG = this.E.a.g();
            xu0VarG.c(f);
            setShapeAppearanceModel(xu0VarG.a());
        }
    }

    public final void K(Drawable drawable) {
        Drawable drawable2 = this.l0;
        Drawable drawableV0 = drawable2 != null ? z91.v0(drawable2) : null;
        if (drawableV0 != drawable) {
            float fY = y();
            this.l0 = drawable != null ? drawable.mutate() : null;
            float fY2 = y();
            c0(drawableV0);
            if (a0()) {
                w(this.l0);
            }
            invalidateSelf();
            if (fY != fY2) {
                D();
            }
        }
    }

    public final void L(float f) {
        if (this.n0 != f) {
            float fY = y();
            this.n0 = f;
            float fY2 = y();
            invalidateSelf();
            if (fY != fY2) {
                D();
            }
        }
    }

    public final void M(ColorStateList colorStateList) {
        this.o0 = true;
        if (this.m0 != colorStateList) {
            this.m0 = colorStateList;
            if (a0()) {
                ip.h(this.l0, colorStateList);
            }
            onStateChange(getState());
        }
    }

    public final void N(boolean z) {
        if (this.k0 != z) {
            boolean zA0 = a0();
            this.k0 = z;
            boolean zA02 = a0();
            if (zA0 != zA02) {
                if (zA02) {
                    w(this.l0);
                } else {
                    c0(this.l0);
                }
                invalidateSelf();
                D();
            }
        }
    }

    public final void O(ColorStateList colorStateList) {
        if (this.g0 != colorStateList) {
            this.g0 = colorStateList;
            if (this.j1) {
                r(colorStateList);
            }
            onStateChange(getState());
        }
    }

    public final void P(float f) {
        if (this.h0 != f) {
            this.h0 = f;
            this.K0.setStrokeWidth(f);
            if (this.j1) {
                s(f);
            }
            invalidateSelf();
        }
    }

    public final void Q(Drawable drawable) {
        Drawable drawable2 = this.q0;
        Drawable drawableV0 = drawable2 != null ? z91.v0(drawable2) : null;
        if (drawableV0 != drawable) {
            float fZ = z();
            this.q0 = drawable != null ? drawable.mutate() : null;
            int[] iArr = gp0.a;
            this.r0 = new RippleDrawable(gp0.c(this.i0), this.q0, l1);
            float fZ2 = z();
            c0(drawableV0);
            if (b0()) {
                w(this.q0);
            }
            invalidateSelf();
            if (fZ != fZ2) {
                D();
            }
        }
    }

    public final void R(float f) {
        if (this.H0 != f) {
            this.H0 = f;
            invalidateSelf();
            if (b0()) {
                D();
            }
        }
    }

    public final void S(float f) {
        if (this.t0 != f) {
            this.t0 = f;
            invalidateSelf();
            if (b0()) {
                D();
            }
        }
    }

    public final void T(float f) {
        if (this.G0 != f) {
            this.G0 = f;
            invalidateSelf();
            if (b0()) {
                D();
            }
        }
    }

    public final void U(ColorStateList colorStateList) {
        if (this.s0 != colorStateList) {
            this.s0 = colorStateList;
            if (b0()) {
                ip.h(this.q0, colorStateList);
            }
            onStateChange(getState());
        }
    }

    public final void V(boolean z) {
        if (this.p0 != z) {
            boolean zB0 = b0();
            this.p0 = z;
            boolean zB02 = b0();
            if (zB0 != zB02) {
                if (zB02) {
                    w(this.q0);
                } else {
                    c0(this.q0);
                }
                invalidateSelf();
                D();
            }
        }
    }

    public final void W(float f) {
        if (this.D0 != f) {
            float fY = y();
            this.D0 = f;
            float fY2 = y();
            invalidateSelf();
            if (fY != fY2) {
                D();
            }
        }
    }

    public final void X(float f) {
        if (this.C0 != f) {
            float fY = y();
            this.C0 = f;
            float fY2 = y();
            invalidateSelf();
            if (fY != fY2) {
                D();
            }
        }
    }

    public final void Y(ColorStateList colorStateList) {
        if (this.i0 != colorStateList) {
            this.i0 = colorStateList;
            this.e1 = null;
            onStateChange(getState());
        }
    }

    public final boolean Z() {
        return this.w0 && this.x0 != null && this.W0;
    }

    @Override // defpackage.ld0, defpackage.uz0
    public final void a() {
        D();
        invalidateSelf();
    }

    public final boolean a0() {
        return this.k0 && this.l0 != null;
    }

    public final boolean b0() {
        return this.p0 && this.q0 != null;
    }

    @Override // defpackage.ld0, android.graphics.drawable.Drawable
    public final void draw(Canvas canvas) {
        int i;
        Canvas canvas2;
        int iSaveLayerAlpha;
        int i2;
        Rect bounds = getBounds();
        if (bounds.isEmpty() || (i = this.Y0) == 0) {
            return;
        }
        if (i < 255) {
            canvas2 = canvas;
            iSaveLayerAlpha = canvas2.saveLayerAlpha(bounds.left, bounds.top, bounds.right, bounds.bottom, i);
        } else {
            canvas2 = canvas;
            iSaveLayerAlpha = 0;
        }
        boolean z = this.j1;
        Paint paint = this.K0;
        RectF rectF = this.M0;
        if (!z) {
            paint.setColor(this.Q0);
            paint.setStyle(Paint.Style.FILL);
            rectF.set(bounds);
            canvas2.drawRoundRect(rectF, A(), A(), paint);
        }
        if (!this.j1) {
            paint.setColor(this.R0);
            paint.setStyle(Paint.Style.FILL);
            ColorFilter colorFilter = this.Z0;
            if (colorFilter == null) {
                colorFilter = this.a1;
            }
            paint.setColorFilter(colorFilter);
            rectF.set(bounds);
            canvas2.drawRoundRect(rectF, A(), A(), paint);
        }
        if (this.j1) {
            super.draw(canvas);
        }
        if (this.h0 > 0.0f && !this.j1) {
            paint.setColor(this.T0);
            paint.setStyle(Paint.Style.STROKE);
            if (!this.j1) {
                ColorFilter colorFilter2 = this.Z0;
                if (colorFilter2 == null) {
                    colorFilter2 = this.a1;
                }
                paint.setColorFilter(colorFilter2);
            }
            float f = bounds.left;
            float f2 = this.h0 / 2.0f;
            rectF.set(f + f2, bounds.top + f2, bounds.right - f2, bounds.bottom - f2);
            float f3 = this.f0 - (this.h0 / 2.0f);
            canvas2.drawRoundRect(rectF, f3, f3, paint);
        }
        paint.setColor(this.U0);
        paint.setStyle(Paint.Style.FILL);
        rectF.set(bounds);
        if (this.j1) {
            RectF rectF2 = new RectF(bounds);
            Path path = this.O0;
            kd0 kd0Var = this.E;
            this.V.a(kd0Var.a, kd0Var.i, rectF2, this.U, path);
            e(canvas2, paint, path, this.E.a, g());
        } else {
            canvas2.drawRoundRect(rectF, A(), A(), paint);
        }
        if (a0()) {
            x(bounds, rectF);
            float f4 = rectF.left;
            float f5 = rectF.top;
            canvas2.translate(f4, f5);
            this.l0.setBounds(0, 0, (int) rectF.width(), (int) rectF.height());
            this.l0.draw(canvas2);
            canvas2.translate(-f4, -f5);
        }
        if (Z()) {
            x(bounds, rectF);
            float f6 = rectF.left;
            float f7 = rectF.top;
            canvas2.translate(f6, f7);
            this.x0.setBounds(0, 0, (int) rectF.width(), (int) rectF.height());
            this.x0.draw(canvas2);
            canvas2.translate(-f6, -f7);
        }
        if (this.h1 && this.j0 != null) {
            PointF pointF = this.N0;
            pointF.set(0.0f, 0.0f);
            Paint.Align align = Paint.Align.LEFT;
            CharSequence charSequence = this.j0;
            vz0 vz0Var = this.P0;
            if (charSequence != null) {
                float fY = y() + this.B0 + this.E0;
                if (jp.a(this) == 0) {
                    pointF.x = bounds.left + fY;
                } else {
                    pointF.x = bounds.right - fY;
                    align = Paint.Align.RIGHT;
                }
                float fCenterY = bounds.centerY();
                TextPaint textPaint = vz0Var.a;
                Paint.FontMetrics fontMetrics = this.L0;
                textPaint.getFontMetrics(fontMetrics);
                pointF.y = fCenterY - ((fontMetrics.descent + fontMetrics.ascent) / 2.0f);
            }
            rectF.setEmpty();
            if (this.j0 != null) {
                float fY2 = y() + this.B0 + this.E0;
                float fZ = z() + this.I0 + this.F0;
                if (jp.a(this) == 0) {
                    rectF.left = bounds.left + fY2;
                    rectF.right = bounds.right - fZ;
                } else {
                    rectF.left = bounds.left + fZ;
                    rectF.right = bounds.right - fY2;
                }
                rectF.top = bounds.top;
                rectF.bottom = bounds.bottom;
            }
            sz0 sz0Var = vz0Var.g;
            TextPaint textPaint2 = vz0Var.a;
            if (sz0Var != null) {
                textPaint2.drawableState = getState();
                vz0Var.g.e(this.J0, textPaint2, vz0Var.b);
            }
            textPaint2.setTextAlign(align);
            boolean z2 = Math.round(vz0Var.a(this.j0.toString())) > Math.round(rectF.width());
            if (z2) {
                int iSave = canvas2.save();
                canvas2.clipRect(rectF);
                i2 = iSave;
            } else {
                i2 = 0;
            }
            CharSequence charSequenceEllipsize = this.j0;
            if (z2 && this.g1 != null) {
                charSequenceEllipsize = TextUtils.ellipsize(charSequenceEllipsize, textPaint2, rectF.width(), this.g1);
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
                float f8 = this.I0 + this.H0;
                if (jp.a(this) == 0) {
                    float f9 = bounds.right - f8;
                    rectF.right = f9;
                    rectF.left = f9 - this.t0;
                } else {
                    float f10 = bounds.left + f8;
                    rectF.left = f10;
                    rectF.right = f10 + this.t0;
                }
                float fExactCenterY = bounds.exactCenterY();
                float f11 = this.t0;
                float f12 = fExactCenterY - (f11 / 2.0f);
                rectF.top = f12;
                rectF.bottom = f12 + f11;
            }
            float f13 = rectF.left;
            float f14 = rectF.top;
            canvas2.translate(f13, f14);
            this.q0.setBounds(0, 0, (int) rectF.width(), (int) rectF.height());
            int[] iArr = gp0.a;
            this.r0.setBounds(this.q0.getBounds());
            this.r0.jumpToCurrentState();
            this.r0.draw(canvas2);
            canvas2.translate(-f13, -f14);
        }
        if (this.Y0 < 255) {
            canvas2.restoreToCount(iSaveLayerAlpha);
        }
    }

    @Override // defpackage.ld0, android.graphics.drawable.Drawable
    public final int getAlpha() {
        return this.Y0;
    }

    @Override // android.graphics.drawable.Drawable
    public final ColorFilter getColorFilter() {
        return this.Z0;
    }

    @Override // android.graphics.drawable.Drawable
    public final int getIntrinsicHeight() {
        return (int) this.e0;
    }

    @Override // android.graphics.drawable.Drawable
    public final int getIntrinsicWidth() {
        return Math.min(Math.round(z() + this.P0.a(this.j0.toString()) + y() + this.B0 + this.E0 + this.F0 + this.I0), this.i1);
    }

    @Override // defpackage.ld0, android.graphics.drawable.Drawable
    public final int getOpacity() {
        return -3;
    }

    @Override // defpackage.ld0, android.graphics.drawable.Drawable
    public final void getOutline(Outline outline) {
        Outline outline2;
        if (this.j1) {
            super.getOutline(outline);
            return;
        }
        Rect bounds = getBounds();
        if (bounds.isEmpty()) {
            outline2 = outline;
            outline2.setRoundRect(0, 0, getIntrinsicWidth(), (int) this.e0, this.f0);
        } else {
            outline.setRoundRect(bounds, this.f0);
            outline2 = outline;
        }
        outline2.setAlpha(this.Y0 / 255.0f);
    }

    @Override // android.graphics.drawable.Drawable.Callback
    public final void invalidateDrawable(Drawable drawable) {
        Drawable.Callback callback = getCallback();
        if (callback != null) {
            callback.invalidateDrawable(this);
        }
    }

    @Override // defpackage.ld0, android.graphics.drawable.Drawable
    public final boolean isStateful() {
        ColorStateList colorStateList;
        if (B(this.c0) || B(this.d0) || B(this.g0)) {
            return true;
        }
        sz0 sz0Var = this.P0.g;
        if (sz0Var == null || (colorStateList = sz0Var.j) == null || !colorStateList.isStateful()) {
            return (this.w0 && this.x0 != null && this.v0) || C(this.l0) || C(this.x0) || B(this.b1);
        }
        return true;
    }

    @Override // android.graphics.drawable.Drawable
    public final boolean onLayoutDirectionChanged(int i) {
        boolean zOnLayoutDirectionChanged = super.onLayoutDirectionChanged(i);
        if (a0()) {
            zOnLayoutDirectionChanged |= jp.b(this.l0, i);
        }
        if (Z()) {
            zOnLayoutDirectionChanged |= jp.b(this.x0, i);
        }
        if (b0()) {
            zOnLayoutDirectionChanged |= jp.b(this.q0, i);
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
            zOnLevelChange |= this.l0.setLevel(i);
        }
        if (Z()) {
            zOnLevelChange |= this.x0.setLevel(i);
        }
        if (b0()) {
            zOnLevelChange |= this.q0.setLevel(i);
        }
        if (zOnLevelChange) {
            invalidateSelf();
        }
        return zOnLevelChange;
    }

    @Override // defpackage.ld0, android.graphics.drawable.Drawable, defpackage.uz0
    public final boolean onStateChange(int[] iArr) {
        if (this.j1) {
            super.onStateChange(iArr);
        }
        return E(iArr, this.d1);
    }

    @Override // android.graphics.drawable.Drawable.Callback
    public final void scheduleDrawable(Drawable drawable, Runnable runnable, long j) {
        Drawable.Callback callback = getCallback();
        if (callback != null) {
            callback.scheduleDrawable(this, runnable, j);
        }
    }

    @Override // defpackage.ld0, android.graphics.drawable.Drawable
    public final void setAlpha(int i) {
        if (this.Y0 != i) {
            this.Y0 = i;
            invalidateSelf();
        }
    }

    @Override // defpackage.ld0, android.graphics.drawable.Drawable
    public final void setColorFilter(ColorFilter colorFilter) {
        if (this.Z0 != colorFilter) {
            this.Z0 = colorFilter;
            invalidateSelf();
        }
    }

    @Override // defpackage.ld0, android.graphics.drawable.Drawable
    public final void setTintList(ColorStateList colorStateList) {
        if (this.b1 != colorStateList) {
            this.b1 = colorStateList;
            onStateChange(getState());
        }
    }

    @Override // defpackage.ld0, android.graphics.drawable.Drawable
    public final void setTintMode(PorterDuff.Mode mode) {
        if (this.c1 != mode) {
            this.c1 = mode;
            ColorStateList colorStateList = this.b1;
            this.a1 = (colorStateList == null || mode == null) ? null : new PorterDuffColorFilter(colorStateList.getColorForState(getState(), 0), mode);
            invalidateSelf();
        }
    }

    @Override // android.graphics.drawable.Drawable
    public final boolean setVisible(boolean z, boolean z2) {
        boolean visible = super.setVisible(z, z2);
        if (a0()) {
            visible |= this.l0.setVisible(z, z2);
        }
        if (Z()) {
            visible |= this.x0.setVisible(z, z2);
        }
        if (b0()) {
            visible |= this.q0.setVisible(z, z2);
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
        jp.b(drawable, jp.a(this));
        drawable.setLevel(getLevel());
        drawable.setVisible(isVisible(), false);
        if (drawable == this.q0) {
            if (drawable.isStateful()) {
                drawable.setState(this.d1);
            }
            ip.h(drawable, this.s0);
            return;
        }
        Drawable drawable2 = this.l0;
        if (drawable == drawable2 && this.o0) {
            ip.h(drawable2, this.m0);
        }
        if (drawable.isStateful()) {
            drawable.setState(getState());
        }
    }

    public final void x(Rect rect, RectF rectF) {
        rectF.setEmpty();
        if (a0() || Z()) {
            float f = this.B0 + this.C0;
            Drawable drawable = this.W0 ? this.x0 : this.l0;
            float intrinsicWidth = this.n0;
            if (intrinsicWidth <= 0.0f && drawable != null) {
                intrinsicWidth = drawable.getIntrinsicWidth();
            }
            if (jp.a(this) == 0) {
                float f2 = rect.left + f;
                rectF.left = f2;
                rectF.right = f2 + intrinsicWidth;
            } else {
                float f3 = rect.right - f;
                rectF.right = f3;
                rectF.left = f3 - intrinsicWidth;
            }
            Drawable drawable2 = this.W0 ? this.x0 : this.l0;
            float fCeil = this.n0;
            if (fCeil <= 0.0f && drawable2 != null) {
                fCeil = (float) Math.ceil(fa0.C(this.J0, 24));
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
        float f = this.C0;
        Drawable drawable = this.W0 ? this.x0 : this.l0;
        float intrinsicWidth = this.n0;
        if (intrinsicWidth <= 0.0f && drawable != null) {
            intrinsicWidth = drawable.getIntrinsicWidth();
        }
        return intrinsicWidth + f + this.D0;
    }

    public final float z() {
        if (b0()) {
            return this.G0 + this.t0 + this.H0;
        }
        return 0.0f;
    }
}
