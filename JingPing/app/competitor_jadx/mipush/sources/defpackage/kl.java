package defpackage;

import java.io.IOException;
import java.io.InputStream;
import org.bouncycastle.asn1.ASN1ParsingException;

/* JADX INFO: loaded from: classes.dex */
public final class kl implements q {
    public final sn E;
    public int F = 0;

    public kl(sn snVar) {
        this.E = snVar;
    }

    @Override // defpackage.t
    public final g0 b() {
        try {
            return d();
        } catch (IOException e) {
            throw new ASN1ParsingException("IOException converting stream to byte array: " + e.getMessage(), e);
        }
    }

    @Override // defpackage.q
    public final int c() {
        return this.F;
    }

    @Override // defpackage.u30
    public final g0 d() {
        return p.o(this.E.l());
    }

    @Override // defpackage.q
    public final InputStream e() throws IOException {
        sn snVar = this.E;
        int i = snVar.H;
        if (i < 1) {
            throw new IllegalStateException("content octets cannot be empty");
        }
        int i2 = snVar.read();
        this.F = i2;
        if (i2 > 0) {
            if (i < 2) {
                throw new IllegalStateException("zero length data with non-zero pad bits");
            }
            if (i2 > 7) {
                throw new IllegalStateException("pad bits cannot be greater than 7 or less than 0");
            }
        }
        return snVar;
    }
}
