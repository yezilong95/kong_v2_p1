package defpackage;

import android.graphics.drawable.ColorStateListDrawable;
import android.graphics.drawable.Drawable;
import android.telephony.CellInfo;
import android.telephony.CellInfoNr;

/* JADX INFO: loaded from: classes.dex */
public abstract /* synthetic */ class a2 {
    public static /* bridge */ /* synthetic */ ColorStateListDrawable d(Drawable drawable) {
        return (ColorStateListDrawable) drawable;
    }

    public static /* bridge */ /* synthetic */ CellInfoNr f(CellInfo cellInfo) {
        return (CellInfoNr) cellInfo;
    }

    public static /* bridge */ /* synthetic */ boolean s(Drawable drawable) {
        return drawable instanceof ColorStateListDrawable;
    }

    public static /* bridge */ /* synthetic */ boolean u(CellInfo cellInfo) {
        return cellInfo instanceof CellInfoNr;
    }
}
