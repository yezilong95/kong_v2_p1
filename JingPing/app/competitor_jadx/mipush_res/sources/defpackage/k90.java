package defpackage;

import com.google.gson.internal.LinkedHashTreeMap;

/* JADX INFO: loaded from: classes.dex */
public final class k90 extends m90 {
    public final /* synthetic */ int J;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ k90(LinkedHashTreeMap linkedHashTreeMap, int i) {
        super(linkedHashTreeMap);
        this.J = i;
    }

    @Override // defpackage.m90, java.util.Iterator
    public Object next() {
        switch (this.J) {
            case 1:
                return a().J;
            default:
                return super.next();
        }
    }
}
