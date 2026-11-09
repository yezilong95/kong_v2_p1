package defpackage;

import com.google.gson.internal.LinkedHashTreeMap;

/* JADX INFO: loaded from: classes.dex */
public final class g20 extends i20 {
    public final /* synthetic */ int g;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ g20(LinkedHashTreeMap linkedHashTreeMap, int i) {
        super(linkedHashTreeMap);
        this.g = i;
    }

    @Override // defpackage.i20, java.util.Iterator
    public Object next() {
        switch (this.g) {
            case 1:
                return a().g;
            default:
                return super.next();
        }
    }
}
