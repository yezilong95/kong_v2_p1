package defpackage;

/* JADX INFO: loaded from: classes.dex */
public final class vl0 {
    public double a;
    public volatile String b;
    public double c;
    public long d;

    public final synchronized void a() {
        double d = this.a;
        this.a = ((1.0d - d) * 0.15d) + d;
    }
}
