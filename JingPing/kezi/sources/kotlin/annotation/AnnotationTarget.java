package kotlin.annotation;

import defpackage.jo;
import defpackage.ko;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes.dex */
public final class AnnotationTarget {
    public static final AnnotationTarget ANNOTATION_CLASS;
    public static final AnnotationTarget CLASS;
    public static final AnnotationTarget CONSTRUCTOR;
    public static final AnnotationTarget EXPRESSION;
    public static final AnnotationTarget FIELD;
    public static final AnnotationTarget FILE;
    public static final AnnotationTarget FUNCTION;
    public static final AnnotationTarget LOCAL_VARIABLE;
    public static final AnnotationTarget PROPERTY;
    public static final AnnotationTarget PROPERTY_GETTER;
    public static final AnnotationTarget PROPERTY_SETTER;
    public static final AnnotationTarget TYPE;
    public static final AnnotationTarget TYPEALIAS;
    public static final AnnotationTarget TYPE_PARAMETER;
    public static final AnnotationTarget VALUE_PARAMETER;
    public static final /* synthetic */ AnnotationTarget[] b;
    public static final /* synthetic */ ko c;

    static {
        AnnotationTarget annotationTarget = new AnnotationTarget("CLASS", 0);
        CLASS = annotationTarget;
        AnnotationTarget annotationTarget2 = new AnnotationTarget("ANNOTATION_CLASS", 1);
        ANNOTATION_CLASS = annotationTarget2;
        AnnotationTarget annotationTarget3 = new AnnotationTarget("TYPE_PARAMETER", 2);
        TYPE_PARAMETER = annotationTarget3;
        AnnotationTarget annotationTarget4 = new AnnotationTarget("PROPERTY", 3);
        PROPERTY = annotationTarget4;
        AnnotationTarget annotationTarget5 = new AnnotationTarget("FIELD", 4);
        FIELD = annotationTarget5;
        AnnotationTarget annotationTarget6 = new AnnotationTarget("LOCAL_VARIABLE", 5);
        LOCAL_VARIABLE = annotationTarget6;
        AnnotationTarget annotationTarget7 = new AnnotationTarget("VALUE_PARAMETER", 6);
        VALUE_PARAMETER = annotationTarget7;
        AnnotationTarget annotationTarget8 = new AnnotationTarget("CONSTRUCTOR", 7);
        CONSTRUCTOR = annotationTarget8;
        AnnotationTarget annotationTarget9 = new AnnotationTarget("FUNCTION", 8);
        FUNCTION = annotationTarget9;
        AnnotationTarget annotationTarget10 = new AnnotationTarget("PROPERTY_GETTER", 9);
        PROPERTY_GETTER = annotationTarget10;
        AnnotationTarget annotationTarget11 = new AnnotationTarget("PROPERTY_SETTER", 10);
        PROPERTY_SETTER = annotationTarget11;
        AnnotationTarget annotationTarget12 = new AnnotationTarget("TYPE", 11);
        TYPE = annotationTarget12;
        AnnotationTarget annotationTarget13 = new AnnotationTarget("EXPRESSION", 12);
        EXPRESSION = annotationTarget13;
        AnnotationTarget annotationTarget14 = new AnnotationTarget("FILE", 13);
        FILE = annotationTarget14;
        AnnotationTarget annotationTarget15 = new AnnotationTarget("TYPEALIAS", 14);
        TYPEALIAS = annotationTarget15;
        AnnotationTarget[] annotationTargetArr = {annotationTarget, annotationTarget2, annotationTarget3, annotationTarget4, annotationTarget5, annotationTarget6, annotationTarget7, annotationTarget8, annotationTarget9, annotationTarget10, annotationTarget11, annotationTarget12, annotationTarget13, annotationTarget14, annotationTarget15};
        b = annotationTargetArr;
        c = new ko(annotationTargetArr);
    }

    public static jo getEntries() {
        return c;
    }

    public static AnnotationTarget valueOf(String str) {
        return (AnnotationTarget) Enum.valueOf(AnnotationTarget.class, str);
    }

    public static AnnotationTarget[] values() {
        return (AnnotationTarget[]) b.clone();
    }
}
