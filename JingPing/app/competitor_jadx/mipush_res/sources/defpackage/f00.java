package defpackage;

import androidx.fragment.app.c;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class f00 implements e00 {
    public final int a;
    public final /* synthetic */ c b;

    public f00(c cVar, int i) {
        this.b = cVar;
        this.a = i;
    }

    @Override // defpackage.e00
    public final boolean a(ArrayList arrayList, ArrayList arrayList2) {
        c cVar = this.b;
        vz vzVar = cVar.r;
        int i = this.a;
        if (vzVar == null || i >= 0 || !vzVar.f().H()) {
            return cVar.I(arrayList, arrayList2, i, 1);
        }
        return false;
    }
}
