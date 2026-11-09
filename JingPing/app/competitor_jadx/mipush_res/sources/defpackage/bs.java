package defpackage;

import java.io.ByteArrayOutputStream;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public final class bs extends ByteArrayOutputStream {
    public final /* synthetic */ int E;

    public /* synthetic */ bs(int i) {
        this.E = i;
    }

    public void j() {
        Arrays.fill(((ByteArrayOutputStream) this).buf, (byte) 0);
    }

    public synchronized byte[] l(zr zrVar) {
        byte[] bArr;
        bArr = new byte[64];
        byte[] bArr2 = ((ByteArrayOutputStream) this).buf;
        int i = ((ByteArrayOutputStream) this).count;
        byte[] bArr3 = new byte[32];
        l21.D(zrVar.a().F, bArr3, 0);
        l21.U(zrVar.F, bArr3, (byte) 0, bArr2, i, bArr);
        reset();
        return bArr;
    }

    @Override // java.io.ByteArrayOutputStream
    public synchronized void reset() {
        switch (this.E) {
            case 0:
                synchronized (this) {
                    Arrays.fill(((ByteArrayOutputStream) this).buf, 0, ((ByteArrayOutputStream) this).count, (byte) 0);
                    ((ByteArrayOutputStream) this).count = 0;
                }
                return;
            default:
                super.reset();
                return;
        }
    }

    public synchronized boolean w(as asVar, byte[] bArr) {
        if (64 != bArr.length) {
            reset();
            return false;
        }
        boolean zW = l21.W(bArr, asVar.F, (byte) 0, ((ByteArrayOutputStream) this).buf, ((ByteArrayOutputStream) this).count);
        reset();
        return zW;
    }
}
