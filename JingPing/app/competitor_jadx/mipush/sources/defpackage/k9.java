package defpackage;

import java.io.IOException;
import org.bouncycastle.asn1.ASN1ParsingException;

/* JADX INFO: loaded from: classes.dex */
public class k9 implements t, u30 {
    public final int E;
    public final int F;
    public final l0 G;

    public k9(int i, int i2, l0 l0Var) {
        this.E = i;
        this.F = i2;
        this.G = l0Var;
    }

    @Override // defpackage.t
    public final g0 b() {
        try {
            return d();
        } catch (IOException e) {
            throw new ASN1ParsingException(e.getMessage());
        }
    }

    @Override // defpackage.u30
    public g0 d() {
        return this.G.j(this.E, this.F);
    }
}
