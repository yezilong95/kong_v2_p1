package defpackage;

import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
public final class nw extends vm0 {
    public final /* synthetic */ uw e;
    public final /* synthetic */ int f;
    public final /* synthetic */ int g;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public nw(String str, uw uwVar, int i, int i2) {
        super(str, true);
        this.e = uwVar;
        this.f = i;
        this.g = i2;
    }

    @Override // defpackage.vm0
    public final long a() {
        int i = this.f;
        int i2 = this.g;
        uw uwVar = this.e;
        uwVar.getClass();
        try {
            uwVar.x.F(i, i2, true);
            return -1L;
        } catch (IOException e) {
            uwVar.t(e);
            return -1L;
        }
    }
}
