package defpackage;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.ServiceConfigurationError;

/* JADX INFO: loaded from: classes.dex */
public abstract class rh {
    public static final List a;

    static {
        try {
            Iterator it = Arrays.asList(new d3()).iterator();
            oy.j(it, "<this>");
            a = zh0.d0(new yf(new he(2, it)));
        } catch (Throwable th) {
            throw new ServiceConfigurationError(th.getMessage(), th);
        }
    }
}
