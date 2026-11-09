package defpackage;

import java.io.IOException;
import java.io.InputStream;
import org.bouncycastle.asn1.ASN1ParsingException;

/* JADX INFO: loaded from: classes.dex */
public final class f9 implements f0 {
    public final /* synthetic */ int E = 1;
    public Object F;

    public /* synthetic */ f9() {
    }

    public static e9 f(l0 l0Var) {
        return new e9(la0.Z(new ci(l0Var)), null);
    }

    @Override // defpackage.f0
    public final InputStream a() {
        switch (this.E) {
            case 0:
                return new ci((l0) this.F);
            default:
                return (sn) this.F;
        }
    }

    @Override // defpackage.t
    public final g0 b() {
        switch (this.E) {
            case 0:
                try {
                    return f((l0) this.F);
                } catch (IOException e) {
                    throw new ASN1ParsingException("IOException converting stream to byte array: " + e.getMessage(), e);
                }
            default:
                try {
                    return d();
                } catch (IOException e2) {
                    throw new ASN1ParsingException("IOException converting stream to byte array: " + e2.getMessage(), e2);
                }
        }
    }

    @Override // defpackage.u30
    public final g0 d() {
        switch (this.E) {
            case 0:
                return f((l0) this.F);
            default:
                return new tk(((sn) this.F).l());
        }
    }

    public f9(l0 l0Var) {
        this.F = l0Var;
    }
}
