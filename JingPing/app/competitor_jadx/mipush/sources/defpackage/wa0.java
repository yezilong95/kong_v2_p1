package defpackage;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.ServiceConfigurationError;
import java.util.ServiceLoader;
import java.util.concurrent.LinkedBlockingQueue;
import kotlin.uuid.Uuid;
import org.slf4j.helpers.NOPLogger;

/* JADX INFO: loaded from: classes.dex */
public abstract class wa0 {
    public static volatile int a;
    public static final zf0 b = new zf0(1);
    public static final zf0 c = new zf0(0);
    public static final boolean d;
    public static volatile zf0 e;
    public static final String[] f;

    static {
        String property;
        try {
            property = System.getProperty("slf4j.detectLoggerNameMismatch");
        } catch (SecurityException unused) {
            property = null;
        }
        d = property == null ? false : property.equalsIgnoreCase("true");
        f = new String[]{"2.0"};
    }

    public static ArrayList a() {
        final ClassLoader classLoader = wa0.class.getClassLoader();
        ServiceLoader serviceLoaderLoad = System.getSecurityManager() == null ? ServiceLoader.load(zf0.class, classLoader) : (ServiceLoader) AccessController.doPrivileged(new PrivilegedAction() { // from class: va0
            @Override // java.security.PrivilegedAction
            public final Object run() {
                return ServiceLoader.load(zf0.class, classLoader);
            }
        });
        ArrayList arrayList = new ArrayList();
        Iterator it = serviceLoaderLoad.iterator();
        while (it.hasNext()) {
            try {
                arrayList.add((zf0) it.next());
            } catch (ServiceConfigurationError e2) {
                j31.V("A SLF4J service provider failed to instantiate:\n" + e2.getMessage());
            }
        }
        return arrayList;
    }

    public static ua0 b(Class cls) {
        int i;
        ua0 ua0VarC = c(cls.getName());
        if (d) {
            i31 i31Var = j31.a;
            Class cls2 = null;
            if (i31Var == null) {
                if (j31.b) {
                    i31Var = null;
                } else {
                    try {
                        i31Var = new i31();
                    } catch (SecurityException unused) {
                        i31Var = null;
                    }
                    j31.a = i31Var;
                    j31.b = true;
                }
            }
            if (i31Var != null) {
                Class[] classContext = i31Var.getClassContext();
                String name = j31.class.getName();
                int i2 = 0;
                while (i2 < classContext.length && !name.equals(classContext[i2].getName())) {
                    i2++;
                }
                if (i2 >= classContext.length || (i = i2 + 2) >= classContext.length) {
                    throw new IllegalStateException("Failed to find org.slf4j.helpers.Util or its caller in the stack; this should not happen");
                }
                cls2 = classContext[i];
            }
            if (cls2 != null && !cls2.isAssignableFrom(cls)) {
                j31.V("Detected logger name mismatch. Given name: \"" + ua0VarC.getName() + "\"; computed name: \"" + cls2.getName() + "\".");
                j31.V("See https://www.slf4j.org/codes.html#loggerNameMismatch for an explanation");
            }
        }
        return ua0VarC;
    }

    public static ua0 c(String str) {
        zf0 zf0Var;
        i30 i30Var;
        if (a == 0) {
            synchronized (wa0.class) {
                try {
                    if (a == 0) {
                        a = 1;
                        d();
                    }
                } finally {
                }
            }
        }
        int i = a;
        if (i == 1) {
            zf0Var = b;
        } else {
            if (i == 2) {
                throw new IllegalStateException("org.slf4j.LoggerFactory in failed state. Original exception was thrown EARLIER. See also https://www.slf4j.org/codes.html#unsuccessfulInit");
            }
            if (i == 3) {
                zf0Var = e;
            } else {
                if (i != 4) {
                    throw new IllegalStateException("Unreachable code");
                }
                zf0Var = c;
            }
        }
        switch (zf0Var.a) {
            case 0:
                i30Var = (di) zf0Var.b;
                break;
            default:
                i30Var = (ux0) zf0Var.b;
                break;
        }
        return i30Var.b(str);
    }

    public static final void d() {
        try {
            ArrayList arrayListA = a();
            g(arrayListA);
            if (arrayListA.isEmpty()) {
                a = 4;
                j31.V("No SLF4J providers were found.");
                j31.V("Defaulting to no-operation (NOP) logger implementation");
                j31.V("See https://www.slf4j.org/codes.html#noProviders for further details.");
                LinkedHashSet linkedHashSet = new LinkedHashSet();
                try {
                    ClassLoader classLoader = wa0.class.getClassLoader();
                    Enumeration<URL> systemResources = classLoader == null ? ClassLoader.getSystemResources("org/slf4j/impl/StaticLoggerBinder.class") : classLoader.getResources("org/slf4j/impl/StaticLoggerBinder.class");
                    while (systemResources.hasMoreElements()) {
                        linkedHashSet.add(systemResources.nextElement());
                    }
                } catch (IOException e2) {
                    j31.W("Error getting resources from path", e2);
                }
                f(linkedHashSet);
            } else {
                e = (zf0) arrayListA.get(0);
                e.getClass();
                a = 3;
                if (!arrayListA.isEmpty() && arrayListA.size() > 1) {
                    j31.V("Actual provider is of type [" + arrayListA.get(0) + "]");
                }
            }
            e();
            if (a == 3) {
                try {
                    switch (e.a) {
                        case 0:
                            boolean z = false;
                            for (String str : f) {
                                if ("2.0.99".startsWith(str)) {
                                    z = true;
                                }
                            }
                            if (z) {
                                return;
                            }
                            j31.V("The requested version 2.0.99 by your slf4j binding is not compatible with " + Arrays.asList(f).toString());
                            j31.V("See https://www.slf4j.org/codes.html#version_mismatch for further details.");
                            return;
                        default:
                            throw new UnsupportedOperationException();
                    }
                } catch (NoSuchFieldError unused) {
                } catch (Throwable th) {
                    j31.W("Unexpected problem occured during version sanity check", th);
                }
            }
        } catch (Exception e3) {
            a = 2;
            j31.W("Failed to instantiate SLF4J LoggerFactory", e3);
            throw new IllegalStateException("Unexpected initialization failure", e3);
        }
    }

    public static void e() {
        zf0 zf0Var = b;
        synchronized (zf0Var) {
            try {
                ((ux0) zf0Var.b).E = true;
                ux0 ux0Var = (ux0) zf0Var.b;
                ux0Var.getClass();
                for (tx0 tx0Var : new ArrayList(ux0Var.F.values())) {
                    tx0Var.F = c(tx0Var.E);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        LinkedBlockingQueue linkedBlockingQueue = ((ux0) b.b).G;
        int size = linkedBlockingQueue.size();
        ArrayList<vx0> arrayList = new ArrayList(Uuid.SIZE_BITS);
        int i = 0;
        while (linkedBlockingQueue.drainTo(arrayList, Uuid.SIZE_BITS) != 0) {
            for (vx0 vx0Var : arrayList) {
                if (vx0Var != null) {
                    tx0 tx0Var2 = vx0Var.c;
                    String str = tx0Var2.E;
                    if (tx0Var2.F == null) {
                        throw new IllegalStateException("Delegate logger cannot be null at this state.");
                    }
                    if (!(tx0Var2.F instanceof NOPLogger)) {
                        if (!tx0Var2.b()) {
                            j31.V(str);
                        } else if (tx0Var2.isEnabledForLevel(vx0Var.a) && tx0Var2.b()) {
                            try {
                                tx0Var2.H.invoke(tx0Var2.F, vx0Var);
                            } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException unused) {
                            }
                        }
                    }
                }
                int i2 = i + 1;
                if (i == 0) {
                    if (vx0Var.c.b()) {
                        j31.V("A number (" + size + ") of logging calls during the initialization phase have been intercepted and are");
                        j31.V("now being replayed. These are subject to the filtering rules of the underlying logging system.");
                        j31.V("See also https://www.slf4j.org/codes.html#replay");
                    } else if (!(vx0Var.c.F instanceof NOPLogger)) {
                        j31.V("The following set of substitute loggers may have been accessed");
                        j31.V("during the initialization phase. Logging calls during this");
                        j31.V("phase were not honored. However, subsequent logging calls to these");
                        j31.V("loggers will work as normally expected.");
                        j31.V("See also https://www.slf4j.org/codes.html#substituteLogger");
                    }
                }
                i = i2;
            }
            arrayList.clear();
        }
        ux0 ux0Var2 = (ux0) b.b;
        ux0Var2.F.clear();
        ux0Var2.G.clear();
    }

    public static void f(LinkedHashSet linkedHashSet) {
        if (linkedHashSet.isEmpty()) {
            return;
        }
        j31.V("Class path contains SLF4J bindings targeting slf4j-api versions 1.7.x or earlier.");
        Iterator it = linkedHashSet.iterator();
        while (it.hasNext()) {
            j31.V("Ignoring binding found at [" + ((URL) it.next()) + "]");
        }
        j31.V("See https://www.slf4j.org/codes.html#ignoredBindings for an explanation.");
    }

    public static void g(ArrayList arrayList) {
        if (arrayList.size() > 1) {
            j31.V("Class path contains multiple SLF4J providers.");
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                j31.V("Found provider [" + ((zf0) it.next()) + "]");
            }
            j31.V("See https://www.slf4j.org/codes.html#multiple_bindings for an explanation.");
        }
    }
}
