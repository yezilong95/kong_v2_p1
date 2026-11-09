package defpackage;

import io.nats.client.support.NatsJetStreamConstants;
import java.math.BigInteger;
import org.bouncycastle.crypto.CryptoServicePurpose;
import org.bouncycastle.crypto.a;
import org.bouncycastle.crypto.digests.SHAKENativeDigest;

/* JADX INFO: loaded from: classes.dex */
public abstract class pm0 {
    static {
        d0 d0Var = vj0.a;
        int i = n91.a;
        d0 d0Var2 = vj0.a;
        d0 d0Var3 = vj0.a;
    }

    public static String a(BigInteger bigInteger) {
        byte[] byteArray = bigInteger.toByteArray();
        zq0 sHAKENativeDigest = a.c("SHAKE") ? new SHAKENativeDigest(NatsJetStreamConstants.MAX_PULL_SIZE, CryptoServicePurpose.ANY) : new gq0(NatsJetStreamConstants.MAX_PULL_SIZE);
        sHAKENativeDigest.e(byteArray, 0, byteArray.length);
        byte[] bArr = new byte[4];
        sHAKENativeDigest.h(bArr, 0, 4);
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i != 4; i++) {
            if (i > 0) {
                stringBuffer.append(":");
            }
            char[] cArr = t40.e;
            stringBuffer.append(cArr[(bArr[i] >>> 4) & 15]);
            stringBuffer.append(cArr[bArr[i] & 15]);
        }
        return stringBuffer.toString();
    }

    public static String b(BigInteger bigInteger) {
        byte[] byteArray = bigInteger.toByteArray();
        zq0 sHAKENativeDigest = a.c("SHAKE") ? new SHAKENativeDigest(NatsJetStreamConstants.MAX_PULL_SIZE, CryptoServicePurpose.ANY) : new gq0(NatsJetStreamConstants.MAX_PULL_SIZE);
        sHAKENativeDigest.e(byteArray, 0, byteArray.length);
        byte[] bArr = new byte[20];
        sHAKENativeDigest.h(bArr, 0, 20);
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i != 20; i++) {
            if (i > 0) {
                stringBuffer.append(":");
            }
            char[] cArr = t40.e;
            stringBuffer.append(cArr[(bArr[i] >>> 4) & 15]);
            stringBuffer.append(cArr[bArr[i] & 15]);
        }
        return stringBuffer.toString();
    }
}
