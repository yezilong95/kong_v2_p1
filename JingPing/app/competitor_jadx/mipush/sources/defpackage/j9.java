package defpackage;

import androidx.work.Configuration;
import androidx.work.impl.WorkDatabaseVersions;
import androidx.work.impl.WorkManagerImpl;
import java.io.IOException;
import java.util.Arrays;
import kotlin.uuid.Uuid;

/* JADX INFO: loaded from: classes.dex */
public final class j9 extends g0 implements u30 {
    public final int E;
    public final int F;
    public final int G;
    public final t H;
    public final /* synthetic */ int I;

    public j9(int i, int i2, int i3, t tVar) {
        if (tVar == null) {
            throw new NullPointerException("'obj' cannot be null");
        }
        if (i2 == 0 || (i2 & 192) != i2) {
            throw new IllegalArgumentException(zn.j(i2, "invalid tag class: "));
        }
        this.E = tVar instanceof s ? 1 : i;
        this.F = i2;
        this.G = i3;
        this.H = tVar;
    }

    public static j9 p(Object obj) {
        if (obj == null || (obj instanceof j9)) {
            return (j9) obj;
        }
        if (obj instanceof t) {
            g0 g0VarB = ((t) obj).b();
            if (g0VarB instanceof j9) {
                return (j9) g0VarB;
            }
        } else if (obj instanceof byte[]) {
            try {
                g0 g0VarL = g0.l((byte[]) obj);
                if (g0VarL instanceof j9) {
                    return (j9) g0VarL;
                }
                throw new IllegalStateException("unexpected object: ".concat(g0VarL.getClass().getName()));
            } catch (IOException e) {
                throw new IllegalArgumentException("failed to construct tagged object from byte[]: " + e.getMessage());
            }
        }
        throw new IllegalArgumentException("unknown object in getInstance: ".concat(obj.getClass().getName()));
    }

    @Override // defpackage.g0
    public final boolean g(g0 g0Var) {
        if (!(g0Var instanceof j9)) {
            return false;
        }
        j9 j9Var = (j9) g0Var;
        if (this.G != j9Var.G || this.F != j9Var.F) {
            return false;
        }
        if (this.E != j9Var.E && q() != j9Var.q()) {
            return false;
        }
        g0 g0VarB = this.H.b();
        g0 g0VarB2 = j9Var.H.b();
        if (g0VarB == g0VarB2) {
            return true;
        }
        if (q()) {
            return g0VarB.g(g0VarB2);
        }
        try {
            return Arrays.equals(getEncoded(), j9Var.getEncoded());
        } catch (IOException unused) {
            return false;
        }
    }

    @Override // defpackage.g0
    public final void h(p50 p50Var, boolean z) throws IOException {
        switch (this.I) {
            case 0:
                g0 g0VarB = this.H.b();
                boolean zQ = q();
                if (z) {
                    int i = this.F;
                    if (zQ || g0VarB.i()) {
                        i |= 32;
                    }
                    p50Var.I(i, this.G);
                }
                if (!zQ) {
                    g0VarB.h(p50Var, false);
                } else {
                    p50Var.C(Uuid.SIZE_BITS);
                    g0VarB.h(p50Var, true);
                    p50Var.C(0);
                    p50Var.C(0);
                }
                break;
            case 1:
                g0 g0VarM = this.H.b().m();
                boolean zQ2 = q();
                if (z) {
                    int i2 = this.F;
                    if (zQ2 || g0VarM.i()) {
                        i2 |= 32;
                    }
                    p50Var.I(i2, this.G);
                }
                if (zQ2) {
                    p50Var.E(g0VarM.j(true));
                }
                g0VarM.h(p50Var.u(), zQ2);
                break;
            default:
                g0 g0VarN = this.H.b().n();
                boolean zQ3 = q();
                if (z) {
                    int i3 = this.F;
                    if (zQ3 || g0VarN.i()) {
                        i3 |= 32;
                    }
                    p50Var.I(i3, this.G);
                }
                if (zQ3) {
                    p50Var.E(g0VarN.j(true));
                }
                g0VarN.h(p50Var.v(), zQ3);
                break;
        }
    }

    @Override // defpackage.g0, defpackage.a0
    public final int hashCode() {
        return (((this.F * 7919) ^ this.G) ^ (q() ? 15 : 240)) ^ this.H.b().hashCode();
    }

    @Override // defpackage.g0
    public final boolean i() {
        switch (this.I) {
            case 0:
                if (q() || this.H.b().i()) {
                }
                break;
            case 1:
                if (q() || this.H.b().m().i()) {
                }
                break;
            default:
                if (q() || this.H.b().n().i()) {
                }
                break;
        }
        return true;
    }

    @Override // defpackage.g0
    public final int j(boolean z) {
        switch (this.I) {
            case 0:
                g0 g0VarB = this.H.b();
                boolean zQ = q();
                int iJ = g0VarB.j(zQ);
                if (zQ) {
                    iJ += 3;
                }
                return iJ + (z ? p50.y(this.G) : 0);
            case 1:
                g0 g0VarM = this.H.b().m();
                boolean zQ2 = q();
                int iJ2 = g0VarM.j(zQ2);
                if (zQ2) {
                    iJ2 += p50.w(iJ2);
                }
                return iJ2 + (z ? p50.y(this.G) : 0);
            default:
                g0 g0VarN = this.H.b().n();
                boolean zQ3 = q();
                int iJ3 = g0VarN.j(zQ3);
                if (zQ3) {
                    iJ3 += p50.w(iJ3);
                }
                return iJ3 + (z ? p50.y(this.G) : 0);
        }
    }

    @Override // defpackage.g0
    public g0 m() {
        switch (this.I) {
            case 1:
                return this;
            default:
                return new j9(this.E, this.F, this.G, this.H, 1);
        }
    }

    @Override // defpackage.g0
    public g0 n() {
        switch (this.I) {
            case 1:
            case 2:
                return this;
            default:
                return new j9(this.E, this.F, this.G, this.H, 2);
        }
    }

    public final g0 o(boolean z, o oVar) {
        j0 g9Var;
        g0 kkVar;
        t tVar = this.H;
        if (z) {
            if (!q()) {
                throw new IllegalStateException("object implicit - explicit expected.");
            }
            g0 g0VarB = tVar.b();
            oVar.a(g0VarB);
            return g0VarB;
        }
        int i = this.E;
        if (1 == i) {
            throw new IllegalStateException("object explicit - implicit expected.");
        }
        g0 g0VarB2 = tVar.b();
        if (i == 3) {
            switch (this.I) {
                case 0:
                    g9Var = new g9(g0VarB2);
                    break;
                case 1:
                    wk wkVar = new wk(g0VarB2, 0);
                    wkVar.H = -1;
                    g9Var = wkVar;
                    break;
                default:
                    wk wkVar2 = new wk(g0VarB2, 1);
                    wkVar2.H = -1;
                    g9Var = wkVar2;
                    break;
            }
            return oVar.c(g9Var);
        }
        if (i != 4) {
            oVar.a(g0VarB2);
            return g0VarB2;
        }
        if (g0VarB2 instanceof j0) {
            return oVar.c((j0) g0VarB2);
        }
        tk tkVar = (tk) g0VarB2;
        switch (oVar.b) {
            case 0:
                kkVar = new kk(tkVar.E);
                break;
            case 1:
                return p.o(tkVar.E);
            case 2:
                return r.o(tkVar.E);
            case 3:
                return v.o(tkVar.E, false);
            case 4:
            case 17:
            case 18:
            default:
                throw new IllegalStateException("unexpected implicit primitive encoding");
            case 5:
                kkVar = new nk(tkVar.E);
                break;
            case 6:
                kkVar = new w(tkVar.E);
                break;
            case 7:
                kkVar = new pk(tkVar.E);
                break;
            case 8:
                kkVar = new qk(tkVar.E);
                break;
            case WorkDatabaseVersions.VERSION_9 /* 9 */:
                kkVar = new y(tkVar.E);
                break;
            case 10:
                if (tkVar.E.length == 0) {
                    return rk.E;
                }
                throw new IllegalStateException("malformed NULL encoding encountered");
            case WorkDatabaseVersions.VERSION_11 /* 11 */:
                kkVar = new sk(tkVar.E);
                break;
            case WorkDatabaseVersions.VERSION_12 /* 12 */:
                kkVar = new b0(new pk(tkVar.E));
                break;
            case WorkDatabaseVersions.VERSION_13 /* 13 */:
                return d0.q(tkVar.E, false);
            case WorkDatabaseVersions.VERSION_14 /* 14 */:
                return tkVar;
            case WorkDatabaseVersions.VERSION_15 /* 15 */:
                kkVar = new vk(tkVar.E);
                break;
            case 16:
                return h0.o(tkVar.E, false);
            case 19:
                kkVar = new yk(tkVar.E);
                break;
            case Configuration.MIN_SCHEDULER_LIMIT /* 20 */:
                kkVar = new m0(tkVar.E);
                break;
            case 21:
                kkVar = new zk(tkVar.E);
                break;
            case WorkManagerImpl.MAX_PRE_JOB_SCHEDULER_API_LEVEL /* 22 */:
                kkVar = new al(tkVar.E);
                break;
            case WorkManagerImpl.MIN_JOB_SCHEDULER_API_LEVEL /* 23 */:
                kkVar = new bl(tkVar.E);
                break;
            case 24:
                kkVar = new cl(tkVar.E);
                break;
        }
        return kkVar;
    }

    public final boolean q() {
        int i = this.E;
        return i == 1 || i == 3;
    }

    public final String toString() {
        return t40.p0(this.F, this.G) + this.H;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ j9(int i, int i2, int i3, t tVar, int i4) {
        this(i, i2, i3, tVar);
        this.I = i4;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public j9(boolean z, int i, t tVar, int i2) {
        this(z ? 1 : 2, Uuid.SIZE_BITS, i, tVar);
        this.I = i2;
    }

    @Override // defpackage.u30
    public final g0 d() {
        return this;
    }
}
