package defpackage;

import androidx.lifecycle.Lifecycle$Event;
import androidx.lifecycle.Lifecycle$State;

/* JADX INFO: loaded from: classes.dex */
public final class n10 {
    public static Lifecycle$Event a(Lifecycle$State lifecycle$State) {
        oy.j(lifecycle$State, "state");
        int i = m10.a[lifecycle$State.ordinal()];
        if (i == 1) {
            return Lifecycle$Event.ON_DESTROY;
        }
        if (i == 2) {
            return Lifecycle$Event.ON_STOP;
        }
        if (i != 3) {
            return null;
        }
        return Lifecycle$Event.ON_PAUSE;
    }

    public static Lifecycle$Event b(Lifecycle$State lifecycle$State) {
        oy.j(lifecycle$State, "state");
        int i = m10.a[lifecycle$State.ordinal()];
        if (i == 1) {
            return Lifecycle$Event.ON_START;
        }
        if (i == 2) {
            return Lifecycle$Event.ON_RESUME;
        }
        if (i != 5) {
            return null;
        }
        return Lifecycle$Event.ON_CREATE;
    }
}
