package defpackage;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public abstract class j0 extends g0 implements Iterable {
    public static final o F = new o(17, j0.class);
    public t[] E;

    public j0() {
        this.E = u.d;
    }

    public static j0 q(Object obj) {
        if (obj == null || (obj instanceof j0)) {
            return (j0) obj;
        }
        if (obj instanceof t) {
            g0 g0VarB = ((t) obj).b();
            if (g0VarB instanceof j0) {
                return (j0) g0VarB;
            }
        } else if (obj instanceof byte[]) {
            try {
                return (j0) F.b((byte[]) obj);
            } catch (IOException e) {
                throw new IllegalArgumentException("failed to construct sequence from byte[]: " + e.getMessage());
            }
        }
        throw new IllegalArgumentException("unknown object in getInstance: ".concat(obj.getClass().getName()));
    }

    @Override // defpackage.g0
    public final boolean g(g0 g0Var) {
        if (g0Var instanceof j0) {
            j0 j0Var = (j0) g0Var;
            int size = size();
            if (j0Var.size() == size) {
                for (int i = 0; i < size; i++) {
                    g0 g0VarB = this.E[i].b();
                    g0 g0VarB2 = j0Var.E[i].b();
                    if (g0VarB == g0VarB2 || g0VarB.g(g0VarB2)) {
                    }
                }
                return true;
            }
        }
        return false;
    }

    @Override // defpackage.g0, defpackage.a0
    public int hashCode() {
        int length = this.E.length;
        int iHashCode = length + 1;
        while (true) {
            length--;
            if (length < 0) {
                return iHashCode;
            }
            iHashCode = (iHashCode * 257) ^ this.E[length].b().hashCode();
        }
    }

    @Override // defpackage.g0
    public final boolean i() {
        return true;
    }

    @Override // java.lang.Iterable
    public Iterator iterator() {
        return new s0(this.E);
    }

    @Override // defpackage.g0
    public g0 m() {
        wk wkVar = new wk(false, this.E);
        wkVar.H = -1;
        return wkVar;
    }

    @Override // defpackage.g0
    public g0 n() {
        wk wkVar = new wk(this.E, 1);
        wkVar.H = -1;
        return wkVar;
    }

    public final p[] o() {
        int size = size();
        p[] pVarArr = new p[size];
        for (int i = 0; i < size; i++) {
            pVarArr[i] = p.q(this.E[i]);
        }
        return pVarArr;
    }

    public final e0[] p() {
        int size = size();
        e0[] e0VarArr = new e0[size];
        for (int i = 0; i < size; i++) {
            e0VarArr[i] = e0.o(this.E[i]);
        }
        return e0VarArr;
    }

    public t r(int i) {
        return this.E[i];
    }

    public Enumeration s() {
        return new i0(this);
    }

    public int size() {
        return this.E.length;
    }

    public abstract p t();

    public final String toString() {
        int size = size();
        if (size == 0) {
            return "[]";
        }
        StringBuffer stringBuffer = new StringBuffer("[");
        int i = 0;
        while (true) {
            stringBuffer.append(this.E[i]);
            i++;
            if (i >= size) {
                stringBuffer.append(']');
                return stringBuffer.toString();
            }
            stringBuffer.append(", ");
        }
    }

    public abstract mk u();

    public abstract e0 v();

    public abstract k0 w();

    public j0(u uVar) {
        if (uVar == null) {
            throw new NullPointerException("'elementVector' cannot be null");
        }
        this.E = uVar.f();
    }

    public j0(g0 g0Var) {
        if (g0Var == null) {
            throw new NullPointerException("'element' cannot be null");
        }
        this.E = new t[]{g0Var};
    }

    public j0(t[] tVarArr) {
        this.E = tVarArr;
    }
}
