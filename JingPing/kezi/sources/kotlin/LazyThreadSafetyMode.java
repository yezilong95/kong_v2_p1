package kotlin;

import defpackage.jo;
import defpackage.ko;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes.dex */
public final class LazyThreadSafetyMode {
    public static final LazyThreadSafetyMode NONE;
    public static final LazyThreadSafetyMode PUBLICATION;
    public static final LazyThreadSafetyMode SYNCHRONIZED;
    public static final /* synthetic */ LazyThreadSafetyMode[] b;
    public static final /* synthetic */ ko c;

    static {
        LazyThreadSafetyMode lazyThreadSafetyMode = new LazyThreadSafetyMode("SYNCHRONIZED", 0);
        SYNCHRONIZED = lazyThreadSafetyMode;
        LazyThreadSafetyMode lazyThreadSafetyMode2 = new LazyThreadSafetyMode("PUBLICATION", 1);
        PUBLICATION = lazyThreadSafetyMode2;
        LazyThreadSafetyMode lazyThreadSafetyMode3 = new LazyThreadSafetyMode("NONE", 2);
        NONE = lazyThreadSafetyMode3;
        LazyThreadSafetyMode[] lazyThreadSafetyModeArr = {lazyThreadSafetyMode, lazyThreadSafetyMode2, lazyThreadSafetyMode3};
        b = lazyThreadSafetyModeArr;
        c = new ko(lazyThreadSafetyModeArr);
    }

    public static jo getEntries() {
        return c;
    }

    public static LazyThreadSafetyMode valueOf(String str) {
        return (LazyThreadSafetyMode) Enum.valueOf(LazyThreadSafetyMode.class, str);
    }

    public static LazyThreadSafetyMode[] values() {
        return (LazyThreadSafetyMode[]) b.clone();
    }
}
