package defpackage;

import com.google.gson.internal.LinkedTreeMap;

/* JADX INFO: loaded from: classes.dex */
public final class y50 extends w50 {
    public final LinkedTreeMap E = new LinkedTreeMap();

    public final boolean equals(Object obj) {
        if (obj != this) {
            return (obj instanceof y50) && ((y50) obj).E.equals(this.E);
        }
        return true;
    }

    public final int hashCode() {
        return this.E.hashCode();
    }
}
