package defpackage;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.util.TypedValue;
import androidx.appcompat.R$dimen;
import androidx.appcompat.R$drawable;
import java.lang.ref.WeakReference;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public final class uo0 {
    public static uo0 g;
    public WeakHashMap a;
    public final WeakHashMap b = new WeakHashMap(0);
    public TypedValue c;
    public boolean d;
    public e6 e;
    public static final PorterDuff.Mode f = PorterDuff.Mode.SRC_IN;
    public static final to0 h = new to0(6);

    public static synchronized uo0 b() {
        try {
            if (g == null) {
                g = new uo0();
            }
        } catch (Throwable th) {
            throw th;
        }
        return g;
    }

    public static synchronized PorterDuffColorFilter e(int i, PorterDuff.Mode mode) {
        PorterDuffColorFilter porterDuffColorFilter;
        to0 to0Var = h;
        to0Var.getClass();
        int i2 = (31 + i) * 31;
        porterDuffColorFilter = (PorterDuffColorFilter) to0Var.a(Integer.valueOf(mode.hashCode() + i2));
        if (porterDuffColorFilter == null) {
            porterDuffColorFilter = new PorterDuffColorFilter(i, mode);
        }
        return porterDuffColorFilter;
    }

    public final Drawable a(Context context, int i) {
        Drawable drawableNewDrawable;
        WeakReference weakReference;
        if (this.c == null) {
            this.c = new TypedValue();
        }
        TypedValue typedValue = this.c;
        context.getResources().getValue(i, typedValue, true);
        long j = (((long) typedValue.assetCookie) << 32) | ((long) typedValue.data);
        synchronized (this) {
            cb0 cb0Var = (cb0) this.b.get(context);
            drawableNewDrawable = null;
            if (cb0Var != null && (weakReference = (WeakReference) cb0Var.c(j, null)) != null) {
                Drawable.ConstantState constantState = (Drawable.ConstantState) weakReference.get();
                if (constantState != null) {
                    drawableNewDrawable = constantState.newDrawable(context.getResources());
                } else {
                    int iH = z91.h(cb0Var.F, cb0Var.H, j);
                    if (iH >= 0) {
                        Object[] objArr = cb0Var.G;
                        Object obj = objArr[iH];
                        Object obj2 = cb0.I;
                        if (obj != obj2) {
                            objArr[iH] = obj2;
                            cb0Var.E = true;
                        }
                    }
                }
            }
        }
        if (drawableNewDrawable != null) {
            return drawableNewDrawable;
        }
        LayerDrawable layerDrawableC = null;
        if (this.e != null) {
            if (i == R$drawable.abc_cab_background_top_material) {
                layerDrawableC = new LayerDrawable(new Drawable[]{c(context, R$drawable.abc_cab_background_internal_bg), c(context, R$drawable.abc_cab_background_top_mtrl_alpha)});
            } else if (i == R$drawable.abc_ratingbar_material) {
                layerDrawableC = e6.c(this, context, R$dimen.abc_star_big);
            } else if (i == R$drawable.abc_ratingbar_indicator_material) {
                layerDrawableC = e6.c(this, context, R$dimen.abc_star_medium);
            } else if (i == R$drawable.abc_ratingbar_small_material) {
                layerDrawableC = e6.c(this, context, R$dimen.abc_star_small);
            }
        }
        if (layerDrawableC == null) {
            return layerDrawableC;
        }
        layerDrawableC.setChangingConfigurations(typedValue.changingConfigurations);
        synchronized (this) {
            try {
                Drawable.ConstantState constantState2 = layerDrawableC.getConstantState();
                if (constantState2 != null) {
                    cb0 cb0Var2 = (cb0) this.b.get(context);
                    if (cb0Var2 == null) {
                        cb0Var2 = new cb0();
                        this.b.put(context, cb0Var2);
                    }
                    cb0Var2.d(j, new WeakReference(constantState2));
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        return layerDrawableC;
    }

    public final synchronized Drawable c(Context context, int i) {
        return d(context, i, false);
    }

    public final synchronized Drawable d(Context context, int i, boolean z) {
        Drawable drawableA;
        try {
            if (!this.d) {
                this.d = true;
                Drawable drawableC = c(context, androidx.appcompat.resources.R$drawable.abc_vector_test);
                if (drawableC == null || (!(drawableC instanceof a41) && !"android.graphics.drawable.VectorDrawable".equals(drawableC.getClass().getName()))) {
                    this.d = false;
                    throw new IllegalStateException("This app has been built with an incorrect configuration. Please configure your build for VectorDrawableCompat.");
                }
            }
            drawableA = a(context, i);
            if (drawableA == null) {
                drawableA = pi.b(context, i);
            }
            if (drawableA != null) {
                drawableA = g(context, i, z, drawableA);
            }
            if (drawableA != null) {
                op.a(drawableA);
            }
        } catch (Throwable th) {
            throw th;
        }
        return drawableA;
    }

    public final synchronized ColorStateList f(Context context, int i) {
        ColorStateList colorStateList;
        lw0 lw0Var;
        WeakHashMap weakHashMap = this.a;
        ColorStateList colorStateListD = null;
        colorStateList = (weakHashMap == null || (lw0Var = (lw0) weakHashMap.get(context)) == null) ? null : (ColorStateList) lw0Var.b(i, null);
        if (colorStateList == null) {
            e6 e6Var = this.e;
            if (e6Var != null) {
                colorStateListD = e6Var.d(context, i);
            }
            if (colorStateListD != null) {
                if (this.a == null) {
                    this.a = new WeakHashMap();
                }
                lw0 lw0Var2 = (lw0) this.a.get(context);
                if (lw0Var2 == null) {
                    lw0Var2 = new lw0();
                    this.a.put(context, lw0Var2);
                }
                lw0Var2.a(i, colorStateListD);
            }
            colorStateList = colorStateListD;
        }
        return colorStateList;
    }

    /* JADX WARN: Removed duplicated region for block: B:49:0x00ea  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final android.graphics.drawable.Drawable g(android.content.Context r8, int r9, boolean r10, android.graphics.drawable.Drawable r11) {
        /*
            Method dump skipped, instruction units count: 263
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.uo0.g(android.content.Context, int, boolean, android.graphics.drawable.Drawable):android.graphics.drawable.Drawable");
    }
}
