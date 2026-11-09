package defpackage;

/* JADX INFO: loaded from: classes.dex */
public final class cs extends s8 {
    public final byte[] F;
    public ds G;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public cs(byte[] bArr) {
        super(true);
        if (bArr.length != 57) {
            throw new IllegalArgumentException("'buf' must have length 57");
        }
        byte[] bArr2 = new byte[57];
        this.F = bArr2;
        System.arraycopy(bArr, 0, bArr2, 0, 57);
    }

    public final ds a() {
        ds dsVar;
        synchronized (this.F) {
            try {
                if (this.G == null) {
                    this.G = new ds(j31.p(this.F));
                }
                dsVar = this.G;
            } catch (Throwable th) {
                throw th;
            }
        }
        return dsVar;
    }
}
