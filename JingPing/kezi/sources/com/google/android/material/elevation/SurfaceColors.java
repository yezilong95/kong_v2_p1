package com.google.android.material.elevation;

import android.content.Context;
import com.google.android.material.R$attr;
import com.google.android.material.R$dimen;
import defpackage.bn;
import defpackage.k9;

/* JADX INFO: loaded from: classes.dex */
public enum SurfaceColors {
    SURFACE_0(R$dimen.m3_sys_elevation_level0),
    SURFACE_1(R$dimen.m3_sys_elevation_level1),
    SURFACE_2(R$dimen.m3_sys_elevation_level2),
    SURFACE_3(R$dimen.m3_sys_elevation_level3),
    SURFACE_4(R$dimen.m3_sys_elevation_level4),
    SURFACE_5(R$dimen.m3_sys_elevation_level5);

    public final int b;

    SurfaceColors(int i) {
        this.b = i;
    }

    public static int getColorForElevation(Context context, float f) {
        return new bn(context).a(k9.p(context, R$attr.colorSurface, 0), f);
    }

    public int getColor(Context context) {
        return getColorForElevation(context, context.getResources().getDimension(this.b));
    }
}
