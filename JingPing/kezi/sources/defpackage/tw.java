package defpackage;

import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
public final class tw extends vm0 {
    public final /* synthetic */ uw e;
    public final /* synthetic */ int f;
    public final /* synthetic */ long g;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public tw(String str, uw uwVar, int i, long j) {
        super(str, true);
        this.e = uwVar;
        this.f = i;
        this.g = j;
    }

    @Override // defpackage.vm0
    public final long a() {
        uw uwVar = this.e;
        try {
            uwVar.x.H(this.g, this.f);
            return -1L;
        } catch (IOException e) {
            uwVar.t(e);
            return -1L;
        }
    }
}
