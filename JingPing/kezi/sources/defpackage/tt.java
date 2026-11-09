package defpackage;

import androidx.fragment.app.c;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class tt implements st {
    public final int a;
    public final /* synthetic */ c b;

    public tt(c cVar, int i) {
        this.b = cVar;
        this.a = i;
    }

    @Override // defpackage.st
    public final boolean a(ArrayList arrayList, ArrayList arrayList2) {
        c cVar = this.b;
        jt jtVar = cVar.r;
        int i = this.a;
        if (jtVar == null || i >= 0 || !jtVar.f().I()) {
            return cVar.J(arrayList, arrayList2, i, 1);
        }
        return false;
    }
}
