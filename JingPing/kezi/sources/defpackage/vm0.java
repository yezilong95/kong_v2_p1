package defpackage;

/* JADX INFO: loaded from: classes.dex */
public abstract class vm0 {
    public final String a;
    public final boolean b;
    public zm0 c;
    public long d;

    public vm0(String str, boolean z) {
        oy.j(str, "name");
        this.a = str;
        this.b = z;
        this.d = -1L;
    }

    public abstract long a();

    public final String toString() {
        return this.a;
    }
}
