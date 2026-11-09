package defpackage;

import androidx.lifecycle.Lifecycle$Event;
import androidx.lifecycle.Lifecycle$State;

/* JADX INFO: loaded from: classes.dex */
public final class y80 {
    public Lifecycle$State a;
    public v80 b;

    public final void a(x80 x80Var, Lifecycle$Event lifecycle$Event) {
        Lifecycle$State targetState = lifecycle$Event.getTargetState();
        Lifecycle$State lifecycle$State = this.a;
        t40.n(lifecycle$State, "state1");
        if (targetState != null && targetState.compareTo(lifecycle$State) < 0) {
            lifecycle$State = targetState;
        }
        this.a = lifecycle$State;
        this.b.j(x80Var, lifecycle$Event);
        this.a = targetState;
    }
}
