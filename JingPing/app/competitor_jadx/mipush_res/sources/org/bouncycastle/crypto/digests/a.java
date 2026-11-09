package org.bouncycastle.crypto.digests;

import defpackage.bg0;

/* JADX INFO: loaded from: classes.dex */
public final class a extends bg0 {
    public final /* synthetic */ int G;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ a(long j, int i) {
        super(j);
        this.G = i;
    }

    @Override // defpackage.bg0
    public final void a(long j) {
        switch (this.G) {
            case 0:
                SHA224NativeDigest.destroy(j);
                break;
            case 1:
                SHA256NativeDigest.destroy(j);
                break;
            case 2:
                SHA384NativeDigest.destroy(j);
                break;
            default:
                SHA512NativeDigest.destroy(j);
                break;
        }
    }
}
