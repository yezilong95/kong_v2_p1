package defpackage;

import java.util.LinkedHashMap;

/* JADX INFO: loaded from: classes.dex */
public abstract class bi {
    public final LinkedHashMap a = new LinkedHashMap();

    public final boolean equals(Object obj) {
        return (obj instanceof bi) && oy.c(this.a, ((bi) obj).a);
    }

    public final int hashCode() {
        return this.a.hashCode();
    }

    public final String toString() {
        return "CreationExtras(extras=" + this.a + ')';
    }
}
