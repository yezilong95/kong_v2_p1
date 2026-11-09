package androidx.constraintlayout.solver.state;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes.dex */
public final class State$Chain {
    public static final State$Chain PACKED;
    public static final State$Chain SPREAD;
    public static final State$Chain SPREAD_INSIDE;
    public static final /* synthetic */ State$Chain[] b;

    static {
        State$Chain state$Chain = new State$Chain("SPREAD", 0);
        SPREAD = state$Chain;
        State$Chain state$Chain2 = new State$Chain("SPREAD_INSIDE", 1);
        SPREAD_INSIDE = state$Chain2;
        State$Chain state$Chain3 = new State$Chain("PACKED", 2);
        PACKED = state$Chain3;
        b = new State$Chain[]{state$Chain, state$Chain2, state$Chain3};
    }

    public static State$Chain valueOf(String str) {
        return (State$Chain) Enum.valueOf(State$Chain.class, str);
    }

    public static State$Chain[] values() {
        return (State$Chain[]) b.clone();
    }
}
