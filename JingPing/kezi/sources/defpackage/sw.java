package defpackage;

import java.io.IOException;
import okhttp3.internal.http2.ErrorCode;

/* JADX INFO: loaded from: classes.dex */
public final class sw extends vm0 {
    public final /* synthetic */ uw e;
    public final /* synthetic */ int f;
    public final /* synthetic */ ErrorCode g;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public sw(String str, uw uwVar, int i, ErrorCode errorCode) {
        super(str, true);
        this.e = uwVar;
        this.f = i;
        this.g = errorCode;
    }

    @Override // defpackage.vm0
    public final long a() {
        uw uwVar = this.e;
        try {
            int i = this.f;
            ErrorCode errorCode = this.g;
            uwVar.getClass();
            oy.j(errorCode, "statusCode");
            uwVar.x.G(i, errorCode);
            return -1L;
        } catch (IOException e) {
            uwVar.t(e);
            return -1L;
        }
    }
}
