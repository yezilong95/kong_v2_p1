package defpackage;

import java.math.BigInteger;

/* JADX INFO: loaded from: classes.dex */
public final class hl extends el {
    public static final BigInteger H = BigInteger.valueOf(1);
    public static final BigInteger I = BigInteger.valueOf(2);
    public final BigInteger G;

    /* JADX WARN: Code restructure failed: missing block: B:42:0x009c, code lost:
    
        if (1 == (defpackage.fa0.f0(r15, r1) ? 1 - (r4 & 2) : 0)) goto L52;
     */
    /* JADX WARN: Code restructure failed: missing block: B:51:0x00c0, code lost:
    
        if (defpackage.hl.H.equals(r14.modPow(r15, r1)) != false) goto L52;
     */
    /* JADX WARN: Code restructure failed: missing block: B:55:0x00cc, code lost:
    
        throw new java.lang.IllegalArgumentException("Y value does not appear to be in correct group");
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public hl(java.math.BigInteger r14, defpackage.gl r15) {
        /*
            Method dump skipped, instruction units count: 213
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.hl.<init>(java.math.BigInteger, gl):void");
    }

    @Override // defpackage.el
    public final boolean equals(Object obj) {
        return (obj instanceof hl) && ((hl) obj).G.equals(this.G) && super.equals(obj);
    }

    @Override // defpackage.el
    public final int hashCode() {
        return this.G.hashCode() ^ super.hashCode();
    }
}
