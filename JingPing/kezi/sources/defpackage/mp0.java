package defpackage;

import android.view.View;
import java.util.ArrayList;
import java.util.HashMap;

/* JADX INFO: loaded from: classes.dex */
public final class mp0 {
    public final View b;
    public final HashMap a = new HashMap();
    public final ArrayList c = new ArrayList();

    public mp0(View view) {
        this.b = view;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof mp0)) {
            return false;
        }
        mp0 mp0Var = (mp0) obj;
        return this.b == mp0Var.b && this.a.equals(mp0Var.a);
    }

    public final int hashCode() {
        return this.a.hashCode() + (this.b.hashCode() * 31);
    }

    public final String toString() {
        String strI = hk.i(("TransitionValues@" + Integer.toHexString(hashCode()) + ":\n") + "    view = " + this.b + "\n", "    values:");
        HashMap map = this.a;
        for (String str : map.keySet()) {
            strI = strI + "    " + str + ": " + map.get(str) + "\n";
        }
        return strI;
    }
}
