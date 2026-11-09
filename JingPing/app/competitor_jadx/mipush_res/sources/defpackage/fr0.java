package defpackage;

import android.os.Bundle;
import androidx.activity.ComponentActivity;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;
import kotlin.Pair;

/* JADX INFO: loaded from: classes.dex */
public final class fr0 implements ir0 {
    public final i6 a;
    public boolean b;
    public Bundle c;
    public final xy0 d;

    public fr0(i6 i6Var, ComponentActivity componentActivity) {
        t40.n(i6Var, "savedStateRegistry");
        this.a = i6Var;
        this.d = z91.N(new qg(componentActivity, 1));
    }

    @Override // defpackage.ir0
    public final Bundle a() {
        Bundle bundleH = j31.h((Pair[]) Arrays.copyOf(new Pair[0], 0));
        Bundle bundle = this.c;
        if (bundle != null) {
            bundleH.putAll(bundle);
        }
        Iterator it = ((gr0) this.d.getValue()).b.entrySet().iterator();
        if (!it.hasNext()) {
            this.b = false;
            return bundleH;
        }
        Map.Entry entry = (Map.Entry) it.next();
        ((ar0) entry.getValue()).getClass();
        throw null;
    }
}
