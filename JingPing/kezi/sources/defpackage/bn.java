package defpackage;

import android.content.Context;
import android.graphics.Color;
import com.google.android.material.R$attr;

/* JADX INFO: loaded from: classes.dex */
public final class bn {
    public static final int f = (int) Math.round(5.1000000000000005d);
    public final boolean a;
    public final int b;
    public final int c;
    public final int d;
    public final float e;

    public bn(Context context) {
        boolean zK = nv0.K(context, R$attr.elevationOverlayEnabled, false);
        int iP = k9.p(context, R$attr.elevationOverlayColor, 0);
        int iP2 = k9.p(context, R$attr.elevationOverlayAccentColor, 0);
        int iP3 = k9.p(context, R$attr.colorSurface, 0);
        float f2 = context.getResources().getDisplayMetrics().density;
        this.a = zK;
        this.b = iP;
        this.c = iP2;
        this.d = iP3;
        this.e = f2;
    }

    public final int a(int i, float f2) {
        int i2;
        float fMin = (this.e <= 0.0f || f2 <= 0.0f) ? 0.0f : Math.min(((((float) Math.log1p(f2 / r0)) * 4.5f) + 2.0f) / 100.0f, 1.0f);
        int iAlpha = Color.alpha(i);
        int iX = k9.x(je.e(i, 255), fMin, this.b);
        if (fMin > 0.0f && (i2 = this.c) != 0) {
            iX = je.c(je.e(i2, f), iX);
        }
        return je.e(iX, iAlpha);
    }

    public final int b(int i, float f2) {
        return (this.a && je.e(i, 255) == this.d) ? a(i, f2) : i;
    }
}
