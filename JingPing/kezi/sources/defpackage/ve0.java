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
public final class ve0 {
    public static ve0 g;
    public WeakHashMap a;
    public final WeakHashMap b = new WeakHashMap(0);
    public TypedValue c;
    public boolean d;
    public z4 e;
    public static final PorterDuff.Mode f = PorterDuff.Mode.SRC_IN;
    public static final ue0 h = new ue0(6);

    public static synchronized ve0 b() {
        try {
            if (g == null) {
                g = new ve0();
            }
        } catch (Throwable th) {
            throw th;
        }
        return g;
    }

    public static synchronized PorterDuffColorFilter e(int i, PorterDuff.Mode mode) {
        PorterDuffColorFilter porterDuffColorFilter;
        ue0 ue0Var = h;
        ue0Var.getClass();
        int i2 = (31 + i) * 31;
        porterDuffColorFilter = (PorterDuffColorFilter) ue0Var.a(Integer.valueOf(mode.hashCode() + i2));
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
            t30 t30Var = (t30) this.b.get(context);
            drawableNewDrawable = null;
            if (t30Var != null && (weakReference = (WeakReference) t30Var.c(j, null)) != null) {
                Drawable.ConstantState constantState = (Drawable.ConstantState) weakReference.get();
                if (constantState != null) {
                    drawableNewDrawable = constantState.newDrawable(context.getResources());
                } else {
                    int iE = xi.e(t30Var.c, t30Var.e, j);
                    if (iE >= 0) {
                        Object[] objArr = t30Var.d;
                        Object obj = objArr[iE];
                        Object obj2 = t30.f;
                        if (obj != obj2) {
                            objArr[iE] = obj2;
                            t30Var.b = true;
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
                layerDrawableC = z4.c(this, context, R$dimen.abc_star_big);
            } else if (i == R$drawable.abc_ratingbar_indicator_material) {
                layerDrawableC = z4.c(this, context, R$dimen.abc_star_medium);
            } else if (i == R$drawable.abc_ratingbar_small_material) {
                layerDrawableC = z4.c(this, context, R$dimen.abc_star_small);
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
                    t30 t30Var2 = (t30) this.b.get(context);
                    if (t30Var2 == null) {
                        t30Var2 = new t30();
                        this.b.put(context, t30Var2);
                    }
                    t30Var2.d(j, new WeakReference(constantState2));
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
                if (drawableC == null || (!(drawableC instanceof vq0) && !"android.graphics.drawable.VectorDrawable".equals(drawableC.getClass().getName()))) {
                    this.d = false;
                    throw new IllegalStateException("This app has been built with an incorrect configuration. Please configure your build for VectorDrawableCompat.");
                }
            }
            drawableA = a(context, i);
            if (drawableA == null) {
                drawableA = ah.b(context, i);
            }
            if (drawableA != null) {
                drawableA = g(context, i, z, drawableA);
            }
            if (drawableA != null) {
                ll.a(drawableA);
            }
        } catch (Throwable th) {
            throw th;
        }
        return drawableA;
    }

    public final synchronized ColorStateList f(Context context, int i) {
        ColorStateList colorStateList;
        xj0 xj0Var;
        WeakHashMap weakHashMap = this.a;
        ColorStateList colorStateListD = null;
        colorStateList = (weakHashMap == null || (xj0Var = (xj0) weakHashMap.get(context)) == null) ? null : (ColorStateList) xj0Var.b(i, null);
        if (colorStateList == null) {
            z4 z4Var = this.e;
            if (z4Var != null) {
                colorStateListD = z4Var.d(context, i);
            }
            if (colorStateListD != null) {
                if (this.a == null) {
                    this.a = new WeakHashMap();
                }
                xj0 xj0Var2 = (xj0) this.a.get(context);
                if (xj0Var2 == null) {
                    xj0Var2 = new xj0();
                    this.a.put(context, xj0Var2);
                }
                xj0Var2.a(i, colorStateListD);
            }
            colorStateList = colorStateListD;
        }
        return colorStateList;
    }

    /* JADX WARN: Removed duplicated region for block: B:49:0x00ea  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final android.graphics.drawable.Drawable g(android.content.Context r8, int r9, boolean r10, android.graphics.drawable.Drawable r11) {
        /*
            Method dump skipped, instruction units count: 263
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.ve0.g(android.content.Context, int, boolean, android.graphics.drawable.Drawable):android.graphics.drawable.Drawable");
    }
}
