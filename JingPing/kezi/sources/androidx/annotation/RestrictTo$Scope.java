package androidx.annotation;

import defpackage.jo;
import defpackage.ko;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes.dex */
public final class RestrictTo$Scope {
    public static final RestrictTo$Scope GROUP_ID;
    public static final RestrictTo$Scope LIBRARY;
    public static final RestrictTo$Scope LIBRARY_GROUP;
    public static final RestrictTo$Scope LIBRARY_GROUP_PREFIX;
    public static final RestrictTo$Scope SUBCLASSES;
    public static final RestrictTo$Scope TESTS;
    public static final /* synthetic */ RestrictTo$Scope[] b;
    public static final /* synthetic */ ko c;

    static {
        RestrictTo$Scope restrictTo$Scope = new RestrictTo$Scope("LIBRARY", 0);
        LIBRARY = restrictTo$Scope;
        RestrictTo$Scope restrictTo$Scope2 = new RestrictTo$Scope("LIBRARY_GROUP", 1);
        LIBRARY_GROUP = restrictTo$Scope2;
        RestrictTo$Scope restrictTo$Scope3 = new RestrictTo$Scope("LIBRARY_GROUP_PREFIX", 2);
        LIBRARY_GROUP_PREFIX = restrictTo$Scope3;
        RestrictTo$Scope restrictTo$Scope4 = new RestrictTo$Scope("GROUP_ID", 3);
        GROUP_ID = restrictTo$Scope4;
        RestrictTo$Scope restrictTo$Scope5 = new RestrictTo$Scope("TESTS", 4);
        TESTS = restrictTo$Scope5;
        RestrictTo$Scope restrictTo$Scope6 = new RestrictTo$Scope("SUBCLASSES", 5);
        SUBCLASSES = restrictTo$Scope6;
        RestrictTo$Scope[] restrictTo$ScopeArr = {restrictTo$Scope, restrictTo$Scope2, restrictTo$Scope3, restrictTo$Scope4, restrictTo$Scope5, restrictTo$Scope6};
        b = restrictTo$ScopeArr;
        c = new ko(restrictTo$ScopeArr);
    }

    public static jo getEntries() {
        return c;
    }

    public static RestrictTo$Scope valueOf(String str) {
        return (RestrictTo$Scope) Enum.valueOf(RestrictTo$Scope.class, str);
    }

    public static RestrictTo$Scope[] values() {
        return (RestrictTo$Scope[]) b.clone();
    }
}
