package androidx.lifecycle;

import defpackage.gu;
import defpackage.hu;
import defpackage.p80;
import defpackage.q80;
import defpackage.r80;
import defpackage.t40;
import io.nats.client.support.ApiConstants;
import kotlin.NoWhenBranchMatchedException;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes.dex */
public final class Lifecycle$Event {
    private static final /* synthetic */ gu $ENTRIES;
    private static final /* synthetic */ Lifecycle$Event[] $VALUES;
    public static final q80 Companion;
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
        $ENTRIES = new hu(lifecycle$EventArr);
        Companion = new q80();
    }

    public static final Lifecycle$Event downFrom(Lifecycle$State lifecycle$State) {
        Companion.getClass();
        return q80.a(lifecycle$State);
    }

    public static final Lifecycle$Event downTo(Lifecycle$State lifecycle$State) {
        Companion.getClass();
        t40.n(lifecycle$State, ApiConstants.STATE);
        int i = p80.a[lifecycle$State.ordinal()];
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

    public static gu getEntries() {
        return $ENTRIES;
    }

    public static final Lifecycle$Event upFrom(Lifecycle$State lifecycle$State) {
        Companion.getClass();
        return q80.b(lifecycle$State);
    }

    public static final Lifecycle$Event upTo(Lifecycle$State lifecycle$State) {
        Companion.getClass();
        t40.n(lifecycle$State, ApiConstants.STATE);
        int i = p80.a[lifecycle$State.ordinal()];
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
        switch (r80.a[ordinal()]) {
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
