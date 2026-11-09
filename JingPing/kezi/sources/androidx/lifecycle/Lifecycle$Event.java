package androidx.lifecycle;

import defpackage.jo;
import defpackage.ko;
import defpackage.m10;
import defpackage.n10;
import defpackage.o10;
import defpackage.oy;
import kotlin.NoWhenBranchMatchedException;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes.dex */
public final class Lifecycle$Event {
    private static final /* synthetic */ jo $ENTRIES;
    private static final /* synthetic */ Lifecycle$Event[] $VALUES;
    public static final n10 Companion;
    public static final Lifecycle$Event ON_ANY;
    public static final Lifecycle$Event ON_CREATE;
    public static final Lifecycle$Event ON_DESTROY;
    public static final Lifecycle$Event ON_PAUSE;
    public static final Lifecycle$Event ON_RESUME;
    public static final Lifecycle$Event ON_START;
    public static final Lifecycle$Event ON_STOP;

    static {
        Lifecycle$Event lifecycle$Event = new Lifecycle$Event("ON_CREATE", 0);
        ON_CREATE = lifecycle$Event;
        Lifecycle$Event lifecycle$Event2 = new Lifecycle$Event("ON_START", 1);
        ON_START = lifecycle$Event2;
        Lifecycle$Event lifecycle$Event3 = new Lifecycle$Event("ON_RESUME", 2);
        ON_RESUME = lifecycle$Event3;
        Lifecycle$Event lifecycle$Event4 = new Lifecycle$Event("ON_PAUSE", 3);
        ON_PAUSE = lifecycle$Event4;
        Lifecycle$Event lifecycle$Event5 = new Lifecycle$Event("ON_STOP", 4);
        ON_STOP = lifecycle$Event5;
        Lifecycle$Event lifecycle$Event6 = new Lifecycle$Event("ON_DESTROY", 5);
        ON_DESTROY = lifecycle$Event6;
        Lifecycle$Event lifecycle$Event7 = new Lifecycle$Event("ON_ANY", 6);
        ON_ANY = lifecycle$Event7;
        Lifecycle$Event[] lifecycle$EventArr = {lifecycle$Event, lifecycle$Event2, lifecycle$Event3, lifecycle$Event4, lifecycle$Event5, lifecycle$Event6, lifecycle$Event7};
        $VALUES = lifecycle$EventArr;
        $ENTRIES = new ko(lifecycle$EventArr);
        Companion = new n10();
    }

    public static final Lifecycle$Event downFrom(Lifecycle$State lifecycle$State) {
        Companion.getClass();
        return n10.a(lifecycle$State);
    }

    public static final Lifecycle$Event downTo(Lifecycle$State lifecycle$State) {
        Companion.getClass();
        oy.j(lifecycle$State, "state");
        int i = m10.a[lifecycle$State.ordinal()];
        if (i == 1) {
            return ON_STOP;
        }
        if (i == 2) {
            return ON_PAUSE;
        }
        if (i != 4) {
            return null;
        }
        return ON_DESTROY;
    }

    public static jo getEntries() {
        return $ENTRIES;
    }

    public static final Lifecycle$Event upFrom(Lifecycle$State lifecycle$State) {
        Companion.getClass();
        return n10.b(lifecycle$State);
    }

    public static final Lifecycle$Event upTo(Lifecycle$State lifecycle$State) {
        Companion.getClass();
        oy.j(lifecycle$State, "state");
        int i = m10.a[lifecycle$State.ordinal()];
        if (i == 1) {
            return ON_CREATE;
        }
        if (i == 2) {
            return ON_START;
        }
        if (i != 3) {
            return null;
        }
        return ON_RESUME;
    }

    public static Lifecycle$Event valueOf(String str) {
        return (Lifecycle$Event) Enum.valueOf(Lifecycle$Event.class, str);
    }

    public static Lifecycle$Event[] values() {
        return (Lifecycle$Event[]) $VALUES.clone();
    }

    public final Lifecycle$State getTargetState() {
        switch (o10.a[ordinal()]) {
            case 1:
            case 2:
                return Lifecycle$State.CREATED;
            case 3:
            case 4:
                return Lifecycle$State.STARTED;
            case 5:
                return Lifecycle$State.RESUMED;
            case 6:
                return Lifecycle$State.DESTROYED;
            case 7:
                throw new IllegalArgumentException(this + " has no target state");
            default:
                throw new NoWhenBranchMatchedException();
        }
    }
}
