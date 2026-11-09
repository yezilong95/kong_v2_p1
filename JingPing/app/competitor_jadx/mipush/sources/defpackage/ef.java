package defpackage;

import java.security.PrivilegedAction;
import java.security.Security;

/* JADX INFO: loaded from: classes.dex */
public final class ef implements PrivilegedAction {
    public final /* synthetic */ int a;
    public final /* synthetic */ String b;

    public /* synthetic */ ef(String str, int i) {
        this.a = i;
        this.b = str;
    }

    @Override // java.security.PrivilegedAction
    public final Object run() {
        switch (this.a) {
            case 0:
                try {
                    return ClassLoader.getSystemClassLoader().loadClass(this.b);
                } catch (Exception unused) {
                    return null;
                }
            case 1:
                String str = this.b;
                try {
                    return (sl) z91.P(wl.class, str).newInstance();
                } catch (Exception e) {
                    throw new IllegalStateException("entropy source " + str + " not created: " + e.getMessage(), e);
                }
            case 2:
                return Security.getProperty(this.b);
            default:
                return System.getProperty(this.b);
        }
    }
}
