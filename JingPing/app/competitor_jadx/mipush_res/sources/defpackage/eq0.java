package defpackage;

import org.bouncycastle.crypto.digests.SHA3NativeDigest;
import org.bouncycastle.crypto.digests.SHAKENativeDigest;

/* JADX INFO: loaded from: classes.dex */
public final class eq0 extends bg0 {
    public final /* synthetic */ int G;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ eq0(long j, int i) {
        super(j);
        this.G = i;
    }

    @Override // defpackage.bg0
    public final void a(long j) {
        switch (this.G) {
            case 0:
                SHA3NativeDigest.destroy(j);
                break;
            default:
                SHAKENativeDigest.destroy(j);
                break;
        }
    }
}
