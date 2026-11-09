package defpackage;

import android.graphics.drawable.Drawable;
import android.util.Property;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public final class hp extends Property {
    public static final hp a;

    static {
        hp hpVar = new hp(Integer.class, "drawableAlphaCompat");
        new WeakHashMap();
        a = hpVar;
    }

    @Override // android.util.Property
    public final Object get(Object obj) {
        return Integer.valueOf(((Drawable) obj).getAlpha());
    }

    @Override // android.util.Property
    public final void set(Object obj, Object obj2) {
        ((Drawable) obj).setAlpha(((Integer) obj2).intValue());
    }
}
