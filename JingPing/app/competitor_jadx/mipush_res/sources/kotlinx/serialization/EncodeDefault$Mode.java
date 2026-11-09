package kotlinx.serialization;

import defpackage.gu;
import defpackage.hu;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes.dex */
public final class EncodeDefault$Mode {
    public static final EncodeDefault$Mode ALWAYS;
    public static final /* synthetic */ EncodeDefault$Mode[] E;
    public static final /* synthetic */ hu F;
    public static final EncodeDefault$Mode NEVER;

    static {
        EncodeDefault$Mode encodeDefault$Mode = new EncodeDefault$Mode("ALWAYS", 0);
        ALWAYS = encodeDefault$Mode;
        EncodeDefault$Mode encodeDefault$Mode2 = new EncodeDefault$Mode("NEVER", 1);
        NEVER = encodeDefault$Mode2;
        EncodeDefault$Mode[] encodeDefault$ModeArr = {encodeDefault$Mode, encodeDefault$Mode2};
        E = encodeDefault$ModeArr;
        F = new hu(encodeDefault$ModeArr);
    }

    public static gu getEntries() {
        return F;
    }

    public static EncodeDefault$Mode valueOf(String str) {
        return (EncodeDefault$Mode) Enum.valueOf(EncodeDefault$Mode.class, str);
    }

    public static EncodeDefault$Mode[] values() {
        return (EncodeDefault$Mode[]) E.clone();
    }
}
