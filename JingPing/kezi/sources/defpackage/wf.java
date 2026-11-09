package defpackage;

import javax.net.ssl.SSLSocket;
import org.conscrypt.Conscrypt;

/* JADX INFO: loaded from: classes.dex */
public final class wf implements zj {
    @Override // defpackage.zj
    public final boolean b(SSLSocket sSLSocket) {
        return vf.d && Conscrypt.isConscrypt(sSLSocket);
    }

    @Override // defpackage.zj
    public final oj0 c(SSLSocket sSLSocket) {
        return new xf();
    }
}
