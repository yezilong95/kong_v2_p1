package defpackage;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.text.TextPaint;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import com.google.android.material.R$attr;
import com.google.android.material.R$string;
import com.google.android.material.R$style;
import com.google.android.material.badge.BadgeState$State;
import java.lang.ref.WeakReference;
import java.text.NumberFormat;
import org.slf4j.Marker;

/* JADX INFO: loaded from: classes.dex */
public final class n9 extends Drawable implements uz0 {
    public static final int R = R$style.Widget_MaterialComponents_Badge;
    public static final int S = R$attr.badgeStyle;
    public final WeakReference E;
    public final ld0 F;
    public final vz0 G;
    public final Rect H;
    public final o9 I;
    public float J;
    public float K;
    public final int L;
    public float M;
    public float N;
    public float O;
    public WeakReference P;
    public WeakReference Q;

    public n9(Context context, BadgeState$State badgeState$State) {
        sz0 sz0Var;
        WeakReference weakReference = new WeakReference(context);
        this.E = weakReference;
        i01.c(context, i01.b, "Theme.MaterialComponents");
        this.H = new Rect();
        vz0 vz0Var = new vz0(this);
        this.G = vz0Var;
        TextPaint textPaint = vz0Var.a;
        textPaint.setTextAlign(Paint.Align.CENTER);
        o9 o9Var = new o9(context, badgeState$State);
        this.I = o9Var;
        boolean zF = f();
        BadgeState$State badgeState$State2 = o9Var.b;
        ld0 ld0Var = new ld0(zu0.a(context, zF ? badgeState$State2.K.intValue() : badgeState$State2.I.intValue(), f() ? badgeState$State2.L.intValue() : badgeState$State2.J.intValue()).a());
        this.F = ld0Var;
        h();
        Context context2 = (Context) weakReference.get();
        if (context2 != null && vz0Var.g != (sz0Var = new sz0(context2, badgeState$State2.H.intValue()))) {
            vz0Var.c(sz0Var, context2);
            textPaint.setColor(badgeState$State2.G.intValue());
            invalidateSelf();
            j();
            invalidateSelf();
        }
        int i = badgeState$State2.P;
        if (i != -2) {
            this.L = ((int) Math.pow(10.0d, ((double) i) - 1.0d)) - 1;
        } else {
            this.L = badgeState$State2.Q;
        }
        vz0Var.e = true;
        j();
        invalidateSelf();
        vz0Var.e = true;
        h();
        j();
        invalidateSelf();
        textPaint.setAlpha(getAlpha());
        invalidateSelf();
        ColorStateList colorStateListValueOf = ColorStateList.valueOf(badgeState$State2.F.intValue());
        if (ld0Var.E.c != colorStateListValueOf) {
            ld0Var.m(colorStateListValueOf);
            invalidateSelf();
        }
        textPaint.setColor(badgeState$State2.G.intValue());
        invalidateSelf();
        WeakReference weakReference2 = this.P;
        if (weakReference2 != null && weakReference2.get() != null) {
            View view = (View) this.P.get();
            WeakReference weakReference3 = this.Q;
            i(view, weakReference3 != null ? (FrameLayout) weakReference3.get() : null);
        }
        j();
        setVisible(badgeState$State2.X.booleanValue(), false);
    }

    @Override // defpackage.uz0
    public final void a() {
        invalidateSelf();
    }

    public final String b() {
        o9 o9Var = this.I;
        BadgeState$State badgeState$State = o9Var.b;
        String str = badgeState$State.N;
        boolean z = str != null;
        WeakReference weakReference = this.E;
        if (z) {
            int i = badgeState$State.P;
            if (i == -2 || str == null || str.length() <= i) {
                return str;
            }
            Context context = (Context) weakReference.get();
            if (context == null) {
                return "";
            }
            return String.format(context.getString(R$string.m3_exceed_max_badge_text_suffix), str.substring(0, i - 1), "…");
        }
        if (!g()) {
            return null;
        }
        int i2 = this.L;
        BadgeState$State badgeState$State2 = o9Var.b;
        if (i2 == -2 || e() <= this.L) {
            return NumberFormat.getInstance(badgeState$State2.R).format(e());
        }
        Context context2 = (Context) weakReference.get();
        return context2 == null ? "" : String.format(badgeState$State2.R, context2.getString(R$string.mtrl_exceed_max_badge_number_suffix), Integer.valueOf(this.L), Marker.ANY_NON_NULL_MARKER);
    }

    public final CharSequence c() {
        Context context;
        if (!isVisible()) {
            return null;
        }
        o9 o9Var = this.I;
        BadgeState$State badgeState$State = o9Var.b;
        String str = badgeState$State.N;
        if (str != null) {
            String str2 = badgeState$State.S;
            return str2 != null ? str2 : str;
        }
        boolean zG = g();
        BadgeState$State badgeState$State2 = o9Var.b;
        if (!zG) {
            return badgeState$State2.T;
        }
        if (badgeState$State2.U == 0 || (context = (Context) this.E.get()) == null) {
            return null;
        }
        if (this.L != -2) {
            int iE = e();
            int i = this.L;
            if (iE > i) {
                return context.getString(badgeState$State2.V, Integer.valueOf(i));
            }
        }
        return context.getResources().getQuantityString(badgeState$State2.U, e(), Integer.valueOf(e()));
    }

    public final FrameLayout d() {
        WeakReference weakReference = this.Q;
        if (weakReference != null) {
            return (FrameLayout) weakReference.get();
        }
        return null;
    }

    @Override // android.graphics.drawable.Drawable
    public final void draw(Canvas canvas) {
        String strB;
        if (getBounds().isEmpty() || getAlpha() == 0 || !isVisible()) {
            return;
        }
        this.F.draw(canvas);
        if (!f() || (strB = b()) == null) {
            return;
        }
        Rect rect = new Rect();
        vz0 vz0Var = this.G;
        vz0Var.a.getTextBounds(strB, 0, strB.length(), rect);
        float fExactCenterY = this.K - rect.exactCenterY();
        canvas.drawText(strB, this.J, rect.bottom <= 0 ? (int) fExactCenterY : Math.round(fExactCenterY), vz0Var.a);
    }

    public final int e() {
        int i = this.I.b.O;
        if (i != -1) {
            return i;
        }
        return 0;
    }

    public final boolean f() {
        return this.I.b.N != null || g();
    }

    public final boolean g() {
        BadgeState$State badgeState$State = this.I.b;
        return badgeState$State.N == null && badgeState$State.O != -1;
    }

    @Override // android.graphics.drawable.Drawable
    public final int getAlpha() {
        return this.I.b.M;
    }

    @Override // android.graphics.drawable.Drawable
    public final int getIntrinsicHeight() {
        return this.H.height();
    }

    @Override // android.graphics.drawable.Drawable
    public final int getIntrinsicWidth() {
        return this.H.width();
    }

    @Override // android.graphics.drawable.Drawable
    public final int getOpacity() {
        return -3;
    }

    public final void h() {
        Context context = (Context) this.E.get();
        if (context == null) {
            return;
        }
        boolean zF = f();
        o9 o9Var = this.I;
        this.F.setShapeAppearanceModel(zu0.a(context, zF ? o9Var.b.K.intValue() : o9Var.b.I.intValue(), f() ? o9Var.b.L.intValue() : o9Var.b.J.intValue()).a());
        invalidateSelf();
    }

    public final void i(View view, FrameLayout frameLayout) {
        this.P = new WeakReference(view);
        this.Q = new WeakReference(frameLayout);
        ViewGroup viewGroup = (ViewGroup) view.getParent();
        viewGroup.setClipChildren(false);
        viewGroup.setClipToPadding(false);
        j();
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public final boolean isStateful() {
        return false;
    }

    /* JADX WARN: Removed duplicated region for block: B:101:0x0238  */
    /* JADX WARN: Removed duplicated region for block: B:102:0x0250  */
    /* JADX WARN: Removed duplicated region for block: B:105:0x0255  */
    /* JADX WARN: Removed duplicated region for block: B:108:0x0262  */
    /* JADX WARN: Removed duplicated region for block: B:111:0x026f  */
    /* JADX WARN: Removed duplicated region for block: B:114:0x027c  */
    /* JADX WARN: Removed duplicated region for block: B:97:0x0217  */
    /* JADX WARN: Removed duplicated region for block: B:98:0x022f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void j() {
        /*
            Method dump skipped, instruction units count: 702
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.n9.j():void");
    }

    @Override // android.graphics.drawable.Drawable, defpackage.uz0
    public final boolean onStateChange(int[] iArr) {
        return super.onStateChange(iArr);
    }

    @Override // android.graphics.drawable.Drawable
    public final void setAlpha(int i) {
        o9 o9Var = this.I;
        o9Var.a.M = i;
        o9Var.b.M = i;
        this.G.a.setAlpha(getAlpha());
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public final void setColorFilter(ColorFilter colorFilter) {
    }
}
