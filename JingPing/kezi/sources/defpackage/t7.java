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
import org.java_websocket.extensions.ExtensionRequestData;
import org.slf4j.Marker;

/* JADX INFO: loaded from: classes.dex */
public final class t7 extends Drawable implements gn0 {
    public static final int o = R$style.Widget_MaterialComponents_Badge;
    public static final int p = R$attr.badgeStyle;
    public final WeakReference b;
    public final u50 c;
    public final hn0 d;
    public final Rect e;
    public final u7 f;
    public float g;
    public float h;
    public final int i;
    public float j;
    public float k;
    public float l;
    public WeakReference m;
    public WeakReference n;

    public t7(Context context, BadgeState$State badgeState$State) {
        en0 en0Var;
        WeakReference weakReference = new WeakReference(context);
        this.b = weakReference;
        un0.c(context, un0.b, "Theme.MaterialComponents");
        this.e = new Rect();
        hn0 hn0Var = new hn0(this);
        this.d = hn0Var;
        TextPaint textPaint = hn0Var.a;
        textPaint.setTextAlign(Paint.Align.CENTER);
        u7 u7Var = new u7(context, badgeState$State);
        this.f = u7Var;
        boolean zF = f();
        BadgeState$State badgeState$State2 = u7Var.b;
        u50 u50Var = new u50(ki0.a(context, zF ? badgeState$State2.h.intValue() : badgeState$State2.f.intValue(), f() ? badgeState$State2.i.intValue() : badgeState$State2.g.intValue()).a());
        this.c = u50Var;
        h();
        Context context2 = (Context) weakReference.get();
        if (context2 != null && hn0Var.g != (en0Var = new en0(context2, badgeState$State2.e.intValue()))) {
            hn0Var.c(en0Var, context2);
            textPaint.setColor(badgeState$State2.d.intValue());
            invalidateSelf();
            j();
            invalidateSelf();
        }
        int i = badgeState$State2.m;
        if (i != -2) {
            this.i = ((int) Math.pow(10.0d, ((double) i) - 1.0d)) - 1;
        } else {
            this.i = badgeState$State2.n;
        }
        hn0Var.e = true;
        j();
        invalidateSelf();
        hn0Var.e = true;
        h();
        j();
        invalidateSelf();
        textPaint.setAlpha(getAlpha());
        invalidateSelf();
        ColorStateList colorStateListValueOf = ColorStateList.valueOf(badgeState$State2.c.intValue());
        if (u50Var.b.c != colorStateListValueOf) {
            u50Var.m(colorStateListValueOf);
            invalidateSelf();
        }
        textPaint.setColor(badgeState$State2.d.intValue());
        invalidateSelf();
        WeakReference weakReference2 = this.m;
        if (weakReference2 != null && weakReference2.get() != null) {
            View view = (View) this.m.get();
            WeakReference weakReference3 = this.n;
            i(view, weakReference3 != null ? (FrameLayout) weakReference3.get() : null);
        }
        j();
        setVisible(badgeState$State2.u.booleanValue(), false);
    }

    @Override // defpackage.gn0
    public final void a() {
        invalidateSelf();
    }

    public final String b() {
        u7 u7Var = this.f;
        BadgeState$State badgeState$State = u7Var.b;
        String str = badgeState$State.k;
        boolean z = str != null;
        WeakReference weakReference = this.b;
        if (z) {
            int i = badgeState$State.m;
            if (i == -2 || str == null || str.length() <= i) {
                return str;
            }
            Context context = (Context) weakReference.get();
            if (context == null) {
                return ExtensionRequestData.EMPTY_VALUE;
            }
            return String.format(context.getString(R$string.m3_exceed_max_badge_text_suffix), str.substring(0, i - 1), "…");
        }
        if (!g()) {
            return null;
        }
        int i2 = this.i;
        BadgeState$State badgeState$State2 = u7Var.b;
        if (i2 == -2 || e() <= this.i) {
            return NumberFormat.getInstance(badgeState$State2.o).format(e());
        }
        Context context2 = (Context) weakReference.get();
        return context2 == null ? ExtensionRequestData.EMPTY_VALUE : String.format(badgeState$State2.o, context2.getString(R$string.mtrl_exceed_max_badge_number_suffix), Integer.valueOf(this.i), Marker.ANY_NON_NULL_MARKER);
    }

    public final CharSequence c() {
        Context context;
        if (!isVisible()) {
            return null;
        }
        u7 u7Var = this.f;
        BadgeState$State badgeState$State = u7Var.b;
        String str = badgeState$State.k;
        if (str != null) {
            String str2 = badgeState$State.p;
            return str2 != null ? str2 : str;
        }
        boolean zG = g();
        BadgeState$State badgeState$State2 = u7Var.b;
        if (!zG) {
            return badgeState$State2.q;
        }
        if (badgeState$State2.r == 0 || (context = (Context) this.b.get()) == null) {
            return null;
        }
        if (this.i != -2) {
            int iE = e();
            int i = this.i;
            if (iE > i) {
                return context.getString(badgeState$State2.s, Integer.valueOf(i));
            }
        }
        return context.getResources().getQuantityString(badgeState$State2.r, e(), Integer.valueOf(e()));
    }

    public final FrameLayout d() {
        WeakReference weakReference = this.n;
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
        this.c.draw(canvas);
        if (!f() || (strB = b()) == null) {
            return;
        }
        Rect rect = new Rect();
        hn0 hn0Var = this.d;
        hn0Var.a.getTextBounds(strB, 0, strB.length(), rect);
        float fExactCenterY = this.h - rect.exactCenterY();
        canvas.drawText(strB, this.g, rect.bottom <= 0 ? (int) fExactCenterY : Math.round(fExactCenterY), hn0Var.a);
    }

    public final int e() {
        int i = this.f.b.l;
        if (i != -1) {
            return i;
        }
        return 0;
    }

    public final boolean f() {
        return this.f.b.k != null || g();
    }

    public final boolean g() {
        BadgeState$State badgeState$State = this.f.b;
        return badgeState$State.k == null && badgeState$State.l != -1;
    }

    @Override // android.graphics.drawable.Drawable
    public final int getAlpha() {
        return this.f.b.j;
    }

    @Override // android.graphics.drawable.Drawable
    public final int getIntrinsicHeight() {
        return this.e.height();
    }

    @Override // android.graphics.drawable.Drawable
    public final int getIntrinsicWidth() {
        return this.e.width();
    }

    @Override // android.graphics.drawable.Drawable
    public final int getOpacity() {
        return -3;
    }

    public final void h() {
        Context context = (Context) this.b.get();
        if (context == null) {
            return;
        }
        boolean zF = f();
        u7 u7Var = this.f;
        this.c.setShapeAppearanceModel(ki0.a(context, zF ? u7Var.b.h.intValue() : u7Var.b.f.intValue(), f() ? u7Var.b.i.intValue() : u7Var.b.g.intValue()).a());
        invalidateSelf();
    }

    public final void i(View view, FrameLayout frameLayout) {
        this.m = new WeakReference(view);
        this.n = new WeakReference(frameLayout);
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
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void j() {
        /*
            Method dump skipped, instruction units count: 702
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.t7.j():void");
    }

    @Override // android.graphics.drawable.Drawable, defpackage.gn0
    public final boolean onStateChange(int[] iArr) {
        return super.onStateChange(iArr);
    }

    @Override // android.graphics.drawable.Drawable
    public final void setAlpha(int i) {
        u7 u7Var = this.f;
        u7Var.a.j = i;
        u7Var.b.j = i;
        this.d.a.setAlpha(getAlpha());
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public final void setColorFilter(ColorFilter colorFilter) {
    }
}
