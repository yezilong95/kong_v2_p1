package defpackage;

import android.os.Bundle;
import androidx.activity.ComponentActivity;
import java.util.Arrays;
import java.util.Map;
import kotlin.Pair;

/* JADX INFO: loaded from: classes.dex */
public final class pg0 implements sg0 {
    public final e5 a;
    public boolean b;
    public Bundle c;
    public final im0 d;

    public pg0(e5 e5Var, ComponentActivity componentActivity) {
        oy.j(e5Var, "savedStateRegistry");
        this.a = e5Var;
        this.d = oy.G(new we(componentActivity, 1));
    }

    @Override // defpackage.sg0
    public final Bundle a() {
        Bundle bundleG = rp0.g((Pair[]) Arrays.copyOf(new Pair[0], 0));
        Bundle bundle = this.c;
        if (bundle != null) {
            bundleG.putAll(bundle);
        }
        for (Map.Entry entry : ((qg0) this.d.getValue()).b.entrySet()) {
            String str = (String) entry.getKey();
            Bundle bundleA = ((xe) ((mg0) entry.getValue()).a.f).a();
            if (!bundleA.isEmpty()) {
                oy.j(str, "key");
                bundleG.putBundle(str, bundleA);
            }
        }
        this.b = false;
        return bundleG;
    }

    public final void b() {
        if (this.b) {
            return;
        }
        Bundle bundleD = this.a.d("androidx.lifecycle.internal.SavedStateHandlesProvider");
        Bundle bundleG = rp0.g((Pair[]) Arrays.copyOf(new Pair[0], 0));
        Bundle bundle = this.c;
        if (bundle != null) {
            bundleG.putAll(bundle);
        }
        if (bundleD != null) {
            bundleG.putAll(bundleD);
        }
        this.c = bundleG;
        this.b = true;
    }
}
