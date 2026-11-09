package defpackage;

import android.graphics.drawable.Drawable;
import android.util.Property;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public final class gl extends Property {
    public static final gl a;

    static {
        gl glVar = new gl(Integer.class, "drawableAlphaCompat");
        new WeakHashMap();
        a = glVar;
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
