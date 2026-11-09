package defpackage;

/* JADX INFO: loaded from: classes.dex */
public final class pt implements w30 {
    public final boolean a;

    public pt(boolean z) {
        this.a = z;
    }

    @Override // defpackage.w30
    public final th0 getList() {
        return null;
    }

    @Override // defpackage.w30
    public final boolean isActive() {
        return this.a;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("Empty{");
        sb.append(this.a ? "Active" : "New");
        sb.append('}');
        return sb.toString();
    }
}
