package defpackage;

import java.io.File;
import java.security.PrivilegedAction;
import java.security.SecureRandom;
import java.security.Security;
import java.util.logging.Logger;
import org.bouncycastle.crypto.b;

/* JADX INFO: loaded from: classes.dex */
public final class pl implements PrivilegedAction {
    public final /* synthetic */ int a;

    public /* synthetic */ pl(int i) {
        this.a = i;
    }

    @Override // java.security.PrivilegedAction
    public final Object run() {
        Logger logger;
        StringBuilder sb;
        File file;
        Logger logger2;
        String str;
        boolean z = true;
        switch (this.a) {
            case 0:
                try {
                    if (SecureRandom.class.getMethod("getInstanceStrong", new Class[0]) == null) {
                        z = false;
                    }
                    return Boolean.valueOf(z);
                } catch (Exception unused) {
                    return Boolean.FALSE;
                }
            case 1:
                try {
                    return (SecureRandom) SecureRandom.class.getMethod("getInstanceStrong", new Class[0]).invoke(null, new Object[0]);
                } catch (Exception unused2) {
                    return null;
                }
            case 2:
                return Security.getProperty("securerandom.source");
            case 3:
                File file2 = new File(cm0.b("java.io.tmpdir"));
                if (file2.exists()) {
                    try {
                        long jNanoTime = System.nanoTime();
                        int i = 0;
                        while (true) {
                            if (i < 1000) {
                                file = new File(file2, "bc-lts-jni" + Long.toString(((long) i) + jNanoTime, 32) + "-libs");
                                if (!file.mkdirs()) {
                                    Thread.sleep(jNanoTime % 97);
                                    i++;
                                }
                            } else {
                                file = null;
                            }
                        }
                        if (file == null) {
                            b.c = false;
                            b.e = "unable to create directory in " + file2 + " after 1000 unique attempts";
                            logger2 = b.a;
                            str = "exited with " + b.e;
                        } else {
                            if (file.exists()) {
                                Runtime.getRuntime().addShutdownHook(new Thread(new f(18, file)));
                                return file;
                            }
                            b.c = false;
                            b.e = "unable to create temp directory for jni libs: " + file;
                            logger2 = b.a;
                            str = "exited with " + b.e;
                        }
                        logger2.fine(str);
                        return null;
                    } catch (Exception e) {
                        b.c = false;
                        b.e = "failed because it was not able to create a temporary file in 'java.io.tmpdir' " + e.getMessage();
                        logger = b.a;
                        sb = new StringBuilder("exited with ");
                    }
                } else {
                    b.c = false;
                    b.e = file2 + " did not exist";
                    logger = b.a;
                    sb = new StringBuilder("exited with ");
                }
                sb.append(b.e);
                logger.fine(sb.toString());
                return null;
            default:
                return System.getProperty("line.separator");
        }
    }
}
