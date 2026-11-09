package defpackage;

/* JADX INFO: loaded from: classes.dex */
public interface js0 {
    default gs0 a(Class cls, b80 b80Var) {
        return b(cls);
    }

    default gs0 b(Class cls) {
        throw new UnsupportedOperationException("`Factory.create(String, CreationExtras)` is not implemented. You may need to override the method and provide a custom implementation. Note that using `Factory.create(String)` is not supported and considered an error.");
    }

    default gs0 c(jd jdVar, b80 b80Var) {
        Class clsA = jdVar.a();
        oy.h(clsA, "null cannot be cast to non-null type java.lang.Class<T of kotlin.jvm.JvmClassMappingKt.<get-java>>");
        return a(clsA, b80Var);
    }
}
