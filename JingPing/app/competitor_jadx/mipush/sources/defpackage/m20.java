package defpackage;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.MalformedJsonException;
import io.nats.client.support.JsonUtils;
import java.io.EOFException;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicLongArray;

/* JADX INFO: loaded from: classes.dex */
public final class m20 {
    public final ThreadLocal a;
    public final ConcurrentHashMap b;
    public final i6 c;
    public final uf d;
    public final List e;
    public final boolean f;

    static {
        new m21(Object.class);
    }

    public m20() {
        qu quVar = qu.G;
        FieldNamingPolicy fieldNamingPolicy = FieldNamingPolicy.IDENTITY;
        Map map = Collections.EMPTY_MAP;
        List list = Collections.EMPTY_LIST;
        this.a = new ThreadLocal();
        this.b = new ConcurrentHashMap();
        i6 i6Var = new i6(10);
        this.c = i6Var;
        this.f = true;
        ArrayList arrayList = new ArrayList();
        arrayList.add(k21.A);
        arrayList.add(si0.b);
        arrayList.add(quVar);
        arrayList.addAll(list);
        arrayList.add(k21.p);
        arrayList.add(k21.g);
        arrayList.add(k21.d);
        arrayList.add(k21.e);
        arrayList.add(k21.f);
        j20 j20Var = k21.k;
        arrayList.add(new i21(Long.TYPE, Long.class, j20Var));
        arrayList.add(new i21(Double.TYPE, Double.class, new j20(0)));
        arrayList.add(new i21(Float.TYPE, Float.class, new j20(1)));
        arrayList.add(k21.l);
        arrayList.add(k21.h);
        arrayList.add(k21.i);
        arrayList.add(new h21(AtomicLong.class, new k20(new k20(j20Var, 0), 2), 0));
        arrayList.add(new h21(AtomicLongArray.class, new k20(new k20(j20Var, 1), 2), 0));
        arrayList.add(k21.j);
        arrayList.add(k21.m);
        arrayList.add(k21.q);
        arrayList.add(k21.r);
        arrayList.add(new h21(BigDecimal.class, k21.n, 0));
        arrayList.add(new h21(BigInteger.class, k21.o, 0));
        arrayList.add(k21.s);
        arrayList.add(k21.t);
        arrayList.add(k21.v);
        arrayList.add(k21.w);
        arrayList.add(k21.z);
        arrayList.add(k21.u);
        arrayList.add(k21.b);
        arrayList.add(vw0.e);
        arrayList.add(k21.y);
        arrayList.add(vw0.d);
        arrayList.add(vw0.c);
        arrayList.add(k21.x);
        arrayList.add(p8.d);
        arrayList.add(k21.a);
        arrayList.add(new uf(0, i6Var));
        arrayList.add(new uf(2, i6Var));
        uf ufVar = new uf(1, i6Var);
        this.d = ufVar;
        arrayList.add(ufVar);
        arrayList.add(k21.B);
        arrayList.add(new do0(i6Var, fieldNamingPolicy, quVar, ufVar));
        this.e = Collections.unmodifiableList(arrayList);
    }

    public static void a(double d) {
        if (Double.isNaN(d) || Double.isInfinite(d)) {
            throw new IllegalArgumentException(d + " is not a valid double value as per JSON specification. To override this behavior, use GsonBuilder.serializeSpecialFloatingPointValues() method.");
        }
    }

    public final Object b(Class cls, String str) {
        Object objC = c(str, cls);
        Class cls2 = (Class) ll0.a.get(cls);
        if (cls2 != null) {
            cls = cls2;
        }
        return cls.cast(objC);
    }

    public final Object c(String str, Type type) {
        Object objA = null;
        if (str == null) {
            return null;
        }
        a60 a60Var = new a60(new StringReader(str));
        boolean z = true;
        a60Var.F = true;
        try {
            try {
                try {
                    try {
                        a60Var.O();
                    } catch (EOFException e) {
                        e = e;
                    }
                    try {
                        objA = d(new m21(type)).a(a60Var);
                    } catch (EOFException e2) {
                        e = e2;
                        z = false;
                        if (!z) {
                            throw new JsonSyntaxException(e);
                        }
                    }
                    if (objA != null) {
                        try {
                            if (a60Var.O() != JsonToken.END_DOCUMENT) {
                                throw new JsonIOException("JSON document was not fully consumed.");
                            }
                        } catch (MalformedJsonException e3) {
                            throw new JsonSyntaxException(e3);
                        } catch (IOException e4) {
                            throw new JsonIOException(e4);
                        }
                    }
                    return objA;
                } catch (IllegalStateException e5) {
                    throw new JsonSyntaxException(e5);
                }
            } catch (IOException e6) {
                throw new JsonSyntaxException(e6);
            } catch (AssertionError e7) {
                throw new AssertionError("AssertionError (GSON 2.8.5): " + e7.getMessage(), e7);
            }
        } finally {
            a60Var.F = false;
        }
    }

    public final e21 d(m21 m21Var) {
        boolean z;
        ConcurrentHashMap concurrentHashMap = this.b;
        e21 e21Var = (e21) concurrentHashMap.get(m21Var);
        if (e21Var != null) {
            return e21Var;
        }
        ThreadLocal threadLocal = this.a;
        Map map = (Map) threadLocal.get();
        if (map == null) {
            map = new HashMap();
            threadLocal.set(map);
            z = true;
        } else {
            z = false;
        }
        l20 l20Var = (l20) map.get(m21Var);
        if (l20Var != null) {
            return l20Var;
        }
        try {
            l20 l20Var2 = new l20();
            map.put(m21Var, l20Var2);
            Iterator it = this.e.iterator();
            while (it.hasNext()) {
                e21 e21VarA = ((f21) it.next()).a(this, m21Var);
                if (e21VarA != null) {
                    if (l20Var2.a != null) {
                        throw new AssertionError();
                    }
                    l20Var2.a = e21VarA;
                    concurrentHashMap.put(m21Var, e21VarA);
                    map.remove(m21Var);
                    if (z) {
                        threadLocal.remove();
                    }
                    return e21VarA;
                }
            }
            throw new IllegalArgumentException("GSON (2.8.5) cannot handle " + m21Var);
        } catch (Throwable th) {
            map.remove(m21Var);
            if (z) {
                threadLocal.remove();
            }
            throw th;
        }
    }

    public final f60 e(Writer writer) {
        f60 f60Var = new f60(writer);
        f60Var.L = false;
        return f60Var;
    }

    public final String f(Object obj) {
        if (obj != null) {
            return g(obj, obj.getClass());
        }
        StringWriter stringWriter = new StringWriter();
        try {
            h(e(stringWriter));
            return stringWriter.toString();
        } catch (IOException e) {
            throw new JsonIOException(e);
        }
    }

    public final String g(Object obj, Type type) {
        StringWriter stringWriter = new StringWriter();
        try {
            i(obj, type, e(stringWriter));
            return stringWriter.toString();
        } catch (IOException e) {
            throw new JsonIOException(e);
        }
    }

    public final void h(f60 f60Var) {
        x50 x50Var = x50.E;
        boolean z = f60Var.I;
        f60Var.I = true;
        boolean z2 = f60Var.J;
        f60Var.J = this.f;
        boolean z3 = f60Var.L;
        f60Var.L = false;
        try {
            try {
                h21 h21Var = k21.a;
                j20.d(f60Var, x50Var);
                f60Var.I = z;
                f60Var.J = z2;
                f60Var.L = z3;
            } catch (IOException e) {
                throw new JsonIOException(e);
            } catch (AssertionError e2) {
                throw new AssertionError("AssertionError (GSON 2.8.5): " + e2.getMessage(), e2);
            }
        } catch (Throwable th) {
            f60Var.I = z;
            f60Var.J = z2;
            f60Var.L = z3;
            throw th;
        }
    }

    public final void i(Object obj, Type type, f60 f60Var) {
        e21 e21VarD = d(new m21(type));
        boolean z = f60Var.I;
        f60Var.I = true;
        boolean z2 = f60Var.J;
        f60Var.J = this.f;
        boolean z3 = f60Var.L;
        f60Var.L = false;
        try {
            try {
                try {
                    e21VarD.b(f60Var, obj);
                } catch (IOException e) {
                    throw new JsonIOException(e);
                }
            } catch (AssertionError e2) {
                throw new AssertionError("AssertionError (GSON 2.8.5): " + e2.getMessage(), e2);
            }
        } finally {
            f60Var.I = z;
            f60Var.J = z2;
            f60Var.L = z3;
        }
    }

    public final String toString() {
        return "{serializeNulls:false,factories:" + this.e + ",instanceCreators:" + this.c + JsonUtils.CLOSE;
    }
}
