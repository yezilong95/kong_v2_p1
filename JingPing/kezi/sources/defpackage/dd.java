package defpackage;

import android.util.Property;

/* JADX INFO: loaded from: classes.dex */
public final class dd extends Property {
    public static final dd a = new dd(fd.class, "circularReveal");

    @Override // android.util.Property
    public final Object get(Object obj) {
        return ((gd) obj).getRevealInfo();
    }

    @Override // android.util.Property
    public final void set(Object obj, Object obj2) {
        ((gd) obj).setRevealInfo((fd) obj2);
    }
}
