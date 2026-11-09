package defpackage;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.ServiceConfigurationError;

/* JADX INFO: loaded from: classes.dex */
public abstract class fj {
    public static final List a;

    static {
        try {
            Iterator it = Arrays.asList(new o4()).iterator();
            t40.n(it, "<this>");
            a = nu0.S0(new kh(new ou0(1, it)));
        } catch (Throwable th) {
            throw new ServiceConfigurationError(th.getMessage(), th);
        }
    }
}
