package defpackage;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import com.mamimobi.AccessService;
import java.net.URISyntaxException;

/* JADX INFO: loaded from: classes.dex */
public final class rh0 extends ConnectivityManager.NetworkCallback {
    public final /* synthetic */ int a;
    public final /* synthetic */ Object b;

    public /* synthetic */ rh0(int i, Object obj) {
        this.a = i;
        this.b = obj;
    }

    @Override // android.net.ConnectivityManager.NetworkCallback
    public final void onAvailable(Network network) {
        switch (this.a) {
            case 0:
                i6 i6Var = (i6) this.b;
                i6Var.getClass();
                try {
                    AccessService accessService = AccessService.e0;
                    if (accessService != null) {
                        o71.f(accessService).a();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                try {
                    ((AccessService) i6Var.G).H.a((String) i6Var.F);
                } catch (Exception e2) {
                    e2.printStackTrace();
                    return;
                }
                break;
            default:
                lm0 lm0Var = o71.f((Context) this.b).e;
                String str = lm0Var.e;
                if (str != null) {
                    try {
                        lm0Var.b(str);
                    } catch (URISyntaxException unused) {
                        return;
                    }
                    break;
                }
                break;
        }
    }

    @Override // android.net.ConnectivityManager.NetworkCallback
    public final void onLost(Network network) {
        switch (this.a) {
            case 0:
                ((i6) this.b).getClass();
                break;
        }
    }

    private final void a(Network network) {
    }
}
