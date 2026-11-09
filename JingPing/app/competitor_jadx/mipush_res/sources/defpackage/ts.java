package defpackage;

import android.content.Context;
import android.graphics.Color;
import com.google.android.material.R$attr;

/* JADX INFO: loaded from: classes.dex */
public final class ts {
    public static final int f = (int) Math.round(5.1000000000000005d);
    public final boolean a;
    public final int b;
    public final int c;
    public final int d;
    public final float e;

    public ts(Context context) {
        boolean zT = s90.T(context, R$attr.elevationOverlayEnabled, false);
        int iO = fa0.O(context, R$attr.elevationOverlayColor, 0);
        int iO2 = fa0.O(context, R$attr.elevationOverlayAccentColor, 0);
        int iO3 = fa0.O(context, R$attr.colorSurface, 0);
        float f2 = context.getResources().getDisplayMetrics().density;
        this.a = zT;
        this.b = iO;
        this.c = iO2;
        this.d = iO3;
        this.e = f2;
    }

    public final int a(int i, float f2) {
        int i2;
        float fMin = (this.e <= 0.0f || f2 <= 0.0f) ? 0.0f : Math.min(((((float) Math.log1p(f2 / r0)) * 4.5f) + 2.0f) / 100.0f, 1.0f);
        int iAlpha = Color.alpha(i);
        int iK0 = fa0.k0(dg.e(i, 255), fMin, this.b);
        if (fMin > 0.0f && (i2 = this.c) != 0) {
            iK0 = dg.c(dg.e(i2, f), iK0);
        }
        return dg.e(iK0, iAlpha);
    }

    public final int b(int i, float f2) {
        return (this.a && dg.e(i, 255) == this.d) ? a(i, f2) : i;
    }
}
