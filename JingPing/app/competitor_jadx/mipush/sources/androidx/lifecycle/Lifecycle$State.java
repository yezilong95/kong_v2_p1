package androidx.lifecycle;

import defpackage.gu;
import defpackage.hu;
import defpackage.t40;
import io.nats.client.support.ApiConstants;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes.dex */
public final class Lifecycle$State {
    public static final Lifecycle$State CREATED;
    public static final Lifecycle$State DESTROYED;
    public static final /* synthetic */ Lifecycle$State[] E;
    public static final /* synthetic */ hu F;
    public static final Lifecycle$State INITIALIZED;
    public static final Lifecycle$State RESUMED;
    public static final Lifecycle$State STARTED;

    static {
        Lifecycle$State lifecycle$State = new Lifecycle$State("DESTROYED", 0);
        DESTROYED = lifecycle$State;
        Lifecycle$State lifecycle$State2 = new Lifecycle$State("INITIALIZED", 1);
        INITIALIZED = lifecycle$State2;
        Lifecycle$State lifecycle$State3 = new Lifecycle$State("CREATED", 2);
        CREATED = lifecycle$State3;
        Lifecycle$State lifecycle$State4 = new Lifecycle$State("STARTED", 3);
        STARTED = lifecycle$State4;
        Lifecycle$State lifecycle$State5 = new Lifecycle$State("RESUMED", 4);
        RESUMED = lifecycle$State5;
        Lifecycle$State[] lifecycle$StateArr = {lifecycle$State, lifecycle$State2, lifecycle$State3, lifecycle$State4, lifecycle$State5};
        E = lifecycle$StateArr;
        F = new hu(lifecycle$StateArr);
    }

    public static gu getEntries() {
        return F;
    }

    public static Lifecycle$State valueOf(String str) {
        return (Lifecycle$State) Enum.valueOf(Lifecycle$State.class, str);
    }

    public static Lifecycle$State[] values() {
        return (Lifecycle$State[]) E.clone();
    }

    public final boolean isAtLeast(Lifecycle$State lifecycle$State) {
        t40.n(lifecycle$State, ApiConstants.STATE);
        return compareTo(lifecycle$State) >= 0;
    }
}
