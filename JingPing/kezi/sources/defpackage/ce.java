package defpackage;

import java.util.Collection;

/* JADX INFO: loaded from: classes.dex */
public abstract class ce extends be {
    public static int Z(Iterable iterable) {
        oy.j(iterable, "<this>");
        if (iterable instanceof Collection) {
            return ((Collection) iterable).size();
        }
        return 10;
    }
}
