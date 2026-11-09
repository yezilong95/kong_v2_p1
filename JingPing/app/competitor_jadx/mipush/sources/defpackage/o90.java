package defpackage;

import com.google.gson.internal.LinkedTreeMap;

/* JADX INFO: loaded from: classes.dex */
public final class o90 extends m90 {
    public final /* synthetic */ int J;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ o90(LinkedTreeMap linkedTreeMap, int i) {
        super(linkedTreeMap);
        this.J = i;
    }

    @Override // defpackage.m90, java.util.Iterator
    public Object next() {
        switch (this.J) {
            case 1:
                return b().J;
            default:
                return super.next();
        }
    }
}
