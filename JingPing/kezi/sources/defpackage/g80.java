package defpackage;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.Base64;
import android.util.Log;
import com.mamimobi.shell.utils.AppConfig;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.spec.MGF1ParameterSpec;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;
import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.OAEPParameterSpec;
import javax.crypto.spec.PSource;
import javax.crypto.spec.SecretKeySpec;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class g80 extends gs0 {
    public final c80 b = new c80();
    public boolean c = false;

    public static String c(PrivateKey privateKey, String str, String str2, String str3) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, InvalidAlgorithmParameterException {
        Cipher cipher = Cipher.getInstance("RSA/ECB/OAEPWithSHA-256AndMGF1Padding");
        cipher.init(2, privateKey, new OAEPParameterSpec("SHA-256", "MGF1", MGF1ParameterSpec.SHA256, PSource.PSpecified.DEFAULT));
        SecretKeySpec secretKeySpec = new SecretKeySpec(cipher.doFinal(Base64.decode(str, 2)), "AES");
        Cipher cipher2 = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher2.init(2, secretKeySpec, new IvParameterSpec(Base64.decode(str2, 2)));
        return new String(cipher2.doFinal(Base64.decode(str3, 2)), StandardCharsets.UTF_8);
    }

    public static String d() {
        try {
            byte[] bArr = new byte[16];
            new SecureRandom().nextBytes(bArr);
            return Base64.encodeToString(MessageDigest.getInstance("SHA-256").digest((Base64.encodeToString(bArr, 2) + "_" + System.currentTimeMillis()).getBytes(StandardCharsets.UTF_8)), 2);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public final void e(Context context, int i) {
        try {
            ArrayList arrayList = new ArrayList();
            String host = AppConfig.getHOST();
            String[] strArr = {AppConfig.getMiddleCertificatePinner()};
            oy.j(host, "pattern");
            arrayList.add(new ab(host, strArr[0]));
            cb cbVar = new cb(ae.g0(arrayList), null);
            ea0 ea0Var = new ea0();
            ea0Var.k = new vk(8);
            cbVar.equals(ea0Var.q);
            ea0Var.q = cbVar;
            ea0Var.f = true;
            TimeUnit timeUnit = TimeUnit.SECONDS;
            oy.j(timeUnit, "unit");
            ea0Var.r = kq0.b(10L);
            oy.j(timeUnit, "unit");
            ea0Var.s = kq0.b(15L);
            fa0 fa0Var = new fa0(ea0Var);
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            keyPairGenerator.initialize(2048);
            KeyPair keyPairGenerateKeyPair = keyPairGenerator.generateKeyPair();
            PublicKey publicKey = keyPairGenerateKeyPair.getPublic();
            PrivateKey privateKey = keyPairGenerateKeyPair.getPrivate();
            String strEncodeToString = Base64.encodeToString(publicKey.getEncoded(), 2);
            JSONObject jSONObject = new JSONObject();
            String strU = oy.u(context);
            long jCurrentTimeMillis = System.currentTimeMillis();
            jSONObject.put("platform", "android");
            jSONObject.put("timestamp", jCurrentTimeMillis);
            jSONObject.put("clientId", "a137830959316a3dbcf660a9e7515017");
            jSONObject.put("deviceId", strU);
            String strD = d();
            jSONObject.put("nonce", strD);
            jSONObject.put("sign", oy.C("androida137830959316a3dbcf660a9e7515017" + strU + jCurrentTimeMillis + strD, AppConfig.getSecretKey()));
            jSONObject.put("pubkey", strEncodeToString);
            jSONObject.put("data", AppConfig.getKeyData());
            String string = jSONObject.toString();
            Pattern pattern = c60.c;
            d5 d5VarB = d5.b(string, jq0.q("application/json; charset=utf-8"));
            o2 o2Var = new o2();
            o2Var.u(AppConfig.getClientUrl());
            o2Var.l("POST", d5VarB);
            new wc0(fa0Var, o2Var.a()).e(new f80(this, i, context, privateKey));
        } catch (Exception e) {
            Log.e("Network", "Setup error", e);
            if (i < 3) {
                new Handler(Looper.getMainLooper()).postDelayed(new nk(this, context, i), 2000L);
            } else {
                this.c = false;
                this.b.g(null);
            }
        }
    }
}
