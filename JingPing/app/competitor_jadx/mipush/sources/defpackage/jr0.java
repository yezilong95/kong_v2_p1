package defpackage;

import android.os.Bundle;
import androidx.lifecycle.Lifecycle$State;
import java.util.LinkedHashMap;

/* JADX INFO: loaded from: classes.dex */
public final class jr0 {
    public final kr0 a;
    public final qg b;
    public boolean e;
    public Bundle f;
    public boolean g;
    public final er0 c = new er0();
    public final LinkedHashMap d = new LinkedHashMap();
    public boolean h = true;

    public jr0(kr0 kr0Var, qg qgVar) {
        this.a = kr0Var;
        this.b = qgVar;
    }

    public final void a() {
        kr0 kr0Var = this.a;
        if (((z80) kr0Var.getLifecycle()).c != Lifecycle$State.INITIALIZED) {
            throw new IllegalStateException("Restarter must be created only during owner's initialization stage");
        }
        if (this.e) {
            throw new IllegalStateException("SavedStateRegistry was already attached.");
        }
        this.b.invoke();
        kr0Var.getLifecycle().a(new um0(1, this));
        this.e = true;
    }
}
