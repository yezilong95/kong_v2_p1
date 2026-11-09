package defpackage;

/* JADX INFO: loaded from: classes.dex */
public interface q51 {
    default o51 a(Class cls) {
        throw new UnsupportedOperationException("`Factory.create(String, CreationExtras)` is not implemented. You may need to override the method and provide a custom implementation. Note that using `Factory.create(String)` is not supported and considered an error.");
    }

    default o51 b(Class cls, vf0 vf0Var) {
        return a(cls);
    }

    default o51 c(df dfVar, vf0 vf0Var) {
        Class clsA = dfVar.a();
        t40.l(clsA, "null cannot be cast to non-null type java.lang.Class<T of kotlin.jvm.JvmClassMappingKt.<get-java>>");
        return b(clsA, vf0Var);
    }
}
