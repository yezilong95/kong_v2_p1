package defpackage;

import android.view.View;
import java.util.ArrayList;
import java.util.HashMap;

/* JADX INFO: loaded from: classes.dex */
public final class a21 {
    public final View b;
    public final HashMap a = new HashMap();
    public final ArrayList c = new ArrayList();

    public a21(View view) {
        this.b = view;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof a21)) {
            return false;
        }
        a21 a21Var = (a21) obj;
        return this.b == a21Var.b && this.a.equals(a21Var.a);
    }

    public final int hashCode() {
        return this.a.hashCode() + (this.b.hashCode() * 31);
    }

    public final String toString() {
        StringBuilder sbQ = zn.q("TransitionValues@" + Integer.toHexString(hashCode()) + ":\n", "    view = ");
        sbQ.append(this.b);
        sbQ.append("\n");
        String strM = zn.m(sbQ.toString(), "    values:");
        HashMap map = this.a;
        for (String str : map.keySet()) {
            strM = strM + "    " + str + ": " + map.get(str) + "\n";
        }
        return strM;
    }
}
