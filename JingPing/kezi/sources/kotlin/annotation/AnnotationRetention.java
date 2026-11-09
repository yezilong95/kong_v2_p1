package kotlin.annotation;

import defpackage.jo;
import defpackage.ko;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes.dex */
public final class AnnotationRetention {
    public static final AnnotationRetention BINARY;
    public static final AnnotationRetention RUNTIME;
    public static final AnnotationRetention SOURCE;
    public static final /* synthetic */ AnnotationRetention[] b;
    public static final /* synthetic */ ko c;

    static {
        AnnotationRetention annotationRetention = new AnnotationRetention("SOURCE", 0);
        SOURCE = annotationRetention;
        AnnotationRetention annotationRetention2 = new AnnotationRetention("BINARY", 1);
        BINARY = annotationRetention2;
        AnnotationRetention annotationRetention3 = new AnnotationRetention("RUNTIME", 2);
        RUNTIME = annotationRetention3;
        AnnotationRetention[] annotationRetentionArr = {annotationRetention, annotationRetention2, annotationRetention3};
        b = annotationRetentionArr;
        c = new ko(annotationRetentionArr);
    }

    public static jo getEntries() {
        return c;
    }

    public static AnnotationRetention valueOf(String str) {
        return (AnnotationRetention) Enum.valueOf(AnnotationRetention.class, str);
    }

    public static AnnotationRetention[] values() {
        return (AnnotationRetention[]) b.clone();
    }
}
