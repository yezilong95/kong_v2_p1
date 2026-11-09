package defpackage;

/* JADX INFO: loaded from: classes.dex */
public final class qk0 {
    public final int a;
    public final int b;
    public final int c;
    public final float d;
    public final float e;

    public qk0(float f, float f2, int i, int i2, int i3) {
        this.d = f;
        this.e = f2;
        this.a = i;
        this.b = i2;
        this.c = i3;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && qk0.class == obj.getClass()) {
            qk0 qk0Var = (qk0) obj;
            if (this.a == qk0Var.a && this.b == qk0Var.b) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        return (this.a * 31) + this.b;
    }

    public final String toString() {
        return this.d + "," + this.e + "," + this.a + "," + this.b + "," + this.c + ";";
    }
}
