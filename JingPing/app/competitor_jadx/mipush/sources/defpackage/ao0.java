package defpackage;

/* JADX INFO: loaded from: classes.dex */
public final class ao0 {
    public static String a(k10 k10Var) {
        String string = k10Var.getClass().getGenericInterfaces()[0].toString();
        return string.startsWith("kotlin.jvm.functions.") ? string.substring(21) : string;
    }
}
