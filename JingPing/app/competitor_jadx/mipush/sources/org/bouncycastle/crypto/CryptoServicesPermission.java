package org.bouncycastle.crypto;

import java.security.Permission;
import java.util.HashSet;

/* JADX INFO: loaded from: classes.dex */
public class CryptoServicesPermission extends Permission {
    public static final String CONSTRAINTS = "constraints";
    public static final String DEFAULT_RANDOM = "defaultRandomConfig";
    public static final String GLOBAL_CONFIG = "globalConfig";
    public static final String THREAD_LOCAL_CONFIG = "threadLocalConfig";
    public final HashSet E;

    public CryptoServicesPermission(String str) {
        super(str);
        HashSet hashSet = new HashSet();
        this.E = hashSet;
        hashSet.add(str);
    }

    public boolean equals(Object obj) {
        return (obj instanceof CryptoServicesPermission) && this.E.equals(((CryptoServicesPermission) obj).E);
    }

    @Override // java.security.Permission
    public String getActions() {
        return this.E.toString();
    }

    public int hashCode() {
        return this.E.hashCode();
    }

    @Override // java.security.Permission
    public boolean implies(Permission permission) {
        if (!(permission instanceof CryptoServicesPermission)) {
            return false;
        }
        CryptoServicesPermission cryptoServicesPermission = (CryptoServicesPermission) permission;
        return getName().equals(cryptoServicesPermission.getName()) || this.E.containsAll(cryptoServicesPermission.E);
    }
}
