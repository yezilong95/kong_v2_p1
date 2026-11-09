package defpackage;

/* JADX INFO: loaded from: classes.dex */
public final class iq extends jq {
    public final /* synthetic */ int e;
    public final /* synthetic */ mq f;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ iq(mq mqVar, int i) {
        super(mqVar);
        this.e = i;
        this.f = mqVar;
    }

    @Override // defpackage.jq
    public final float a() {
        switch (this.e) {
            case 0:
                mq mqVar = this.f;
                return mqVar.h + mqVar.i;
            case 1:
                mq mqVar2 = this.f;
                return mqVar2.h + mqVar2.j;
            default:
                return this.f.h;
        }
    }
}
