package defpackage;

import android.util.Property;

/* JADX INFO: loaded from: classes.dex */
public final class ye extends Property {
    public static final ye a = new ye(Integer.class, "circularRevealScrimColor");

    @Override // android.util.Property
    public final Object get(Object obj) {
        return Integer.valueOf(((af) obj).getCircularRevealScrimColor());
    }

    @Override // android.util.Property
    public final void set(Object obj, Object obj2) {
        ((af) obj).setCircularRevealScrimColor(((Integer) obj2).intValue());
    }
}
