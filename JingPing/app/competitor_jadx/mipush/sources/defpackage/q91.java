package defpackage;

/* JADX INFO: loaded from: classes.dex */
public final class q91 extends a0 implements s {
    public final g0 E;

    public q91() {
        this.E = rk.E;
    }

    public static q91 g(Object obj) {
        if (obj == null || (obj instanceof q91)) {
            return (q91) obj;
        }
        if (obj instanceof g0) {
            return new q91((g0) obj);
        }
        if (!(obj instanceof byte[])) {
            throw new IllegalArgumentException("unknown object in getInstance()");
        }
        try {
            return new q91(g0.l((byte[]) obj));
        } catch (Exception e) {
            throw new IllegalArgumentException("unable to parse encoded data: " + e.getMessage());
        }
    }

    @Override // defpackage.a0, defpackage.t
    public final g0 b() {
        return this.E;
    }

    public q91(d0 d0Var) {
        this.E = d0Var;
    }

    public q91(g0 g0Var) {
        this.E = g0Var;
    }

    public q91(s91 s91Var) {
        this.E = null;
        this.E = s91Var.b();
    }
}
