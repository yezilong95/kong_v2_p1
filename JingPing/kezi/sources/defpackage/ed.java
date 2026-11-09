package defpackage;

import android.util.Property;

/* JADX INFO: loaded from: classes.dex */
public final class ed extends Property {
    public static final ed a = new ed(Integer.class, "circularRevealScrimColor");

    @Override // android.util.Property
    public final Object get(Object obj) {
        return Integer.valueOf(((gd) obj).getCircularRevealScrimColor());
    }

    @Override // android.util.Property
    public final void set(Object obj, Object obj2) {
        ((gd) obj).setCircularRevealScrimColor(((Integer) obj2).intValue());
    }
}
