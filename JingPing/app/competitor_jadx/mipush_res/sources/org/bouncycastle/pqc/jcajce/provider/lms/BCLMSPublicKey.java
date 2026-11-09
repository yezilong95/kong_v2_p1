package org.bouncycastle.pqc.jcajce.provider.lms;

import defpackage.a80;
import defpackage.d80;
import defpackage.gm0;
import defpackage.s90;
import defpackage.sx0;
import defpackage.t20;
import defpackage.z91;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.security.PublicKey;
import java.util.Arrays;
import org.bouncycastle.pqc.jcajce.interfaces.LMSKey;

/* JADX INFO: loaded from: classes.dex */
public class BCLMSPublicKey implements PublicKey, LMSKey {
    private static final long serialVersionUID = -5617456225328969766L;
    public transient a80 E;

    public BCLMSPublicKey(a80 a80Var) {
        this.E = a80Var;
    }

    private void readObject(ObjectInputStream objectInputStream) throws ClassNotFoundException, IOException {
        objectInputStream.defaultReadObject();
        this.E = (a80) gm0.a(sx0.g((byte[]) objectInputStream.readObject()));
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
        objectOutputStream.writeObject(getEncoded());
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof BCLMSPublicKey) {
            try {
                return Arrays.equals(this.E.getEncoded(), ((BCLMSPublicKey) obj).E.getEncoded());
            } catch (IOException unused) {
            }
        }
        return false;
    }

    @Override // java.security.Key
    public final String getAlgorithm() {
        return "LMS";
    }

    @Override // java.security.Key
    public byte[] getEncoded() {
        try {
            return s90.s(this.E).getEncoded();
        } catch (IOException unused) {
            return null;
        }
    }

    @Override // java.security.Key
    public String getFormat() {
        return "X.509";
    }

    @Override // org.bouncycastle.pqc.jcajce.interfaces.LMSKey
    public int getLevels() {
        a80 a80Var = this.E;
        if (a80Var instanceof d80) {
            return 1;
        }
        return ((t20) a80Var).F;
    }

    public int hashCode() {
        try {
            return z91.E(this.E.getEncoded());
        } catch (IOException unused) {
            return -1;
        }
    }

    public BCLMSPublicKey(sx0 sx0Var) throws IOException {
        this.E = (a80) gm0.a(sx0Var);
    }
}
