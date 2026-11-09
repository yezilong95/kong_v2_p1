package defpackage;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes.dex */
public final class lf0 {
    public static final lf0 E;
    public static final lf0 F;
    public static final lf0 G;
    public static final lf0 H;
    public static final /* synthetic */ lf0[] I;

    static {
        lf0 lf0Var = new lf0("UNDEFINED", 0);
        E = lf0Var;
        lf0 lf0Var2 = new lf0("SETUP", 1);
        F = lf0Var2;
        lf0 lf0Var3 = new lf0("MOVING", 2);
        G = lf0Var3;
        lf0 lf0Var4 = new lf0("FINISHED", 3);
        H = lf0Var4;
        I = new lf0[]{lf0Var, lf0Var2, lf0Var3, lf0Var4};
    }

    public static lf0 valueOf(String str) {
        return (lf0) Enum.valueOf(lf0.class, str);
    }

    public static lf0[] values() {
        return (lf0[]) I.clone();
    }
}
