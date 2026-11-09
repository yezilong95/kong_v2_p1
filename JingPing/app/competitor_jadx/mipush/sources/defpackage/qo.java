package defpackage;

/* JADX INFO: loaded from: classes.dex */
public final class qo implements po {
    public final Object a;
    public final int b;

    public qo(int i, po poVar) {
        this.a = poVar;
        this.b = i;
    }

    /* JADX WARN: Type inference failed for: r1v0, types: [java.lang.Object, po] */
    @Override // defpackage.po
    public final String b() {
        return this.a.b() + "/" + (this.b * 8);
    }

    @Override // defpackage.po
    public final int c() {
        return this.b;
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [java.lang.Object, po] */
    @Override // defpackage.po
    public final int d(int i, byte[] bArr) {
        ?? r0 = this.a;
        byte[] bArr2 = new byte[r0.c()];
        r0.d(0, bArr2);
        int i2 = this.b;
        System.arraycopy(bArr2, 0, bArr, i, i2);
        return i2;
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [java.lang.Object, po] */
    @Override // defpackage.po
    public final void e(byte[] bArr, int i, int i2) {
        this.a.e(bArr, i, i2);
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [java.lang.Object, po] */
    @Override // defpackage.po
    public final void f(byte b) {
        this.a.f(b);
    }
}
