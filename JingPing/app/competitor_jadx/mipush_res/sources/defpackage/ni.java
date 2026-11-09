package defpackage;

import androidx.activity.ComponentActivity;
import java.util.concurrent.CopyOnWriteArraySet;

/* JADX INFO: loaded from: classes.dex */
public final class ni {
    public final CopyOnWriteArraySet a = new CopyOnWriteArraySet();
    public volatile ComponentActivity b;

    public final void addOnContextAvailableListener(fj0 fj0Var) {
        t40.n(fj0Var, "listener");
        if (this.b != null) {
            fj0Var.a();
        }
        this.a.add(fj0Var);
    }

    public final void removeOnContextAvailableListener(fj0 fj0Var) {
        t40.n(fj0Var, "listener");
        this.a.remove(fj0Var);
    }
}
