package defpackage;

/* JADX INFO: loaded from: classes.dex */
public final class hz0 implements Comparable {
    public final int E;
    public final int F;
    public final String G;
    public final String H;

    public hz0(String str, int i, int i2, String str2) {
        this.E = i;
        this.F = i2;
        this.G = str;
        this.H = str2;
    }

    @Override // java.lang.Comparable
    public final int compareTo(Object obj) {
        hz0 hz0Var = (hz0) obj;
        t40.n(hz0Var, "other");
        int i = this.E - hz0Var.E;
        return i == 0 ? this.F - hz0Var.F : i;
    }
}
