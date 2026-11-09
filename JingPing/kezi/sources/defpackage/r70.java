package defpackage;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes.dex */
public final class r70 {
    public static final r70 b;
    public static final r70 c;
    public static final r70 d;
    public static final r70 e;
    public static final /* synthetic */ r70[] f;

    static {
        r70 r70Var = new r70("UNDEFINED", 0);
        b = r70Var;
        r70 r70Var2 = new r70("SETUP", 1);
        c = r70Var2;
        r70 r70Var3 = new r70("MOVING", 2);
        d = r70Var3;
        r70 r70Var4 = new r70("FINISHED", 3);
        e = r70Var4;
        f = new r70[]{r70Var, r70Var2, r70Var3, r70Var4};
    }

    public static r70 valueOf(String str) {
        return (r70) Enum.valueOf(r70.class, str);
    }

    public static r70[] values() {
        return (r70[]) f.clone();
    }
}
