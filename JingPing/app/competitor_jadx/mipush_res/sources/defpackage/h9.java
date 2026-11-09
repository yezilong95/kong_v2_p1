package defpackage;

import java.io.IOException;
import org.bouncycastle.asn1.ASN1Exception;
import org.bouncycastle.asn1.ASN1ParsingException;

/* JADX INFO: loaded from: classes.dex */
public final class h9 implements t, u30 {
    public final /* synthetic */ int E;
    public l0 F;

    public /* synthetic */ h9(int i) {
        this.E = i;
    }

    public static mk f(l0 l0Var) throws ASN1Exception {
        try {
            wk wkVar = new wk(l0Var.n(), 1);
            wkVar.H = -1;
            return new mk(wkVar, 1);
        } catch (IllegalArgumentException e) {
            throw new ASN1Exception(e, e.getMessage());
        }
    }

    @Override // defpackage.t
    public final g0 b() {
        switch (this.E) {
            case 0:
                try {
                    return new g9(this.F.n());
                } catch (IOException e) {
                    throw new IllegalStateException(e.getMessage());
                }
            case 1:
                try {
                    return new i9(this.F.n(), false);
                } catch (IOException e2) {
                    throw new ASN1ParsingException(e2.getMessage(), e2);
                }
            case 2:
                try {
                    return d();
                } catch (IOException e3) {
                    throw new ASN1ParsingException("unable to get DER object", e3);
                } catch (IllegalArgumentException e4) {
                    throw new ASN1ParsingException("unable to get DER object", e4);
                }
            case 3:
                try {
                    return d();
                } catch (IOException e5) {
                    throw new IllegalStateException(e5.getMessage());
                }
            default:
                try {
                    return d();
                } catch (IOException e6) {
                    throw new ASN1ParsingException(e6.getMessage(), e6);
                }
        }
    }

    @Override // defpackage.u30
    public final g0 d() {
        switch (this.E) {
            case 0:
                return new g9(this.F.n());
            case 1:
                return new i9(this.F.n(), false);
            case 2:
                return f(this.F);
            case 3:
                return ll.a(this.F.n());
            default:
                return ll.b(this.F.n());
        }
    }

    public h9(l0 l0Var) {
        this.E = 2;
        this.F = l0Var;
    }
}
