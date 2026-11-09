package defpackage;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public abstract class e0 extends g0 implements f0 {
    public static final o F = new o(14, e0.class);
    public static final byte[] G = new byte[0];
    public final byte[] E;

    public e0(byte[] bArr) {
        if (bArr == null) {
            throw new NullPointerException("'string' cannot be null");
        }
        this.E = bArr;
    }

    public static e0 o(Object obj) {
        if (obj == null || (obj instanceof e0)) {
            return (e0) obj;
        }
        if (obj instanceof t) {
            g0 g0VarB = ((t) obj).b();
            if (g0VarB instanceof e0) {
                return (e0) g0VarB;
            }
        } else if (obj instanceof byte[]) {
            try {
                return (e0) F.b((byte[]) obj);
            } catch (IOException e) {
                throw new IllegalArgumentException("failed to construct OCTET STRING from byte[]: " + e.getMessage());
            }
        }
        throw new IllegalArgumentException("illegal object in getInstance: ".concat(obj.getClass().getName()));
    }

    @Override // defpackage.f0
    public final InputStream a() {
        return new ByteArrayInputStream(this.E);
    }

    @Override // defpackage.g0
    public final boolean g(g0 g0Var) {
        if (!(g0Var instanceof e0)) {
            return false;
        }
        return Arrays.equals(this.E, ((e0) g0Var).E);
    }

    @Override // defpackage.g0, defpackage.a0
    public final int hashCode() {
        return z91.E(this.E);
    }

    @Override // defpackage.g0
    public g0 m() {
        return new tk(this.E);
    }

    @Override // defpackage.g0
    public g0 n() {
        return new tk(this.E);
    }

    public final String toString() {
        d30 d30Var = c30.a;
        byte[] bArr = this.E;
        return "#".concat(jx0.a(c30.c(bArr, 0, bArr.length)));
    }

    @Override // defpackage.u30
    public final g0 d() {
        return this;
    }
}
