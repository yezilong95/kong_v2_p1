package androidx.loader.content;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes.dex */
public final class ModernAsyncTask$Status {
    public static final /* synthetic */ ModernAsyncTask$Status[] E;
    public static final ModernAsyncTask$Status FINISHED;
    public static final ModernAsyncTask$Status PENDING;
    public static final ModernAsyncTask$Status RUNNING;

    static {
        ModernAsyncTask$Status modernAsyncTask$Status = new ModernAsyncTask$Status("PENDING", 0);
        PENDING = modernAsyncTask$Status;
        ModernAsyncTask$Status modernAsyncTask$Status2 = new ModernAsyncTask$Status("RUNNING", 1);
        RUNNING = modernAsyncTask$Status2;
        ModernAsyncTask$Status modernAsyncTask$Status3 = new ModernAsyncTask$Status("FINISHED", 2);
        FINISHED = modernAsyncTask$Status3;
        E = new ModernAsyncTask$Status[]{modernAsyncTask$Status, modernAsyncTask$Status2, modernAsyncTask$Status3};
    }

    public static ModernAsyncTask$Status valueOf(String str) {
        return (ModernAsyncTask$Status) Enum.valueOf(ModernAsyncTask$Status.class, str);
    }

    public static ModernAsyncTask$Status[] values() {
        return (ModernAsyncTask$Status[]) E.clone();
    }
}
