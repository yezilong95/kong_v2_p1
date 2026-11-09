package defpackage;

import androidx.lifecycle.Lifecycle$Event;
import androidx.lifecycle.Lifecycle$State;

/* JADX INFO: loaded from: classes.dex */
public final class v10 {
    public Lifecycle$State a;
    public s10 b;

    public final void a(u10 u10Var, Lifecycle$Event lifecycle$Event) {
        Lifecycle$State targetState = lifecycle$Event.getTargetState();
        Lifecycle$State lifecycle$State = this.a;
        oy.j(lifecycle$State, "state1");
        if (targetState != null && targetState.compareTo(lifecycle$State) < 0) {
            lifecycle$State = targetState;
        }
        this.a = lifecycle$State;
        this.b.a(u10Var, lifecycle$Event);
        this.a = targetState;
    }
}
