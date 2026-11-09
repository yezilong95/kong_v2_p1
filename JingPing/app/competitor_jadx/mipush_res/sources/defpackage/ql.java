package defpackage;

import java.security.Provider;
import java.security.SecureRandom;
import java.security.SecureRandomSpi;

/* JADX INFO: loaded from: classes.dex */
public final class ql extends SecureRandom {
    public ql(Object[] objArr) {
        super((SecureRandomSpi) objArr[1], (Provider) objArr[0]);
    }
}
