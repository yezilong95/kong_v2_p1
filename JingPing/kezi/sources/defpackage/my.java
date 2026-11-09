package defpackage;

/* JADX INFO: loaded from: classes.dex */
public final class my extends ky {
    public static final my e = new my(1, 0, 1);

    @Override // defpackage.ky
    public final boolean equals(Object obj) {
        if (!(obj instanceof my)) {
            return false;
        }
        if (isEmpty() && ((my) obj).isEmpty()) {
            return true;
        }
        my myVar = (my) obj;
        if (this.b == myVar.b) {
            return this.c == myVar.c;
        }
        return false;
    }

    @Override // defpackage.ky
    public final int hashCode() {
        if (isEmpty()) {
            return -1;
        }
        return (this.b * 31) + this.c;
    }

    @Override // defpackage.ky
    public final boolean isEmpty() {
        return this.b > this.c;
    }

    @Override // defpackage.ky
    public final String toString() {
        return this.b + ".." + this.c;
    }
}
