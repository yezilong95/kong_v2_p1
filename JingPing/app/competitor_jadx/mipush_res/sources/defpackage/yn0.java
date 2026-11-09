package defpackage;

/* JADX INFO: loaded from: classes.dex */
public abstract class yn0 {
    public static final ao0 a;

    static {
        ao0 ao0Var = null;
        try {
            ao0Var = (ao0) Class.forName("kotlin.reflect.jvm.internal.ReflectionFactoryImpl").newInstance();
        } catch (ClassCastException | ClassNotFoundException | IllegalAccessException | InstantiationException unused) {
        }
        if (ao0Var == null) {
            ao0Var = new ao0();
        }
        a = ao0Var;
    }

    public static df a(Class cls) {
        a.getClass();
        return new df(cls);
    }
}
