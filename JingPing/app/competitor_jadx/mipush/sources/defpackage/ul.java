package defpackage;

import java.io.IOException;
import java.io.InputStream;
import java.security.PrivilegedAction;

/* JADX INFO: loaded from: classes.dex */
public final class ul implements PrivilegedAction {
    public final /* synthetic */ byte[] a;
    public final /* synthetic */ int b;
    public final /* synthetic */ int c;
    public final /* synthetic */ sl d;

    public ul(sl slVar, byte[] bArr, int i, int i2) {
        this.d = slVar;
        this.a = bArr;
        this.b = i;
        this.c = i2;
    }

    @Override // java.security.PrivilegedAction
    public final Object run() {
        try {
            return Integer.valueOf(((InputStream) this.d.b).read(this.a, this.b, this.c));
        } catch (IOException unused) {
            throw new InternalError("unable to read random source");
        }
    }
}
