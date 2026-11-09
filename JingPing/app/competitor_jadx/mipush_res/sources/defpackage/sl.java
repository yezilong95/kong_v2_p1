package defpackage;

import java.io.InputStream;
import java.net.URL;
import java.security.AccessController;

/* JADX INFO: loaded from: classes.dex */
public final class sl {
    public final /* synthetic */ int a;
    public final Object b;

    public /* synthetic */ sl(int i, Object obj) {
        this.a = i;
        this.b = obj;
    }

    public final fu a(int i) {
        switch (this.a) {
            case 0:
                return (tl) ((i5) this.b).f;
            case 1:
                return new vl(this, i);
            default:
                return new vl(this, i, (byte) 0);
        }
    }

    public sl(URL url) {
        this.a = 1;
        this.b = (InputStream) AccessController.doPrivileged(new ib(1, url));
    }
}
