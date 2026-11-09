package defpackage;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Iterator;
import org.bouncycastle.asn1.ASN1ParsingException;

/* JADX INFO: loaded from: classes.dex */
public final class k80 extends j0 {
    public byte[] G;

    @Override // defpackage.g0
    public final void h(p50 p50Var, boolean z) throws IOException {
        byte[] bArr;
        synchronized (this) {
            bArr = this.G;
        }
        if (bArr != null) {
            p50Var.G(48, z, bArr);
        } else {
            super.n().h(p50Var, z);
        }
    }

    @Override // defpackage.j0, defpackage.g0, defpackage.a0
    public final int hashCode() {
        x();
        return super.hashCode();
    }

    @Override // defpackage.j0, java.lang.Iterable
    public final Iterator iterator() {
        x();
        return super.iterator();
    }

    @Override // defpackage.g0
    public final int j(boolean z) {
        byte[] bArr;
        synchronized (this) {
            bArr = this.G;
        }
        return bArr != null ? p50.x(bArr.length, z) : super.n().j(z);
    }

    @Override // defpackage.j0, defpackage.g0
    public final g0 m() {
        x();
        return super.m();
    }

    @Override // defpackage.j0, defpackage.g0
    public final g0 n() {
        x();
        return super.n();
    }

    @Override // defpackage.j0
    public final t r(int i) {
        x();
        return this.E[i];
    }

    @Override // defpackage.j0
    public final Enumeration s() {
        byte[] bArr;
        synchronized (this) {
            bArr = this.G;
        }
        if (bArr == null) {
            return new i0(this);
        }
        j80 j80Var = new j80();
        j80Var.a = new x(bArr, 0);
        j80Var.b = j80Var.a();
        return j80Var;
    }

    @Override // defpackage.j0
    public final int size() {
        x();
        return this.E.length;
    }

    @Override // defpackage.j0
    public final p t() {
        return ((j0) n()).t();
    }

    @Override // defpackage.j0
    public final mk u() {
        return ((j0) n()).u();
    }

    @Override // defpackage.j0
    public final e0 v() {
        return ((j0) n()).v();
    }

    @Override // defpackage.j0
    public final k0 w() {
        return ((j0) n()).w();
    }

    public final synchronized void x() {
        u uVar;
        if (this.G != null) {
            x xVar = new x(this.G, 0);
            try {
                g0 g0VarZ = xVar.z();
                if (g0VarZ == null) {
                    uVar = new u(0);
                } else {
                    u uVar2 = new u();
                    do {
                        uVar2.c(g0VarZ);
                        g0VarZ = xVar.z();
                    } while (g0VarZ != null);
                    uVar = uVar2;
                }
                xVar.close();
                this.E = uVar.f();
                this.G = null;
            } catch (IOException e) {
                throw new ASN1ParsingException("malformed ASN.1: " + e, e);
            }
        }
    }
}
