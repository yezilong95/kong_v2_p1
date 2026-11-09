package org.bouncycastle.jcajce;

import defpackage.d0;
import defpackage.l4;
import defpackage.lk;
import defpackage.o70;
import defpackage.sx0;
import defpackage.u;
import defpackage.uk;
import defpackage.wk;
import defpackage.yg;
import defpackage.ze0;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class CompositePublicKey implements PublicKey {
    public final List E;
    public final d0 F;

    public CompositePublicKey(d0 d0Var, PublicKey... publicKeyArr) {
        this.F = d0Var;
        if (publicKeyArr == null || publicKeyArr.length == 0) {
            throw new IllegalArgumentException("at least one public key must be provided for the composite public key");
        }
        ArrayList arrayList = new ArrayList(publicKeyArr.length);
        for (PublicKey publicKey : publicKeyArr) {
            arrayList.add(publicKey);
        }
        this.E = Collections.unmodifiableList(arrayList);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof CompositePublicKey) {
            CompositePublicKey compositePublicKey = (CompositePublicKey) obj;
            if (compositePublicKey.getAlgorithmIdentifier().k(this.F) && this.E.equals(compositePublicKey.E)) {
                return true;
            }
        }
        return false;
    }

    @Override // java.security.Key
    public String getAlgorithm() {
        return (String) yg.b.get(this.F);
    }

    public d0 getAlgorithmIdentifier() {
        return this.F;
    }

    @Override // java.security.Key
    public byte[] getEncoded() {
        u uVar = new u();
        int i = 0;
        while (true) {
            List list = this.E;
            int size = list.size();
            d0 d0Var = this.F;
            if (i >= size) {
                try {
                    l4 l4Var = new l4(d0Var);
                    wk wkVar = new wk(uVar, 0);
                    wkVar.H = -1;
                    lk lkVar = new lk(wkVar.b().f(), 0);
                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                    u uVar2 = new u(2);
                    uVar2.c(l4Var);
                    uVar2.c(lkVar);
                    wk wkVar2 = new wk(uVar2, 0);
                    wkVar2.H = -1;
                    new uk(2, byteArrayOutputStream).K(wkVar2);
                    return byteArrayOutputStream.toByteArray();
                } catch (IOException e) {
                    throw new IllegalStateException("unable to encode composite public key: " + e.getMessage());
                }
            }
            boolean zK = d0Var.k(ze0.c);
            byte[] encoded = ((PublicKey) list.get(i)).getEncoded();
            if (zK) {
                uVar.c(sx0.g(encoded));
            } else {
                uVar.c(sx0.g(encoded).F);
            }
            i++;
        }
    }

    @Override // java.security.Key
    public String getFormat() {
        return "X.509";
    }

    public List<PublicKey> getPublicKeys() {
        return this.E;
    }

    public int hashCode() {
        return this.E.hashCode();
    }

    public CompositePublicKey(sx0 sx0Var) {
        try {
            if (!yg.a.containsKey(sx0Var.E.E)) {
                throw new IllegalStateException("unable to create CompositePublicKey from SubjectPublicKeyInfo");
            }
            CompositePublicKey compositePublicKeyB = new o70().b(sx0Var);
            this.E = compositePublicKeyB.getPublicKeys();
            this.F = compositePublicKeyB.getAlgorithmIdentifier();
        } catch (IOException e) {
            throw new IllegalStateException(e.getMessage(), e);
        }
    }

    public CompositePublicKey(PublicKey... publicKeyArr) {
        this(ze0.c, publicKeyArr);
    }
}
