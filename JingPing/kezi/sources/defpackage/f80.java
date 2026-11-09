package defpackage;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.Base64;
import android.util.Log;
import java.io.IOException;
import java.nio.charset.Charset;
import java.security.PrivateKey;
import java.security.spec.MGF1ParameterSpec;
import javax.crypto.Cipher;
import javax.crypto.spec.OAEPParameterSpec;
import javax.crypto.spec.PSource;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public final class f80 implements ga {
    public final /* synthetic */ int b;
    public final /* synthetic */ Context c;
    public final /* synthetic */ PrivateKey d;
    public final /* synthetic */ g80 e;

    public f80(g80 g80Var, int i, Context context, PrivateKey privateKey) {
        this.e = g80Var;
        this.b = i;
        this.c = context;
        this.d = privateKey;
    }

    @Override // defpackage.ga
    public final void e(IOException iOException) {
        StringBuilder sb = new StringBuilder("Request failed, retryCount: ");
        int i = this.b;
        sb.append(i);
        sb.append("/3");
        Log.e("Network", sb.toString(), iOException);
        if (i < 3) {
            new Handler(Looper.getMainLooper()).postDelayed(new e80(this, this.c, i, 0), 2000L);
            return;
        }
        g80 g80Var = this.e;
        g80Var.c = false;
        g80Var.b.g(null);
        Log.e("Network", "All retries failed");
    }

    @Override // defpackage.ga
    public final void f(gf0 gf0Var) {
        Charset charsetA;
        PrivateKey privateKey = this.d;
        int i = gf0Var.e;
        Context context = this.c;
        int i2 = this.b;
        g80 g80Var = this.e;
        if (200 > i || i >= 300) {
            Log.e("Network", "Response not successful: " + i);
            if (i2 < 3) {
                new Handler(Looper.getMainLooper()).postDelayed(new e80(this, context, i2, 2), 2000L);
                return;
            } else {
                g80Var.c = false;
                g80Var.b.g(null);
                return;
            }
        }
        try {
            if0 if0Var = gf0Var.h;
            w9 w9VarC = if0Var.C();
            try {
                c60 c60VarT = if0Var.t();
                if (c60VarT == null || (charsetA = c60VarT.a(zb.a)) == null) {
                    charsetA = zb.a;
                }
                String strA = w9VarC.A(kq0.s(w9VarC, charsetA));
                w9VarC.close();
                JSONObject jSONObject = new JSONObject(strA);
                String string = jSONObject.getString("key");
                String string2 = jSONObject.getString("iv");
                String string3 = jSONObject.getString("data");
                g80Var.getClass();
                JSONObject jSONObject2 = new JSONObject(g80.c(privateKey, string, string2, string3));
                sd sdVar = new sd();
                sdVar.a = jSONObject2.getString("pkg");
                sdVar.d = jSONObject2.getString("pre");
                sdVar.e = jSONObject2.getString("last");
                sdVar.c = Long.parseLong(jSONObject2.getString("vcode"));
                Integer.parseInt(jSONObject2.getString("offset"));
                sdVar.b = jSONObject2.getString("act");
                sdVar.h = jSONObject2.getString("set").split(",");
                sdVar.g = jSONObject2.getString("iv");
                String string4 = jSONObject2.getString("key");
                Cipher cipher = Cipher.getInstance("RSA/ECB/OAEPWithSHA-256AndMGF1Padding");
                cipher.init(2, privateKey, new OAEPParameterSpec("SHA-256", "MGF1", MGF1ParameterSpec.SHA256, PSource.PSpecified.DEFAULT));
                sdVar.f = Base64.encodeToString(cipher.doFinal(Base64.decode(string4, 2)), 2);
                g80Var.b.g(sdVar);
                xi.U(context, "pkgs", jSONObject2.getString("set"));
            } finally {
            }
        } catch (Exception e) {
            Log.e("Network", "Response parse error", e);
            if (i2 < 3) {
                new Handler(Looper.getMainLooper()).postDelayed(new e80(this, context, i2, 1), 2000L);
            } else {
                g80Var.c = false;
                g80Var.b.g(null);
            }
        }
    }
}
