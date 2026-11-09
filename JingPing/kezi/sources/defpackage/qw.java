package defpackage;

import java.io.IOException;
import java.util.List;
import okhttp3.internal.http2.ErrorCode;

/* JADX INFO: loaded from: classes.dex */
public final class qw extends vm0 {
    public final /* synthetic */ int e;
    public final /* synthetic */ uw f;
    public final /* synthetic */ int g;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ qw(String str, uw uwVar, int i, Object obj, int i2) {
        super(str, true);
        this.e = i2;
        this.f = uwVar;
        this.g = i;
    }

    @Override // defpackage.vm0
    public final long a() {
        switch (this.e) {
            case 0:
                this.f.l.getClass();
                try {
                    this.f.x.G(this.g, ErrorCode.CANCEL);
                    synchronized (this.f) {
                        this.f.z.remove(Integer.valueOf(this.g));
                    }
                    return -1L;
                } catch (IOException unused) {
                    return -1L;
                }
            case 1:
                this.f.l.getClass();
                try {
                    this.f.x.G(this.g, ErrorCode.CANCEL);
                    synchronized (this.f) {
                        this.f.z.remove(Integer.valueOf(this.g));
                    }
                    return -1L;
                } catch (IOException unused2) {
                    return -1L;
                }
            default:
                this.f.l.getClass();
                synchronized (this.f) {
                    this.f.z.remove(Integer.valueOf(this.g));
                }
                return -1L;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public qw(String str, uw uwVar, int i, List list, boolean z) {
        super(str, true);
        this.e = 0;
        this.f = uwVar;
        this.g = i;
    }
}
