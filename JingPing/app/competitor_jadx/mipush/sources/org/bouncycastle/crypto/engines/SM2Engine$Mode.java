package org.bouncycastle.crypto.engines;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes.dex */
public final class SM2Engine$Mode {
    public static final SM2Engine$Mode C1C2C3;
    public static final SM2Engine$Mode C1C3C2;
    public static final /* synthetic */ SM2Engine$Mode[] E;

    static {
        SM2Engine$Mode sM2Engine$Mode = new SM2Engine$Mode("C1C2C3", 0);
        C1C2C3 = sM2Engine$Mode;
        SM2Engine$Mode sM2Engine$Mode2 = new SM2Engine$Mode("C1C3C2", 1);
        C1C3C2 = sM2Engine$Mode2;
        E = new SM2Engine$Mode[]{sM2Engine$Mode, sM2Engine$Mode2};
    }

    public static SM2Engine$Mode valueOf(String str) {
        return (SM2Engine$Mode) Enum.valueOf(SM2Engine$Mode.class, str);
    }

    public static SM2Engine$Mode[] values() {
        return (SM2Engine$Mode[]) E.clone();
    }
}
