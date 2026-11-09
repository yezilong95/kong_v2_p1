package defpackage;

import java.io.IOException;
import okhttp3.internal.http2.ErrorCode;

/* JADX INFO: loaded from: classes.dex */
public final class pw extends vm0 {
    public final /* synthetic */ uw e;
    public final /* synthetic */ int f;
    public final /* synthetic */ p9 g;
    public final /* synthetic */ int h;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public pw(String str, uw uwVar, int i, p9 p9Var, int i2, boolean z) {
        super(str, true);
        this.e = uwVar;
        this.f = i;
        this.g = p9Var;
        this.h = i2;
    }

    @Override // defpackage.vm0
    public final long a() {
        try {
            qm qmVar = this.e.l;
            p9 p9Var = this.g;
            int i = this.h;
            qmVar.getClass();
            p9Var.I(i);
            this.e.x.G(this.f, ErrorCode.CANCEL);
            synchronized (this.e) {
                this.e.z.remove(Integer.valueOf(this.f));
            }
            return -1L;
        } catch (IOException unused) {
            return -1L;
        }
    }
}
