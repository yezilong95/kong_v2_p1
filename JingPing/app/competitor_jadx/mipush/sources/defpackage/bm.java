package defpackage;

import io.nats.client.support.NatsJetStreamConstants;
import java.math.BigInteger;
import java.security.interfaces.DSAParams;
import org.bouncycastle.crypto.CryptoServicePurpose;
import org.bouncycastle.crypto.a;
import org.bouncycastle.crypto.digests.SHAKENativeDigest;

/* JADX INFO: loaded from: classes.dex */
public abstract class bm {
    public static final /* synthetic */ int a = 0;

    static {
        d0 d0Var = w91.a;
        d0 d0Var2 = oi0.a;
        d0 d0Var3 = w91.a;
    }

    public static String a(BigInteger bigInteger, DSAParams dSAParams) {
        byte[] bArrO = z91.o(bigInteger.toByteArray(), dSAParams.getP().toByteArray(), dSAParams.getQ().toByteArray(), dSAParams.getG().toByteArray());
        zq0 sHAKENativeDigest = a.c("SHAKE") ? new SHAKENativeDigest(NatsJetStreamConstants.MAX_PULL_SIZE, CryptoServicePurpose.ANY) : new gq0(NatsJetStreamConstants.MAX_PULL_SIZE);
        sHAKENativeDigest.e(bArrO, 0, bArrO.length);
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
