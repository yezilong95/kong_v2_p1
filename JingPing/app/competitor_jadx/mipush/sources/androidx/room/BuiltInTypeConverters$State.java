package androidx.room;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes.dex */
public final class BuiltInTypeConverters$State {
    public static final BuiltInTypeConverters$State DISABLED;
    public static final /* synthetic */ BuiltInTypeConverters$State[] E;
    public static final BuiltInTypeConverters$State ENABLED;
    public static final BuiltInTypeConverters$State INHERITED;

    static {
        BuiltInTypeConverters$State builtInTypeConverters$State = new BuiltInTypeConverters$State("ENABLED", 0);
        ENABLED = builtInTypeConverters$State;
        BuiltInTypeConverters$State builtInTypeConverters$State2 = new BuiltInTypeConverters$State("DISABLED", 1);
        DISABLED = builtInTypeConverters$State2;
        BuiltInTypeConverters$State builtInTypeConverters$State3 = new BuiltInTypeConverters$State("INHERITED", 2);
        INHERITED = builtInTypeConverters$State3;
        E = new BuiltInTypeConverters$State[]{builtInTypeConverters$State, builtInTypeConverters$State2, builtInTypeConverters$State3};
    }

    public static BuiltInTypeConverters$State valueOf(String str) {
        return (BuiltInTypeConverters$State) Enum.valueOf(BuiltInTypeConverters$State.class, str);
    }

    public static BuiltInTypeConverters$State[] values() {
        return (BuiltInTypeConverters$State[]) E.clone();
    }
}
