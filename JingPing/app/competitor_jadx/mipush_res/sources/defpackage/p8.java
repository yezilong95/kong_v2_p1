package defpackage;

import com.google.gson.JsonSyntaxException;
import com.google.gson.stream.JsonToken;
import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public final class p8 extends e21 {
    public static final o8 d = new o8(0);
    public final /* synthetic */ int a;
    public final Object b;
    public final Object c;

    public p8(m20 m20Var, e21 e21Var, Class cls) {
        this.a = 0;
        this.b = new g21(m20Var, e21Var, cls);
        this.c = cls;
    }

    @Override // defpackage.e21
    public final Object a(a60 a60Var) {
        switch (this.a) {
            case 0:
                if (a60Var.O() == JsonToken.NULL) {
                    a60Var.K();
                    return null;
                }
                ArrayList arrayList = new ArrayList();
                a60Var.j();
                while (a60Var.B()) {
                    arrayList.add(((g21) this.b).b.a(a60Var));
                }
                a60Var.y();
                int size = arrayList.size();
                Object objNewInstance = Array.newInstance((Class<?>) this.c, size);
                for (int i = 0; i < size; i++) {
                    Array.set(objNewInstance, i, arrayList.get(i));
                }
                return objNewInstance;
            case 1:
                if (a60Var.O() == JsonToken.NULL) {
                    a60Var.K();
                    return null;
                }
                Collection collection = (Collection) ((qi0) this.c).q();
                a60Var.j();
                while (a60Var.B()) {
                    collection.add(((g21) this.b).b.a(a60Var));
                }
                a60Var.y();
                return collection;
            case 2:
                Object objA = ((h21) this.b).G.a(a60Var);
                if (objA != null) {
                    Class cls = (Class) this.c;
                    if (!cls.isInstance(objA)) {
                        throw new JsonSyntaxException("Expected a " + cls.getName() + " but was " + objA.getClass().getName());
                    }
                }
                return objA;
            default:
                if (a60Var.O() != JsonToken.NULL) {
                    return (Enum) ((HashMap) this.c).get(a60Var.M());
                }
                a60Var.K();
                return null;
        }
    }

    @Override // defpackage.e21
    public final void b(f60 f60Var, Object obj) throws IOException {
        switch (this.a) {
            case 0:
                if (obj == null) {
                    f60Var.C();
                } else {
                    f60Var.l();
                    int length = Array.getLength(obj);
                    for (int i = 0; i < length; i++) {
                        ((g21) this.b).b(f60Var, Array.get(obj, i));
                    }
                    f60Var.y();
                }
                break;
            case 1:
                Collection collection = (Collection) obj;
                if (collection == null) {
                    f60Var.C();
                } else {
                    f60Var.l();
                    Iterator it = collection.iterator();
                    while (it.hasNext()) {
                        ((g21) this.b).b(f60Var, it.next());
                    }
                    f60Var.y();
                }
                break;
            case 2:
                ((h21) this.b).G.b(f60Var, obj);
                break;
            default:
                Enum r6 = (Enum) obj;
                f60Var.I(r6 == null ? null : (String) ((HashMap) this.b).get(r6));
                break;
        }
    }

    public p8(m20 m20Var, Type type, e21 e21Var, qi0 qi0Var) {
        this.a = 1;
        this.b = new g21(m20Var, e21Var, type);
        this.c = qi0Var;
    }

    public p8(h21 h21Var, Class cls) {
        this.a = 2;
        this.b = h21Var;
        this.c = cls;
    }

    public p8(Class cls) {
        this.a = 3;
        this.c = new HashMap();
        this.b = new HashMap();
        try {
            for (Enum r4 : (Enum[]) cls.getEnumConstants()) {
                String strName = r4.name();
                su0 su0Var = (su0) cls.getField(strName).getAnnotation(su0.class);
                if (su0Var != null) {
                    strName = su0Var.value();
                    for (String str : su0Var.alternate()) {
                        ((HashMap) this.c).put(str, r4);
                    }
                }
                ((HashMap) this.c).put(strName, r4);
                ((HashMap) this.b).put(r4, strName);
            }
        } catch (NoSuchFieldException e) {
            throw new AssertionError(e);
        }
    }
}
