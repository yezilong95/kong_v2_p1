package androidx.constraintlayout.solver.state;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes.dex */
public final class State$Helper {
    public static final State$Helper ALIGN_HORIZONTALLY;
    public static final State$Helper ALIGN_VERTICALLY;
    public static final State$Helper BARRIER;
    public static final State$Helper FLOW;
    public static final State$Helper HORIZONTAL_CHAIN;
    public static final State$Helper LAYER;
    public static final State$Helper VERTICAL_CHAIN;
    public static final /* synthetic */ State$Helper[] b;

    static {
        State$Helper state$Helper = new State$Helper("HORIZONTAL_CHAIN", 0);
        HORIZONTAL_CHAIN = state$Helper;
        State$Helper state$Helper2 = new State$Helper("VERTICAL_CHAIN", 1);
        VERTICAL_CHAIN = state$Helper2;
        State$Helper state$Helper3 = new State$Helper("ALIGN_HORIZONTALLY", 2);
        ALIGN_HORIZONTALLY = state$Helper3;
        State$Helper state$Helper4 = new State$Helper("ALIGN_VERTICALLY", 3);
        ALIGN_VERTICALLY = state$Helper4;
        State$Helper state$Helper5 = new State$Helper("BARRIER", 4);
        BARRIER = state$Helper5;
        State$Helper state$Helper6 = new State$Helper("LAYER", 5);
        LAYER = state$Helper6;
        State$Helper state$Helper7 = new State$Helper("FLOW", 6);
        FLOW = state$Helper7;
        b = new State$Helper[]{state$Helper, state$Helper2, state$Helper3, state$Helper4, state$Helper5, state$Helper6, state$Helper7};
    }

    public static State$Helper valueOf(String str) {
        return (State$Helper) Enum.valueOf(State$Helper.class, str);
    }

    public static State$Helper[] values() {
        return (State$Helper[]) b.clone();
    }
}
