package defpackage;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import javax.net.ssl.SSLSocket;

/* JADX INFO: loaded from: classes.dex */
public class i3 implements oj0 {
    public static final qm f = new qm();
    public final Class a;
    public final Method b;
    public final Method c;
    public final Method d;
    public final Method e;

    public i3(Class cls) throws NoSuchMethodException {
        this.a = cls;
        Method declaredMethod = cls.getDeclaredMethod("setUseSessionTickets", Boolean.TYPE);
        oy.i(declaredMethod, "sslSocketClass.getDeclar…:class.javaPrimitiveType)");
        this.b = declaredMethod;
        this.c = cls.getMethod("setHostname", String.class);
        this.d = cls.getMethod("getAlpnSelectedProtocol", new Class[0]);
        this.e = cls.getMethod("setAlpnProtocols", byte[].class);
    }

    @Override // defpackage.oj0
    public final String a(SSLSocket sSLSocket) {
        if (!this.a.isInstance(sSLSocket)) {
            return null;
        }
        try {
            byte[] bArr = (byte[]) this.d.invoke(sSLSocket, new Object[0]);
            if (bArr != null) {
                return new String(bArr, zb.a);
            }
            return null;
        } catch (IllegalAccessException e) {
            throw new AssertionError(e);
        } catch (InvocationTargetException e2) {
            Throwable cause = e2.getCause();
            if ((cause instanceof NullPointerException) && oy.c(((NullPointerException) cause).getMessage(), "ssl == null")) {
                return null;
            }
            throw new AssertionError(e2);
        }
    }

    @Override // defpackage.oj0
    public final boolean b(SSLSocket sSLSocket) {
        return this.a.isInstance(sSLSocket);
    }

    @Override // defpackage.oj0
    public final boolean c() {
        boolean z = h3.e;
        return h3.e;
    }

    @Override // defpackage.oj0
    public final void d(SSLSocket sSLSocket, String str, List list) {
        oy.j(list, "protocols");
        if (this.a.isInstance(sSLSocket)) {
            try {
                this.b.invoke(sSLSocket, Boolean.TRUE);
                if (str != null) {
                    this.c.invoke(sSLSocket, str);
                }
                Method method = this.e;
                rb0 rb0Var = rb0.a;
                method.invoke(sSLSocket, yo.k(list));
            } catch (IllegalAccessException e) {
                throw new AssertionError(e);
            } catch (InvocationTargetException e2) {
                throw new AssertionError(e2);
            }
        }
    }
}
