package defpackage;

import io.nats.client.support.NatsConstants;
import java.security.PrivilegedAction;
import java.security.Provider;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

/* JADX INFO: loaded from: classes.dex */
public final class jb implements PrivilegedAction {
    public final /* synthetic */ String a;
    public final /* synthetic */ String b;
    public final /* synthetic */ String c;
    public final /* synthetic */ BouncyCastleProvider d;

    public jb(BouncyCastleProvider bouncyCastleProvider, String str, String str2, String str3) {
        this.d = bouncyCastleProvider;
        this.a = str;
        this.b = str2;
        this.c = str3;
    }

    @Override // java.security.PrivilegedAction
    public final Object run() {
        BouncyCastleProvider bouncyCastleProvider = this.d;
        Provider.Service service = super/*java.security.Provider*/.getService(this.a, this.b);
        if (service == null || service.getClassName() == null) {
            return null;
        }
        bouncyCastleProvider.E.put(this.c, service);
        bouncyCastleProvider.remove(service.getType() + NatsConstants.DOT + service.getAlgorithm());
        bouncyCastleProvider.putService(service);
        return service;
    }
}
