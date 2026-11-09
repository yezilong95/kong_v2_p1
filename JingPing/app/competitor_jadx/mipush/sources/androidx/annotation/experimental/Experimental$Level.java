package androidx.annotation.experimental;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes.dex */
public final class Experimental$Level {
    public static final /* synthetic */ Experimental$Level[] E;
    public static final Experimental$Level ERROR;
    public static final Experimental$Level WARNING;

    static {
        Experimental$Level experimental$Level = new Experimental$Level("WARNING", 0);
        WARNING = experimental$Level;
        Experimental$Level experimental$Level2 = new Experimental$Level("ERROR", 1);
        ERROR = experimental$Level2;
        E = new Experimental$Level[]{experimental$Level, experimental$Level2};
    }

    public static Experimental$Level valueOf(String str) {
        return (Experimental$Level) Enum.valueOf(Experimental$Level.class, str);
    }

    public static Experimental$Level[] values() {
        return (Experimental$Level[]) E.clone();
    }
}
