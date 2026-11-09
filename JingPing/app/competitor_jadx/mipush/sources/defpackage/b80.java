package defpackage;

/* JADX INFO: loaded from: classes.dex */
public final class b80 {
    public final int a;

    public b80(int i) {
        this.a = i;
    }

    public final boolean equals(Object obj) {
        return (obj instanceof b80) && ((b80) obj).a == this.a;
    }

    public final int hashCode() {
        return this.a;
    }
}
