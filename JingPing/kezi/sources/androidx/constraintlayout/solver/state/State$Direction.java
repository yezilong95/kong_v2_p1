package androidx.constraintlayout.solver.state;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes.dex */
public final class State$Direction {
    public static final State$Direction BOTTOM;
    public static final State$Direction END;
    public static final State$Direction LEFT;
    public static final State$Direction RIGHT;
    public static final State$Direction START;
    public static final State$Direction TOP;
    public static final /* synthetic */ State$Direction[] b;

    static {
        State$Direction state$Direction = new State$Direction("LEFT", 0);
        LEFT = state$Direction;
        State$Direction state$Direction2 = new State$Direction("RIGHT", 1);
        RIGHT = state$Direction2;
        State$Direction state$Direction3 = new State$Direction("START", 2);
        START = state$Direction3;
        State$Direction state$Direction4 = new State$Direction("END", 3);
        END = state$Direction4;
        State$Direction state$Direction5 = new State$Direction("TOP", 4);
        TOP = state$Direction5;
        State$Direction state$Direction6 = new State$Direction("BOTTOM", 5);
        BOTTOM = state$Direction6;
        b = new State$Direction[]{state$Direction, state$Direction2, state$Direction3, state$Direction4, state$Direction5, state$Direction6};
    }

    public static State$Direction valueOf(String str) {
        return (State$Direction) Enum.valueOf(State$Direction.class, str);
    }

    public static State$Direction[] values() {
        return (State$Direction[]) b.clone();
    }
}
