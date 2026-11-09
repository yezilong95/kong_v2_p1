package defpackage;

/* JADX INFO: loaded from: classes.dex */
public final class de0 {
    public static String a(yu yuVar) {
        String string = yuVar.getClass().getGenericInterfaces()[0].toString();
        return string.startsWith("kotlin.jvm.functions.") ? string.substring(21) : string;
    }
}
