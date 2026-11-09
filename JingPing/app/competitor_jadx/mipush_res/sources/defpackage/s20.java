package defpackage;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class s20 extends a80 {
    public final int F;
    public final boolean G;
    public List H;
    public List I;
    public final long J;
    public long K;

    public s20(int i, ArrayList arrayList, ArrayList arrayList2, long j, long j2, boolean z) {
        super(true);
        this.K = 0L;
        this.F = i;
        this.H = Collections.unmodifiableList(arrayList);
        this.I = Collections.unmodifiableList(arrayList2);
        this.K = j;
        this.J = j2;
        this.G = z;
    }

    public static s20 a(Object obj) {
        Throwable th;
        if (obj instanceof s20) {
            return (s20) obj;
        }
        if (obj instanceof DataInputStream) {
            DataInputStream dataInputStream = (DataInputStream) obj;
            if (dataInputStream.readInt() != 0) {
                throw new IllegalStateException("unknown version for hss private key");
            }
            int i = dataInputStream.readInt();
            long j = dataInputStream.readLong();
            long j2 = dataInputStream.readLong();
            boolean z = dataInputStream.readBoolean();
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = new ArrayList();
            for (int i2 = 0; i2 < i; i2++) {
                arrayList.add(c80.e(obj));
            }
            for (int i3 = 0; i3 < i - 1; i3++) {
                arrayList2.add(e80.a(obj));
            }
            return new s20(i, arrayList, arrayList2, j, j2, z);
        }
        if (!(obj instanceof byte[])) {
            if (obj instanceof InputStream) {
                return a(la0.Z((InputStream) obj));
            }
            throw new IllegalArgumentException("cannot parse " + obj);
        }
        DataInputStream dataInputStream2 = null;
        try {
            DataInputStream dataInputStream3 = new DataInputStream(new ByteArrayInputStream((byte[]) obj));
            try {
                s20 s20VarA = a(dataInputStream3);
                dataInputStream3.close();
                return s20VarA;
            } catch (Throwable th2) {
                th = th2;
                dataInputStream2 = dataInputStream3;
                if (dataInputStream2 == null) {
                    throw th;
                }
                dataInputStream2.close();
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:116:0x0237 A[EDGE_INSN: B:116:0x0237->B:53:0x0237 BREAK  A[LOOP:3: B:50:0x020e->B:52:0x0218], SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:48:0x0182 A[LOOP:2: B:47:0x0180->B:48:0x0182, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:52:0x0218 A[LOOP:3: B:50:0x020e->B:52:0x0218, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:58:0x026d A[LOOP:4: B:56:0x0267->B:58:0x026d, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:61:0x027c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void b() {
        /*
            Method dump skipped, instruction units count: 825
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.s20.b():void");
    }

    public final Object clone() {
        try {
            return a(getEncoded());
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || s20.class != obj.getClass()) {
            return false;
        }
        s20 s20Var = (s20) obj;
        if (this.F == s20Var.F && this.G == s20Var.G && this.J == s20Var.J && this.K == s20Var.K && this.H.equals(s20Var.H)) {
            return this.I.equals(s20Var.I);
        }
        return false;
    }

    @Override // defpackage.tt
    public final synchronized byte[] getEncoded() {
        z8 z8Var;
        try {
            z8Var = new z8(12);
            z8Var.D(0);
            z8Var.D(this.F);
            long j = this.K;
            z8Var.D((int) (j >>> 32));
            z8Var.D((int) j);
            long j2 = this.J;
            z8Var.D((int) (j2 >>> 32));
            z8Var.D((int) j2);
            ((ByteArrayOutputStream) z8Var.F).write(this.G ? 1 : 0);
            Iterator it = this.H.iterator();
            while (it.hasNext()) {
                z8Var.t((c80) it.next());
            }
            Iterator it2 = this.I.iterator();
            while (it2.hasNext()) {
                z8Var.t((e80) it2.next());
            }
        } catch (Throwable th) {
            throw th;
        }
        return ((ByteArrayOutputStream) z8Var.F).toByteArray();
    }

    public final int hashCode() {
        int iHashCode = (this.I.hashCode() + ((this.H.hashCode() + (((this.F * 31) + (this.G ? 1 : 0)) * 31)) * 31)) * 31;
        long j = this.J;
        int i = (iHashCode + ((int) (j ^ (j >>> 32)))) * 31;
        long j2 = this.K;
        return i + ((int) (j2 ^ (j2 >>> 32)));
    }
}
