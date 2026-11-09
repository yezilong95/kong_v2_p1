package org.bouncycastle.jcajce.provider.config;

import defpackage.jx0;
import io.nats.client.support.NatsJetStreamConstants;
import java.security.BasicPermission;
import java.security.Permission;
import java.util.StringTokenizer;

/* JADX INFO: loaded from: classes.dex */
public class ProviderConfigurationPermission extends BasicPermission {
    public final String E;
    public final int F;

    public ProviderConfigurationPermission(String str) {
        super(str);
        this.E = NatsJetStreamConstants.ROLLUP_HDR_ALL;
        this.F = 63;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof ProviderConfigurationPermission) {
            ProviderConfigurationPermission providerConfigurationPermission = (ProviderConfigurationPermission) obj;
            if (this.F == providerConfigurationPermission.F && getName().equals(providerConfigurationPermission.getName())) {
                return true;
            }
        }
        return false;
    }

    @Override // java.security.BasicPermission, java.security.Permission
    public String getActions() {
        return this.E;
    }

    public int hashCode() {
        return getName().hashCode() + this.F;
    }

    @Override // java.security.BasicPermission, java.security.Permission
    public boolean implies(Permission permission) {
        if (!(permission instanceof ProviderConfigurationPermission) || !getName().equals(permission.getName())) {
            return false;
        }
        int i = ((ProviderConfigurationPermission) permission).F;
        return (this.F & i) == i;
    }

    public ProviderConfigurationPermission(String str, String str2) {
        super(str, str2);
        this.E = str2;
        StringTokenizer stringTokenizer = new StringTokenizer(jx0.c(str2), " ,");
        int i = 0;
        while (stringTokenizer.hasMoreTokens()) {
            String strNextToken = stringTokenizer.nextToken();
            if (strNextToken.equals("threadlocalecimplicitlyca")) {
                i |= 1;
            } else if (strNextToken.equals("ecimplicitlyca")) {
                i |= 2;
            } else if (strNextToken.equals("threadlocaldhdefaultparams")) {
                i |= 4;
            } else if (strNextToken.equals("dhdefaultparams")) {
                i |= 8;
            } else if (strNextToken.equals("acceptableeccurves")) {
                i |= 16;
            } else if (strNextToken.equals("additionalecparameters")) {
                i |= 32;
            } else if (strNextToken.equals(NatsJetStreamConstants.ROLLUP_HDR_ALL)) {
                i = 63;
            }
        }
        if (i == 0) {
            throw new IllegalArgumentException("unknown permissions passed to mask");
        }
        this.F = i;
    }
}
