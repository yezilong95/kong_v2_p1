package defpackage;

import com.google.gson.internal.LinkedTreeMap;

/* JADX INFO: loaded from: classes.dex */
public final class k20 extends i20 {
    public final /* synthetic */ int g;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ k20(LinkedTreeMap linkedTreeMap, int i) {
        super(linkedTreeMap);
        this.g = i;
    }

    @Override // defpackage.i20, java.util.Iterator
    public Object next() {
        switch (this.g) {
            case 1:
                return b().g;
            default:
                return super.next();
        }
    }
}
