package defpackage;

/* JADX INFO: loaded from: classes.dex */
public final class zr extends s8 {
    public final byte[] F;
    public as G;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zr(byte[] bArr) {
        super(true);
        if (bArr.length != 32) {
            throw new IllegalArgumentException("'buf' must have length 32");
        }
        byte[] bArr2 = new byte[32];
        this.F = bArr2;
        System.arraycopy(bArr, 0, bArr2, 0, 32);
    }

    public final as a() {
        as asVar;
        synchronized (this.F) {
            try {
                if (this.G == null) {
                    this.G = new as(l21.G(this.F));
                }
                asVar = this.G;
            } catch (Throwable th) {
                throw th;
            }
        }
        return asVar;
    }
}
