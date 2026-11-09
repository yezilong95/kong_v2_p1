package defpackage;

/* JADX INFO: loaded from: classes.dex */
public class gq0 implements js0, mh {
    public static gq0 b;
    public static gq0 c;

    @Override // defpackage.js0
    public gs0 a(Class cls, b80 b80Var) {
        return b(cls);
    }

    @Override // defpackage.js0
    public gs0 b(Class cls) {
        return nv0.l(cls);
    }

    @Override // defpackage.js0
    public gs0 c(jd jdVar, b80 b80Var) {
        Class clsA = jdVar.a();
        oy.h(clsA, "null cannot be cast to non-null type java.lang.Class<T of kotlin.jvm.JvmClassMappingKt.<get-java>>");
        return a(clsA, b80Var);
    }
}
