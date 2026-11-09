package org.bouncycastle.jcajce;

import defpackage.d0;
import defpackage.l4;
import defpackage.o70;
import defpackage.ol0;
import defpackage.u;
import defpackage.wk;
import defpackage.yg;
import defpackage.ze0;
import java.io.IOException;
import java.security.PrivateKey;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class CompositePrivateKey implements PrivateKey {
    public final List E;
    public final d0 F;

    public CompositePrivateKey(d0 d0Var, PrivateKey... privateKeyArr) {
        this.F = d0Var;
        if (privateKeyArr == null || privateKeyArr.length == 0) {
            throw new IllegalArgumentException("at least one private key must be provided for the composite private key");
        }
        ArrayList arrayList = new ArrayList(privateKeyArr.length);
        for (PrivateKey privateKey : privateKeyArr) {
            arrayList.add(privateKey);
        }
        this.E = Collections.unmodifiableList(arrayList);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof CompositePrivateKey) {
            CompositePrivateKey compositePrivateKey = (CompositePrivateKey) obj;
            if (compositePrivateKey.getAlgorithmIdentifier().k(this.F) && this.E.equals(compositePrivateKey.E)) {
                return true;
            }
        }
        return false;
    }

    @Override // java.security.Key
    public String getAlgorithm() {
        return this.F.r();
    }

    public d0 getAlgorithmIdentifier() {
        return this.F;
    }

    @Override // java.security.Key
    public byte[] getEncoded() {
        u uVar = new u();
        d0 d0Var = ze0.c;
        d0 d0Var2 = this.F;
        boolean zK = d0Var2.k(d0Var);
        int i = 0;
        List list = this.E;
        if (zK) {
            while (i < list.size()) {
                uVar.c(ol0.g(((PrivateKey) list.get(i)).getEncoded()));
                i++;
            }
        } else {
            while (i < list.size()) {
                uVar.c(ol0.g(((PrivateKey) list.get(i)).getEncoded()).h());
                i++;
            }
        }
        try {
            l4 l4Var = new l4(d0Var2);
            wk wkVar = new wk(uVar, 0);
            wkVar.H = -1;
            return new ol0(l4Var, wkVar, null, null).f();
        } catch (IOException e) {
            throw new IllegalStateException("unable to encode composite private key: " + e.getMessage());
        }
    }

    @Override // java.security.Key
    public String getFormat() {
        return "PKCS#8";
    }

    public List<PrivateKey> getPrivateKeys() {
        return this.E;
    }

    public int hashCode() {
        return this.E.hashCode();
    }

    public CompositePrivateKey(ol0 ol0Var) {
        try {
            if (!yg.a.containsKey(ol0Var.F.E)) {
                throw new IllegalStateException("Unable to create CompositePrivateKey from PrivateKeyInfo");
            }
            CompositePrivateKey compositePrivateKeyA = new o70().a(ol0Var);
            this.E = compositePrivateKeyA.getPrivateKeys();
            this.F = compositePrivateKeyA.getAlgorithmIdentifier();
        } catch (IOException e) {
            throw new IllegalStateException(e.getMessage(), e);
        }
    }

    public CompositePrivateKey(PrivateKey... privateKeyArr) {
        this(ze0.c, privateKeyArr);
    }
}
