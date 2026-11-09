package defpackage;

import java.io.IOException;
import java.io.InputStream;
import org.bouncycastle.asn1.ASN1ParsingException;

/* JADX INFO: loaded from: classes.dex */
public final class c9 implements q {
    public final l0 E;
    public bi F;

    public c9(l0 l0Var) {
        this.E = l0Var;
    }

    public static b9 f(l0 l0Var) {
        bi biVar = new bi(l0Var);
        return new b9(la0.Z(biVar), biVar.G);
    }

    @Override // defpackage.t
    public final g0 b() {
        try {
            return f(this.E);
        } catch (IOException e) {
            throw new ASN1ParsingException("IOException converting stream to byte array: " + e.getMessage(), e);
        }
    }

    @Override // defpackage.q
    public final int c() {
        return this.F.G;
    }

    @Override // defpackage.u30
    public final g0 d() {
        return f(this.E);
    }

    @Override // defpackage.q
    public final InputStream e() {
        bi biVar = new bi(this.E);
        this.F = biVar;
        return biVar;
    }
}
