package defpackage;

import java.io.IOException;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public abstract class k0 extends g0 implements Iterable {
    public static final o G = new o(18, k0.class);
    public final t[] E;
    public t[] F;

    public k0() {
        t[] tVarArr = u.d;
        this.E = tVarArr;
        this.F = tVarArr;
    }

    public static byte[] o(t tVar) {
        try {
            return tVar.b().f();
        } catch (IOException unused) {
            throw new IllegalArgumentException("cannot encode object added to SET");
        }
    }

    public static boolean p(byte[] bArr, byte[] bArr2) {
        int i = bArr[0] & 223;
        int i2 = bArr2[0] & 223;
        if (i != i2) {
            return i < i2;
        }
        int iMin = Math.min(bArr.length, bArr2.length) - 1;
        for (int i3 = 1; i3 < iMin; i3++) {
            byte b = bArr[i3];
            byte b2 = bArr2[i3];
            if (b != b2) {
                return (b & 255) < (b2 & 255);
            }
        }
        return (bArr[iMin] & 255) <= (bArr2[iMin] & 255);
    }

    public static void q(t[] tVarArr) {
        int i;
        int length = tVarArr.length;
        if (length < 2) {
            return;
        }
        t tVar = tVarArr[0];
        t tVar2 = tVarArr[1];
        byte[] bArrO = o(tVar);
        byte[] bArrO2 = o(tVar2);
        if (p(bArrO2, bArrO)) {
            tVar2 = tVar;
            tVar = tVar2;
            bArrO2 = bArrO;
            bArrO = bArrO2;
        }
        for (int i2 = 2; i2 < length; i2++) {
            t tVar3 = tVarArr[i2];
            byte[] bArrO3 = o(tVar3);
            if (p(bArrO2, bArrO3)) {
                tVarArr[i2 - 2] = tVar;
                tVar = tVar2;
                bArrO = bArrO2;
                tVar2 = tVar3;
                bArrO2 = bArrO3;
            } else if (p(bArrO, bArrO3)) {
                tVarArr[i2 - 2] = tVar;
                tVar = tVar3;
                bArrO = bArrO3;
            } else {
                int i3 = i2 - 1;
                while (true) {
                    i = i3 - 1;
                    if (i <= 0) {
                        break;
                    }
                    t tVar4 = tVarArr[i3 - 2];
                    if (p(o(tVar4), bArrO3)) {
                        break;
                    }
                    tVarArr[i] = tVar4;
                    i3 = i;
                }
                tVarArr[i] = tVar3;
            }
        }
        tVarArr[length - 2] = tVar;
        tVarArr[length - 1] = tVar2;
    }

    @Override // defpackage.g0
    public final boolean g(g0 g0Var) {
        if (g0Var instanceof k0) {
            k0 k0Var = (k0) g0Var;
            int length = this.E.length;
            if (k0Var.E.length == length) {
                xk xkVar = (xk) m();
                xk xkVar2 = (xk) k0Var.m();
                for (int i = 0; i < length; i++) {
                    g0 g0VarB = xkVar.E[i].b();
                    g0 g0VarB2 = xkVar2.E[i].b();
                    if (g0VarB == g0VarB2 || g0VarB.g(g0VarB2)) {
                    }
                }
                return true;
            }
        }
        return false;
    }

    @Override // defpackage.g0, defpackage.a0
    public final int hashCode() {
        t[] tVarArr = this.E;
        int length = tVarArr.length;
        int iHashCode = length + 1;
        while (true) {
            length--;
            if (length < 0) {
                return iHashCode;
            }
            iHashCode += tVarArr[length].b().hashCode();
        }
    }

    @Override // defpackage.g0
    public final boolean i() {
        return true;
    }

    @Override // java.lang.Iterable
    public final Iterator iterator() {
        t[] tVarArr = this.E;
        return new s0(tVarArr.length < 1 ? u.d : (t[]) tVarArr.clone());
    }

    @Override // defpackage.g0
    public g0 m() {
        if (this.F == null) {
            t[] tVarArr = (t[]) this.E.clone();
            this.F = tVarArr;
            q(tVarArr);
        }
        xk xkVar = new xk(true, this.F);
        xkVar.H = -1;
        return xkVar;
    }

    @Override // defpackage.g0
    public g0 n() {
        nl nlVar = new nl(this.E, this.F);
        nlVar.H = -1;
        return nlVar;
    }

    public final String toString() {
        t[] tVarArr = this.E;
        int length = tVarArr.length;
        if (length == 0) {
            return "[]";
        }
        StringBuffer stringBuffer = new StringBuffer("[");
        int i = 0;
        while (true) {
            stringBuffer.append(tVarArr[i]);
            i++;
            if (i >= length) {
                stringBuffer.append(']');
                return stringBuffer.toString();
            }
            stringBuffer.append(", ");
        }
    }

    public k0(u uVar, boolean z) {
        t[] tVarArrF;
        int i;
        if (uVar == null) {
            throw new NullPointerException("'elementVector' cannot be null");
        }
        if (!z || (i = uVar.b) < 2) {
            tVarArrF = uVar.f();
        } else {
            if (i == 0) {
                tVarArrF = u.d;
            } else {
                t[] tVarArr = new t[i];
                System.arraycopy((t[]) uVar.c, 0, tVarArr, 0, i);
                tVarArrF = tVarArr;
            }
            q(tVarArrF);
        }
        this.E = tVarArrF;
        if (!z && tVarArrF.length >= 2) {
            tVarArrF = null;
        }
        this.F = tVarArrF;
    }

    public k0(boolean z, t[] tVarArr) {
        this.E = tVarArr;
        if (!z && tVarArr.length >= 2) {
            tVarArr = null;
        }
        this.F = tVarArr;
    }

    public k0(t[] tVarArr, t[] tVarArr2) {
        this.E = tVarArr;
        this.F = tVarArr2;
    }
}
