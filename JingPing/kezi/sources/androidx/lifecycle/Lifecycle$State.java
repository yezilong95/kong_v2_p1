package androidx.lifecycle;

import defpackage.jo;
import defpackage.ko;
import defpackage.oy;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes.dex */
public final class Lifecycle$State {
    public static final Lifecycle$State CREATED;
    public static final Lifecycle$State DESTROYED;
    public static final Lifecycle$State INITIALIZED;
    public static final Lifecycle$State RESUMED;
    public static final Lifecycle$State STARTED;
    public static final /* synthetic */ Lifecycle$State[] b;
    public static final /* synthetic */ ko c;

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
        b = lifecycle$StateArr;
        c = new ko(lifecycle$StateArr);
    }

    public static jo getEntries() {
        return c;
    }

    public static Lifecycle$State valueOf(String str) {
        return (Lifecycle$State) Enum.valueOf(Lifecycle$State.class, str);
    }

    public static Lifecycle$State[] values() {
        return (Lifecycle$State[]) b.clone();
    }

    public final boolean isAtLeast(Lifecycle$State lifecycle$State) {
        oy.j(lifecycle$State, "state");
        return compareTo(lifecycle$State) >= 0;
    }
}
