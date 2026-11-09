package defpackage;

/* JADX INFO: loaded from: classes.dex */
public final class xn implements sx {
    public final boolean b;

    public xn(boolean z) {
        this.b = z;
    }

    @Override // defpackage.sx
    public final boolean a() {
        return this.b;
    }

    @Override // defpackage.sx
    public final p90 c() {
        return null;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("Empty{");
        sb.append(this.b ? "Active" : "New");
        sb.append('}');
        return sb.toString();
    }
}
