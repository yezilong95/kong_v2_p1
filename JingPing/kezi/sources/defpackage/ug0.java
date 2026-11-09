package defpackage;

import android.os.Bundle;
import androidx.lifecycle.Lifecycle$Event;
import androidx.lifecycle.Lifecycle$State;
import java.util.LinkedHashMap;

/* JADX INFO: loaded from: classes.dex */
public final class ug0 {
    public final vg0 a;
    public final we b;
    public boolean e;
    public Bundle f;
    public boolean g;
    public final yo c = new yo(23);
    public final LinkedHashMap d = new LinkedHashMap();
    public boolean h = true;

    public ug0(vg0 vg0Var, we weVar) {
        this.a = vg0Var;
        this.b = weVar;
    }

    public final void a() {
        vg0 vg0Var = this.a;
        if (((w10) vg0Var.getLifecycle()).c != Lifecycle$State.INITIALIZED) {
            throw new IllegalStateException("Restarter must be created only during owner's initialization stage");
        }
        if (this.e) {
            throw new IllegalStateException("SavedStateRegistry was already attached.");
        }
        this.b.invoke();
        vg0Var.getLifecycle().a(new s10() { // from class: tg0
            @Override // defpackage.s10
            public final void a(u10 u10Var, Lifecycle$Event lifecycle$Event) {
                Lifecycle$Event lifecycle$Event2 = Lifecycle$Event.ON_START;
                ug0 ug0Var = this.b;
                if (lifecycle$Event == lifecycle$Event2) {
                    ug0Var.h = true;
                } else if (lifecycle$Event == Lifecycle$Event.ON_STOP) {
                    ug0Var.h = false;
                }
            }
        });
        this.e = true;
    }
}
