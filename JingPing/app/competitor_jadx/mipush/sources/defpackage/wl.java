package defpackage;

import io.nats.client.support.NatsJetStreamConstants;
import java.net.URL;
import java.security.AccessController;
import java.security.SecureRandom;
import kotlin.uuid.Uuid;
import org.bouncycastle.crypto.prng.SP800SecureRandom;

/* JADX INFO: loaded from: classes.dex */
public abstract class wl {
    public static final cu b;
    public static final String[][] a = {new String[]{"sun.security.provider.Sun", "sun.security.provider.SecureRandom"}, new String[]{"org.apache.harmony.security.provider.crypto.CryptoProvider", "org.apache.harmony.security.provider.crypto.SHA1PRNG_SecureRandomImpl"}, new String[]{"com.android.org.conscrypt.OpenSSLProvider", "com.android.org.conscrypt.OpenSSLRandom"}, new String[]{"org.conscrypt.OpenSSLProvider", "org.conscrypt.OpenSSLRandom"}};
    public static Thread c = null;

    static {
        b = null;
        b = new cu();
    }

    public static SP800SecureRandom a(boolean z) {
        if (cm0.b("org.bouncycastle.drbg.entropysource") != null) {
            sl slVar = (sl) AccessController.doPrivileged(new ef(cm0.b("org.bouncycastle.drbg.entropysource"), 1));
            fu fuVarA = slVar.a(Uuid.SIZE_BITS);
            byte[] bArrB = fuVarA.b();
            return new SP800SecureRandom(slVar.a(NatsJetStreamConstants.MAX_PULL_SIZE), new ou(new fq0(), fuVarA.b(), z91.l(z ? e(bArrB) : f(bArrB)), 14), z);
        }
        if (!cm0.c("org.bouncycastle.drbg.entropy_thread")) {
            i5 i5Var = new i5(NatsJetStreamConstants.MAX_PULL_SIZE);
            byte[] bArrB2 = i5Var.b();
            return new SP800SecureRandom(new i5(NatsJetStreamConstants.MAX_PULL_SIZE), new ou(new fq0(), i5Var.b(), z91.l(z ? e(bArrB2) : f(bArrB2)), 14), z);
        }
        cu cuVar = b;
        synchronized (cuVar) {
            try {
                if (c == null) {
                    Thread thread = new Thread(cuVar, "BC Entropy Daemon");
                    c = thread;
                    thread.setDaemon(true);
                    c.start();
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        i5 i5Var2 = new i5(cuVar, NatsJetStreamConstants.MAX_PULL_SIZE);
        byte[] bArrB3 = i5Var2.b();
        return new SP800SecureRandom(new i5(cuVar, NatsJetStreamConstants.MAX_PULL_SIZE), new ou(new fq0(), i5Var2.b(), z91.l(z ? e(bArrB3) : f(bArrB3)), 14), z);
    }

    public static sl b() {
        if (!((Boolean) AccessController.doPrivileged(new pl(0))).booleanValue()) {
            return c();
        }
        SecureRandom secureRandom = (SecureRandom) AccessController.doPrivileged(new pl(1));
        return secureRandom == null ? c() : new sl(2, secureRandom);
    }

    public static sl c() {
        String str = (String) AccessController.doPrivileged(new pl(2));
        if (str == null) {
            return new sl(2, new ql(d()));
        }
        try {
            return new sl(new URL(str));
        } catch (Exception unused) {
            return new sl(2, new ql(d()));
        }
    }

    public static final Object[] d() {
        char c2 = 0;
        for (int i = 0; i < 4; i++) {
            String[] strArr = a[i];
            try {
                return new Object[]{Class.forName(strArr[c2]).newInstance(), Class.forName(strArr[1]).newInstance()};
            } catch (Throwable unused) {
            }
        }
        return null;
    }

    public static byte[] e(byte[] bArr) {
        byte[] bArrB = jx0.b("Default");
        byte[] bArr2 = new byte[8];
        ma0.S(Thread.currentThread().getId(), bArr2, 0);
        byte[] bArr3 = new byte[8];
        ma0.S(System.currentTimeMillis(), bArr3, 0);
        return z91.o(bArrB, bArr, bArr2, bArr3);
    }

    public static byte[] f(byte[] bArr) {
        byte[] bArrB = jx0.b("Nonce");
        long id = Thread.currentThread().getId();
        byte[] bArr2 = new byte[8];
        ma0.L(bArr2, (int) (id & 4294967295L), 0);
        ma0.L(bArr2, (int) (id >>> 32), 4);
        long jCurrentTimeMillis = System.currentTimeMillis();
        byte[] bArr3 = new byte[8];
        ma0.L(bArr3, (int) (4294967295L & jCurrentTimeMillis), 0);
        ma0.L(bArr3, (int) (jCurrentTimeMillis >>> 32), 4);
        return z91.o(bArrB, bArr, bArr2, bArr3);
    }
}
