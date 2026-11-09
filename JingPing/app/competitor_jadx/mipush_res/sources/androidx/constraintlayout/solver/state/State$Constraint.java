package androidx.constraintlayout.solver.state;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes.dex */
public final class State$Constraint {
    public static final State$Constraint BASELINE_TO_BASELINE;
    public static final State$Constraint BOTTOM_TO_BOTTOM;
    public static final State$Constraint BOTTOM_TO_TOP;
    public static final State$Constraint CENTER_HORIZONTALLY;
    public static final State$Constraint CENTER_VERTICALLY;
    public static final /* synthetic */ State$Constraint[] E;
    public static final State$Constraint END_TO_END;
    public static final State$Constraint END_TO_START;
    public static final State$Constraint LEFT_TO_LEFT;
    public static final State$Constraint LEFT_TO_RIGHT;
    public static final State$Constraint RIGHT_TO_LEFT;
    public static final State$Constraint RIGHT_TO_RIGHT;
    public static final State$Constraint START_TO_END;
    public static final State$Constraint START_TO_START;
    public static final State$Constraint TOP_TO_BOTTOM;
    public static final State$Constraint TOP_TO_TOP;

    static {
        State$Constraint state$Constraint = new State$Constraint("LEFT_TO_LEFT", 0);
        LEFT_TO_LEFT = state$Constraint;
        State$Constraint state$Constraint2 = new State$Constraint("LEFT_TO_RIGHT", 1);
        LEFT_TO_RIGHT = state$Constraint2;
        State$Constraint state$Constraint3 = new State$Constraint("RIGHT_TO_LEFT", 2);
        RIGHT_TO_LEFT = state$Constraint3;
        State$Constraint state$Constraint4 = new State$Constraint("RIGHT_TO_RIGHT", 3);
        RIGHT_TO_RIGHT = state$Constraint4;
        State$Constraint state$Constraint5 = new State$Constraint("START_TO_START", 4);
        START_TO_START = state$Constraint5;
        State$Constraint state$Constraint6 = new State$Constraint("START_TO_END", 5);
        START_TO_END = state$Constraint6;
        State$Constraint state$Constraint7 = new State$Constraint("END_TO_START", 6);
        END_TO_START = state$Constraint7;
        State$Constraint state$Constraint8 = new State$Constraint("END_TO_END", 7);
        END_TO_END = state$Constraint8;
        State$Constraint state$Constraint9 = new State$Constraint("TOP_TO_TOP", 8);
        TOP_TO_TOP = state$Constraint9;
        State$Constraint state$Constraint10 = new State$Constraint("TOP_TO_BOTTOM", 9);
        TOP_TO_BOTTOM = state$Constraint10;
        State$Constraint state$Constraint11 = new State$Constraint("BOTTOM_TO_TOP", 10);
        BOTTOM_TO_TOP = state$Constraint11;
        State$Constraint state$Constraint12 = new State$Constraint("BOTTOM_TO_BOTTOM", 11);
        BOTTOM_TO_BOTTOM = state$Constraint12;
        State$Constraint state$Constraint13 = new State$Constraint("BASELINE_TO_BASELINE", 12);
        BASELINE_TO_BASELINE = state$Constraint13;
        State$Constraint state$Constraint14 = new State$Constraint("CENTER_HORIZONTALLY", 13);
        CENTER_HORIZONTALLY = state$Constraint14;
        State$Constraint state$Constraint15 = new State$Constraint("CENTER_VERTICALLY", 14);
        CENTER_VERTICALLY = state$Constraint15;
        E = new State$Constraint[]{state$Constraint, state$Constraint2, state$Constraint3, state$Constraint4, state$Constraint5, state$Constraint6, state$Constraint7, state$Constraint8, state$Constraint9, state$Constraint10, state$Constraint11, state$Constraint12, state$Constraint13, state$Constraint14, state$Constraint15};
    }

    public static State$Constraint valueOf(String str) {
        return (State$Constraint) Enum.valueOf(State$Constraint.class, str);
    }

    public static State$Constraint[] values() {
        return (State$Constraint[]) E.clone();
    }
}
