package kotlin.internal;

import defpackage.jo;
import defpackage.ko;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes.dex */
public final class RequireKotlinVersionKind {
    public static final RequireKotlinVersionKind API_VERSION;
    public static final RequireKotlinVersionKind COMPILER_VERSION;
    public static final RequireKotlinVersionKind LANGUAGE_VERSION;
    public static final /* synthetic */ RequireKotlinVersionKind[] b;
    public static final /* synthetic */ ko c;

    static {
        RequireKotlinVersionKind requireKotlinVersionKind = new RequireKotlinVersionKind("LANGUAGE_VERSION", 0);
        LANGUAGE_VERSION = requireKotlinVersionKind;
        RequireKotlinVersionKind requireKotlinVersionKind2 = new RequireKotlinVersionKind("COMPILER_VERSION", 1);
        COMPILER_VERSION = requireKotlinVersionKind2;
        RequireKotlinVersionKind requireKotlinVersionKind3 = new RequireKotlinVersionKind("API_VERSION", 2);
        API_VERSION = requireKotlinVersionKind3;
        RequireKotlinVersionKind[] requireKotlinVersionKindArr = {requireKotlinVersionKind, requireKotlinVersionKind2, requireKotlinVersionKind3};
        b = requireKotlinVersionKindArr;
        c = new ko(requireKotlinVersionKindArr);
    }

    public static jo getEntries() {
        return c;
    }

    public static RequireKotlinVersionKind valueOf(String str) {
        return (RequireKotlinVersionKind) Enum.valueOf(RequireKotlinVersionKind.class, str);
    }

    public static RequireKotlinVersionKind[] values() {
        return (RequireKotlinVersionKind[]) b.clone();
    }
}
