package defpackage;

import android.util.Property;

/* JADX INFO: loaded from: classes.dex */
public final class xe extends Property {
    public static final xe a = new xe(ze.class, "circularReveal");

    @Override // android.util.Property
    public final Object get(Object obj) {
        return ((af) obj).getRevealInfo();
    }

    @Override // android.util.Property
    public final void set(Object obj, Object obj2) {
        ((af) obj).setRevealInfo((ze) obj2);
    }
}
