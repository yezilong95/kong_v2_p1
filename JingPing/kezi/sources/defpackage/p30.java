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
public abstract class p30 {
    public static volatile int a;
    public static final h80 b = new h80(1);
    public static final h80 c = new h80(0);
    public static final boolean d;
    public static volatile h80 e;
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
        final ClassLoader classLoader = p30.class.getClassLoader();
        ServiceLoader serviceLoaderLoad = System.getSecurityManager() == null ? ServiceLoader.load(h80.class, classLoader) : (ServiceLoader) AccessController.doPrivileged(new PrivilegedAction() { // from class: o30
            @Override // java.security.PrivilegedAction
            public final Object run() {
                return ServiceLoader.load(h80.class, classLoader);
            }
        });
        ArrayList arrayList = new ArrayList();
        Iterator it = serviceLoaderLoad.iterator();
        while (it.hasNext()) {
            try {
                arrayList.add((h80) it.next());
            } catch (ServiceConfigurationError e2) {
                jq0.a0("A SLF4J service provider failed to instantiate:\n" + e2.getMessage());
            }
        }
        return arrayList;
    }

    public static n30 b(Class cls) {
        int i;
        n30 n30VarC = c(cls.getName());
        if (d) {
            iq0 iq0Var = jq0.a;
            Class cls2 = null;
            if (iq0Var == null) {
                if (jq0.b) {
                    iq0Var = null;
                } else {
                    try {
                        iq0Var = new iq0();
                    } catch (SecurityException unused) {
                        iq0Var = null;
                    }
                    jq0.a = iq0Var;
                    jq0.b = true;
                }
            }
            if (iq0Var != null) {
                Class[] classContext = iq0Var.getClassContext();
                String name = jq0.class.getName();
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
                jq0.a0("Detected logger name mismatch. Given name: \"" + n30VarC.getName() + "\"; computed name: \"" + cls2.getName() + "\".");
                jq0.a0("See https://www.slf4j.org/codes.html#loggerNameMismatch for an explanation");
            }
        }
        return n30VarC;
    }

    public static n30 c(String str) {
        h80 h80Var;
        jx jxVar;
        if (a == 0) {
            synchronized (p30.class) {
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
            h80Var = b;
        } else {
            if (i == 2) {
                throw new IllegalStateException("org.slf4j.LoggerFactory in failed state. Original exception was thrown EARLIER. See also https://www.slf4j.org/codes.html#unsuccessfulInit");
            }
            if (i == 3) {
                h80Var = e;
            } else {
                if (i != 4) {
                    throw new IllegalStateException("Unreachable code");
                }
                h80Var = c;
            }
        }
        switch (h80Var.a) {
            case 0:
                jxVar = (yo) h80Var.b;
                break;
            default:
                jxVar = (gl0) h80Var.b;
                break;
        }
        return jxVar.c(str);
    }

    public static final void d() {
        try {
            ArrayList arrayListA = a();
            g(arrayListA);
            if (arrayListA.isEmpty()) {
                a = 4;
                jq0.a0("No SLF4J providers were found.");
                jq0.a0("Defaulting to no-operation (NOP) logger implementation");
                jq0.a0("See https://www.slf4j.org/codes.html#noProviders for further details.");
                LinkedHashSet linkedHashSet = new LinkedHashSet();
                try {
                    ClassLoader classLoader = p30.class.getClassLoader();
                    Enumeration<URL> systemResources = classLoader == null ? ClassLoader.getSystemResources("org/slf4j/impl/StaticLoggerBinder.class") : classLoader.getResources("org/slf4j/impl/StaticLoggerBinder.class");
                    while (systemResources.hasMoreElements()) {
                        linkedHashSet.add(systemResources.nextElement());
                    }
                } catch (IOException e2) {
                    System.err.println("Error getting resources from path");
                    System.err.println("Reported exception:");
                    e2.printStackTrace();
                }
                f(linkedHashSet);
            } else {
                e = (h80) arrayListA.get(0);
                e.getClass();
                a = 3;
                if (!arrayListA.isEmpty() && arrayListA.size() > 1) {
                    jq0.a0("Actual provider is of type [" + arrayListA.get(0) + "]");
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
                            jq0.a0("The requested version 2.0.99 by your slf4j binding is not compatible with " + Arrays.asList(f).toString());
                            jq0.a0("See https://www.slf4j.org/codes.html#version_mismatch for further details.");
                            return;
                        default:
                            throw new UnsupportedOperationException();
                    }
                } catch (NoSuchFieldError unused) {
                } catch (Throwable th) {
                    System.err.println("Unexpected problem occured during version sanity check");
                    System.err.println("Reported exception:");
                    th.printStackTrace();
                }
            }
        } catch (Exception e3) {
            a = 2;
            System.err.println("Failed to instantiate SLF4J LoggerFactory");
            System.err.println("Reported exception:");
            e3.printStackTrace();
            throw new IllegalStateException("Unexpected initialization failure", e3);
        }
    }

    public static void e() {
        h80 h80Var = b;
        synchronized (h80Var) {
            try {
                ((gl0) h80Var.b).b = true;
                gl0 gl0Var = (gl0) h80Var.b;
                gl0Var.getClass();
                for (fl0 fl0Var : new ArrayList(gl0Var.c.values())) {
                    fl0Var.c = c(fl0Var.b);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        LinkedBlockingQueue linkedBlockingQueue = ((gl0) b.b).d;
        int size = linkedBlockingQueue.size();
        ArrayList<hl0> arrayList = new ArrayList(Uuid.SIZE_BITS);
        int i = 0;
        while (linkedBlockingQueue.drainTo(arrayList, Uuid.SIZE_BITS) != 0) {
            for (hl0 hl0Var : arrayList) {
                if (hl0Var != null) {
                    fl0 fl0Var2 = hl0Var.c;
                    String str = fl0Var2.b;
                    if (fl0Var2.c == null) {
                        throw new IllegalStateException("Delegate logger cannot be null at this state.");
                    }
                    if (!(fl0Var2.c instanceof NOPLogger)) {
                        if (!fl0Var2.b()) {
                            jq0.a0(str);
                        } else if (fl0Var2.isEnabledForLevel(hl0Var.a) && fl0Var2.b()) {
                            try {
                                fl0Var2.e.invoke(fl0Var2.c, hl0Var);
                            } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException unused) {
                            }
                        }
                    }
                }
                int i2 = i + 1;
                if (i == 0) {
                    if (hl0Var.c.b()) {
                        jq0.a0("A number (" + size + ") of logging calls during the initialization phase have been intercepted and are");
                        jq0.a0("now being replayed. These are subject to the filtering rules of the underlying logging system.");
                        jq0.a0("See also https://www.slf4j.org/codes.html#replay");
                    } else if (!(hl0Var.c.c instanceof NOPLogger)) {
                        jq0.a0("The following set of substitute loggers may have been accessed");
                        jq0.a0("during the initialization phase. Logging calls during this");
                        jq0.a0("phase were not honored. However, subsequent logging calls to these");
                        jq0.a0("loggers will work as normally expected.");
                        jq0.a0("See also https://www.slf4j.org/codes.html#substituteLogger");
                    }
                }
                i = i2;
            }
            arrayList.clear();
        }
        gl0 gl0Var2 = (gl0) b.b;
        gl0Var2.c.clear();
        gl0Var2.d.clear();
    }

    public static void f(LinkedHashSet linkedHashSet) {
        if (linkedHashSet.isEmpty()) {
            return;
        }
        jq0.a0("Class path contains SLF4J bindings targeting slf4j-api versions 1.7.x or earlier.");
        Iterator it = linkedHashSet.iterator();
        while (it.hasNext()) {
            jq0.a0("Ignoring binding found at [" + ((URL) it.next()) + "]");
        }
        jq0.a0("See https://www.slf4j.org/codes.html#ignoredBindings for an explanation.");
    }

    public static void g(ArrayList arrayList) {
        if (arrayList.size() > 1) {
            jq0.a0("Class path contains multiple SLF4J providers.");
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                jq0.a0("Found provider [" + ((h80) it.next()) + "]");
            }
            jq0.a0("See https://www.slf4j.org/codes.html#multiple_bindings for an explanation.");
        }
    }
}
