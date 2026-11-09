package kotlin.reflect;

import defpackage.jo;
import defpackage.ko;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes.dex */
public final class KParameter$Kind {
    public static final KParameter$Kind EXTENSION_RECEIVER;
    public static final KParameter$Kind INSTANCE;
    public static final KParameter$Kind VALUE;
    public static final /* synthetic */ KParameter$Kind[] b;
    public static final /* synthetic */ ko c;

    static {
        KParameter$Kind kParameter$Kind = new KParameter$Kind("INSTANCE", 0);
        INSTANCE = kParameter$Kind;
        KParameter$Kind kParameter$Kind2 = new KParameter$Kind("EXTENSION_RECEIVER", 1);
        EXTENSION_RECEIVER = kParameter$Kind2;
        KParameter$Kind kParameter$Kind3 = new KParameter$Kind("VALUE", 2);
        VALUE = kParameter$Kind3;
        KParameter$Kind[] kParameter$KindArr = {kParameter$Kind, kParameter$Kind2, kParameter$Kind3};
        b = kParameter$KindArr;
        c = new ko(kParameter$KindArr);
    }

    public static jo getEntries() {
        return c;
    }

    public static KParameter$Kind valueOf(String str) {
        return (KParameter$Kind) Enum.valueOf(KParameter$Kind.class, str);
    }

    public static KParameter$Kind[] values() {
        return (KParameter$Kind[]) b.clone();
    }
}
