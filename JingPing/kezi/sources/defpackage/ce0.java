package defpackage;

/* JADX INFO: loaded from: classes.dex */
public abstract class ce0 {
    public static final de0 a;

    static {
        de0 de0Var = null;
        try {
            de0Var = (de0) Class.forName("kotlin.reflect.jvm.internal.ReflectionFactoryImpl").newInstance();
        } catch (ClassCastException | ClassNotFoundException | IllegalAccessException | InstantiationException unused) {
        }
        if (de0Var == null) {
            de0Var = new de0();
        }
        a = de0Var;
    }

    public static jd a(Class cls) {
        a.getClass();
        return new jd(cls);
    }
}
