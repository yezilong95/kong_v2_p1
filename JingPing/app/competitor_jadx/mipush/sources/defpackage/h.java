package defpackage;

import com.mamimobi.service.AIService;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class h implements HostnameVerifier {
    public final /* synthetic */ int a;

    @Override // javax.net.ssl.HostnameVerifier
    public final boolean verify(String str, SSLSession sSLSession) {
        switch (this.a) {
            case 0:
                int i = AIService.E;
                break;
        }
        return true;
    }
}
