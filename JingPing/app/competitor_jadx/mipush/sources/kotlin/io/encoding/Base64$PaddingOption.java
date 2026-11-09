package kotlin.io.encoding;

import defpackage.gu;
import defpackage.hu;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes.dex */
public final class Base64$PaddingOption {
    public static final Base64$PaddingOption ABSENT;
    public static final Base64$PaddingOption ABSENT_OPTIONAL;
    public static final /* synthetic */ Base64$PaddingOption[] E;
    public static final /* synthetic */ hu F;
    public static final Base64$PaddingOption PRESENT;
    public static final Base64$PaddingOption PRESENT_OPTIONAL;

    static {
        Base64$PaddingOption base64$PaddingOption = new Base64$PaddingOption("PRESENT", 0);
        PRESENT = base64$PaddingOption;
        Base64$PaddingOption base64$PaddingOption2 = new Base64$PaddingOption("ABSENT", 1);
        ABSENT = base64$PaddingOption2;
        Base64$PaddingOption base64$PaddingOption3 = new Base64$PaddingOption("PRESENT_OPTIONAL", 2);
        PRESENT_OPTIONAL = base64$PaddingOption3;
        Base64$PaddingOption base64$PaddingOption4 = new Base64$PaddingOption("ABSENT_OPTIONAL", 3);
        ABSENT_OPTIONAL = base64$PaddingOption4;
        Base64$PaddingOption[] base64$PaddingOptionArr = {base64$PaddingOption, base64$PaddingOption2, base64$PaddingOption3, base64$PaddingOption4};
        E = base64$PaddingOptionArr;
        F = new hu(base64$PaddingOptionArr);
    }

    public static gu getEntries() {
        return F;
    }

    public static Base64$PaddingOption valueOf(String str) {
        return (Base64$PaddingOption) Enum.valueOf(Base64$PaddingOption.class, str);
    }

    public static Base64$PaddingOption[] values() {
        return (Base64$PaddingOption[]) E.clone();
    }
}
