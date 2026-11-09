package defpackage;

/* JADX INFO: loaded from: classes.dex */
public final class ac0 implements Comparable {
    public sj0 b;
    public final /* synthetic */ bc0 c;

    public ac0(bc0 bc0Var) {
        this.c = bc0Var;
    }

    @Override // java.lang.Comparable
    public final int compareTo(Object obj) {
        return this.b.b - ((sj0) obj).b;
    }

    public final String toString() {
        String string = "[ ";
        if (this.b != null) {
            for (int i = 0; i < 9; i++) {
                StringBuilder sbL = hk.l(string);
                sbL.append(this.b.h[i]);
                sbL.append(" ");
                string = sbL.toString();
            }
        }
        return string + "] " + this.b;
    }
}
