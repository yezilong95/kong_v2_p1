package kotlin;

import defpackage.gu;
import defpackage.hu;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes.dex */
public final class LazyThreadSafetyMode {
    public static final /* synthetic */ LazyThreadSafetyMode[] E;
    public static final /* synthetic */ hu F;
    public static final LazyThreadSafetyMode NONE;
    public static final LazyThreadSafetyMode PUBLICATION;
    public static final LazyThreadSafetyMode SYNCHRONIZED;

    static {
        LazyThreadSafetyMode lazyThreadSafetyMode = new LazyThreadSafetyMode("SYNCHRONIZED", 0);
        SYNCHRONIZED = lazyThreadSafetyMode;
        LazyThreadSafetyMode lazyThreadSafetyMode2 = new LazyThreadSafetyMode("PUBLICATION", 1);
        PUBLICATION = lazyThreadSafetyMode2;
        LazyThreadSafetyMode lazyThreadSafetyMode3 = new LazyThreadSafetyMode("NONE", 2);
        NONE = lazyThreadSafetyMode3;
        LazyThreadSafetyMode[] lazyThreadSafetyModeArr = {lazyThreadSafetyMode, lazyThreadSafetyMode2, lazyThreadSafetyMode3};
        E = lazyThreadSafetyModeArr;
        F = new hu(lazyThreadSafetyModeArr);
    }

    public static gu getEntries() {
        return F;
    }

    public static LazyThreadSafetyMode valueOf(String str) {
        return (LazyThreadSafetyMode) Enum.valueOf(LazyThreadSafetyMode.class, str);
    }

    public static LazyThreadSafetyMode[] values() {
        return (LazyThreadSafetyMode[]) E.clone();
    }
}
