package defpackage;

import kotlin.jvm.internal.FunctionReferenceImpl;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class pa0 extends FunctionReferenceImpl implements ou {
    public final /* synthetic */ int j;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ pa0(int i, Object obj, Class cls, String str, String str2, int i2, int i3) {
        super(i, obj, cls, str, str2, i2);
        this.j = i3;
    }

    @Override // defpackage.ou
    public final Object invoke() {
        switch (this.j) {
            case 0:
                ((qa0) this.c).c();
                break;
            default:
                ((qa0) this.c).c();
                break;
        }
        return bq0.a;
    }
}
