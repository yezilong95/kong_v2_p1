package defpackage;

/* JADX INFO: loaded from: classes.dex */
public final class sm0 implements Comparable {
    public final int b;
    public final int c;
    public final String d;
    public final String e;

    public sm0(int i, int i2, String str, String str2) {
        this.b = i;
        this.c = i2;
        this.d = str;
        this.e = str2;
    }

    @Override // java.lang.Comparable
    public final int compareTo(Object obj) {
        sm0 sm0Var = (sm0) obj;
        oy.j(sm0Var, "other");
        int i = this.b - sm0Var.b;
        return i == 0 ? this.c - sm0Var.c : i;
    }
}
