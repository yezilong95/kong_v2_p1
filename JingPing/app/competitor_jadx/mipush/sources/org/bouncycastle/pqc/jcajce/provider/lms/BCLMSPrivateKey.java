package org.bouncycastle.pqc.jcajce.provider.lms;

import defpackage.a80;
import defpackage.c80;
import defpackage.k0;
import defpackage.ma0;
import defpackage.o80;
import defpackage.ol0;
import defpackage.s20;
import defpackage.z91;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.security.PrivateKey;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.bouncycastle.pqc.jcajce.interfaces.LMSPrivateKey;

/* JADX INFO: loaded from: classes.dex */
public class BCLMSPrivateKey implements PrivateKey, LMSPrivateKey {
    private static final long serialVersionUID = 8568701712864512338L;
    public transient a80 E;
    public transient k0 F;

    public BCLMSPrivateKey(a80 a80Var) {
        this.E = a80Var;
    }

    private void readObject(ObjectInputStream objectInputStream) throws ClassNotFoundException, IOException {
        objectInputStream.defaultReadObject();
        ol0 ol0VarG = ol0.g((byte[]) objectInputStream.readObject());
        this.F = ol0VarG.H;
        this.E = (a80) ma0.s(ol0VarG);
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
        objectOutputStream.writeObject(getEncoded());
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof BCLMSPrivateKey)) {
            return false;
        }
        try {
            return Arrays.equals(this.E.getEncoded(), ((BCLMSPrivateKey) obj).E.getEncoded());
        } catch (IOException unused) {
            throw new IllegalStateException("unable to perform equals");
        }
    }

    @Override // org.bouncycastle.pqc.jcajce.interfaces.LMSPrivateKey
    public LMSPrivateKey extractKeyShard(int i) {
        long j;
        long j2;
        ArrayList arrayList;
        List list;
        c80 c80Var;
        a80 a80Var = this.E;
        if (a80Var instanceof c80) {
            c80 c80Var2 = (c80) a80Var;
            synchronized (c80Var2) {
                int i2 = c80Var2.N;
                int i3 = i2 + i;
                if (i3 >= c80Var2.I) {
                    throw new IllegalArgumentException("usageCount exceeds usages remaining");
                }
                c80Var = new c80(c80Var2, i2, i3);
                c80Var2.N += i;
            }
            return new BCLMSPrivateKey(c80Var);
        }
        s20 s20Var = (s20) a80Var;
        synchronized (s20Var) {
            long j3 = s20Var.J;
            j = s20Var.K;
            long j4 = i;
            if (j3 - j < j4) {
                throw new IllegalArgumentException("usageCount exceeds usages remaining in current leaf");
            }
            j2 = j + j4;
            s20Var.K = j2;
            synchronized (s20Var) {
                list = s20Var.H;
            }
            try {
                s20 s20VarA = s20.a(new s20(s20Var.F, arrayList, new ArrayList(s20Var.I), j, j2, true).getEncoded());
                s20Var.b();
                return new BCLMSPrivateKey(s20VarA);
            } catch (Exception e) {
                throw new RuntimeException(e.getMessage(), e);
            }
        }
        arrayList = new ArrayList(list);
        synchronized (s20Var) {
            s20 s20VarA2 = s20.a(new s20(s20Var.F, arrayList, new ArrayList(s20Var.I), j, j2, true).getEncoded());
            s20Var.b();
        }
        return new BCLMSPrivateKey(s20VarA2);
    }

    @Override // java.security.Key
    public String getAlgorithm() {
        return "LMS";
    }

    @Override // java.security.Key
    public byte[] getEncoded() {
        try {
            return o80.s(this.E, this.F).getEncoded();
        } catch (IOException unused) {
            return null;
        }
    }

    @Override // java.security.Key
    public String getFormat() {
        return "PKCS#8";
    }

    @Override // org.bouncycastle.pqc.jcajce.interfaces.LMSPrivateKey
    public long getIndex() {
        long j;
        if (getUsagesRemaining() == 0) {
            throw new IllegalStateException("key exhausted");
        }
        a80 a80Var = this.E;
        if (a80Var instanceof c80) {
            return ((c80) a80Var).d();
        }
        s20 s20Var = (s20) a80Var;
        synchronized (s20Var) {
            j = s20Var.K;
        }
        return j;
    }

    @Override // org.bouncycastle.pqc.jcajce.interfaces.LMSKey
    public int getLevels() {
        a80 a80Var = this.E;
        if (a80Var instanceof c80) {
            return 1;
        }
        return ((s20) a80Var).F;
    }

    @Override // org.bouncycastle.pqc.jcajce.interfaces.LMSPrivateKey
    public long getUsagesRemaining() {
        a80 a80Var = this.E;
        if (a80Var instanceof c80) {
            c80 c80Var = (c80) a80Var;
            return c80Var.I - c80Var.d();
        }
        s20 s20Var = (s20) a80Var;
        return s20Var.J - s20Var.K;
    }

    public int hashCode() {
        try {
            return z91.E(this.E.getEncoded());
        } catch (IOException unused) {
            throw new IllegalStateException("unable to calculate hashCode");
        }
    }

    public BCLMSPrivateKey(ol0 ol0Var) throws IOException {
        this.F = ol0Var.H;
        this.E = (a80) ma0.s(ol0Var);
    }
}
