package kotlin.reflect;

import defpackage.jo;
import defpackage.ko;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes.dex */
public final class KVariance {
    public static final KVariance IN;
    public static final KVariance INVARIANT;
    public static final KVariance OUT;
    public static final /* synthetic */ KVariance[] b;
    public static final /* synthetic */ ko c;

    static {
        KVariance kVariance = new KVariance("INVARIANT", 0);
        INVARIANT = kVariance;
        KVariance kVariance2 = new KVariance("IN", 1);
        IN = kVariance2;
        KVariance kVariance3 = new KVariance("OUT", 2);
        OUT = kVariance3;
        KVariance[] kVarianceArr = {kVariance, kVariance2, kVariance3};
        b = kVarianceArr;
        c = new ko(kVarianceArr);
    }

    public static jo getEntries() {
        return c;
    }

    public static KVariance valueOf(String str) {
        return (KVariance) Enum.valueOf(KVariance.class, str);
    }

    public static KVariance[] values() {
        return (KVariance[]) b.clone();
    }
}
