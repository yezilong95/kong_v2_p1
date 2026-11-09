package defpackage;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.view.PointerIcon;

/* JADX INFO: loaded from: classes.dex */
public abstract class yk0 {
    public static PointerIcon a(Bitmap bitmap, float f, float f2) {
        return PointerIcon.create(bitmap, f, f2);
    }

    public static PointerIcon b(Context context, int i) {
        return PointerIcon.getSystemIcon(context, i);
    }

    public static PointerIcon c(Resources resources, int i) {
        return PointerIcon.load(resources, i);
    }
}
