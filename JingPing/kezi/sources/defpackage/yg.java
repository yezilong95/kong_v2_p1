package defpackage;

import androidx.activity.ComponentActivity;
import java.util.concurrent.CopyOnWriteArraySet;

/* JADX INFO: loaded from: classes.dex */
public final class yg {
    public final CopyOnWriteArraySet a = new CopyOnWriteArraySet();
    public volatile ComponentActivity b;

    public final void addOnContextAvailableListener(ra0 ra0Var) {
        oy.j(ra0Var, "listener");
        if (this.b != null) {
            ra0Var.a();
        }
        this.a.add(ra0Var);
    }

    public final void removeOnContextAvailableListener(ra0 ra0Var) {
        oy.j(ra0Var, "listener");
        this.a.remove(ra0Var);
    }
}
