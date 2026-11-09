package defpackage;

import java.security.spec.ECParameterSpec;
import java.util.Map;
import java.util.Set;
import javax.crypto.spec.DHParameterSpec;
import org.bouncycastle.jcajce.provider.config.ProviderConfigurationPermission;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

/* JADX INFO: loaded from: classes.dex */
public final class kb implements em0 {
    public static final ProviderConfigurationPermission g = new ProviderConfigurationPermission(BouncyCastleProvider.PROVIDER_NAME, "threadLocalEcImplicitlyCa");
    public static final ProviderConfigurationPermission h = new ProviderConfigurationPermission(BouncyCastleProvider.PROVIDER_NAME, "ecImplicitlyCa");
    public static final ProviderConfigurationPermission i = new ProviderConfigurationPermission(BouncyCastleProvider.PROVIDER_NAME, "threadLocalDhDefaultParams");
    public static final ProviderConfigurationPermission j = new ProviderConfigurationPermission(BouncyCastleProvider.PROVIDER_NAME, "DhDefaultParams");
    public static final ProviderConfigurationPermission k = new ProviderConfigurationPermission(BouncyCastleProvider.PROVIDER_NAME, "acceptableEcCurves");
    public static final ProviderConfigurationPermission l = new ProviderConfigurationPermission(BouncyCastleProvider.PROVIDER_NAME, "additionalEcParameters");
    public ThreadLocal a;
    public ThreadLocal b;
    public volatile kr c;
    public volatile Object d;
    public volatile Set e;
    public volatile Map f;

    public final kr a() {
        kr krVar = (kr) this.a.get();
        return krVar != null ? krVar : this.c;
    }

    public final void b(String str, Object obj) {
        SecurityManager securityManager = System.getSecurityManager();
        if (str.equals("threadLocalEcImplicitlyCa")) {
            if (securityManager != null) {
                securityManager.checkPermission(g);
            }
            kr krVarX = ((obj instanceof kr) || obj == null) ? (kr) obj : t40.x((ECParameterSpec) obj);
            if (krVarX == null) {
                this.a.remove();
                return;
            } else {
                this.a.set(krVarX);
                return;
            }
        }
        if (str.equals("ecImplicitlyCa")) {
            if (securityManager != null) {
                securityManager.checkPermission(h);
            }
            if ((obj instanceof kr) || obj == null) {
                this.c = (kr) obj;
                return;
            } else {
                this.c = t40.x((ECParameterSpec) obj);
                return;
            }
        }
        if (str.equals("threadLocalDhDefaultParams")) {
            if (securityManager != null) {
                securityManager.checkPermission(i);
            }
            if (!(obj instanceof DHParameterSpec) && !(obj instanceof DHParameterSpec[]) && obj != null) {
                throw new IllegalArgumentException("not a valid DHParameterSpec");
            }
            ThreadLocal threadLocal = this.b;
            if (obj == null) {
                threadLocal.remove();
                return;
            } else {
                threadLocal.set(obj);
                return;
            }
        }
        if (str.equals("DhDefaultParams")) {
            if (securityManager != null) {
                securityManager.checkPermission(j);
            }
            if (!(obj instanceof DHParameterSpec) && !(obj instanceof DHParameterSpec[]) && obj != null) {
                throw new IllegalArgumentException("not a valid DHParameterSpec or DHParameterSpec[]");
            }
            this.d = obj;
            return;
        }
        if (str.equals("acceptableEcCurves")) {
            if (securityManager != null) {
                securityManager.checkPermission(k);
            }
            this.e = (Set) obj;
        } else if (str.equals("additionalEcParameters")) {
            if (securityManager != null) {
                securityManager.checkPermission(l);
            }
            this.f = (Map) obj;
        }
    }
}
