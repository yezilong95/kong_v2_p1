package defpackage;

import io.nats.client.support.NatsConstants;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

/* JADX INFO: loaded from: classes.dex */
public abstract class a90 {
    public static final HashMap a = new HashMap();
    public static final HashMap b = new HashMap();

    public static void a(Constructor constructor, w80 w80Var) {
        try {
            t40.k(constructor.newInstance(w80Var));
            throw new ClassCastException();
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e2) {
            throw new RuntimeException(e2);
        } catch (InvocationTargetException e3) {
            throw new RuntimeException(e3);
        }
    }

    public static final String b(String str) {
        StringBuilder sb = new StringBuilder();
        int iM0 = qx0.m0(str, NatsConstants.DOT, 0, false);
        if (iM0 >= 0) {
            int length = str.length();
            if (length < 0) {
                throw new OutOfMemoryError();
            }
            StringBuilder sb2 = new StringBuilder(length);
            int i = 0;
            do {
                sb2.append((CharSequence) str, i, iM0);
                sb2.append("_");
                i = iM0 + 1;
                if (iM0 >= str.length()) {
                    break;
                }
                iM0 = qx0.m0(str, NatsConstants.DOT, i, false);
            } while (iM0 > 0);
            sb2.append((CharSequence) str, i, str.length());
            str = sb2.toString();
            t40.m(str, "toString(...)");
        }
        return zn.o(sb, str, "_LifecycleAdapter");
    }

    /* JADX WARN: Removed duplicated region for block: B:61:0x0105  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x0107  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x010a  */
    /* JADX WARN: Removed duplicated region for block: B:96:0x0146 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static int c(java.lang.Class r13) {
        /*
            Method dump skipped, instruction units count: 350
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.a90.c(java.lang.Class):int");
    }
}
