package defpackage;

import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class cn extends za0 {
    public final /* synthetic */ dn p;

    public cn(dn dnVar) {
        this.p = dnVar;
    }

    @Override // defpackage.za0
    public final void F(Throwable th) {
        this.p.a.d(th);
    }

    @Override // defpackage.za0
    public final void I(ba baVar) {
        dn dnVar = this.p;
        dnVar.c = baVar;
        dnVar.b = new e5(dnVar.c, new qm(), dnVar.a.h);
        hn hnVar = dnVar.a;
        hnVar.getClass();
        ArrayList arrayList = new ArrayList();
        hnVar.a.writeLock().lock();
        try {
            hnVar.c = 1;
            arrayList.addAll(hnVar.b);
            hnVar.b.clear();
            hnVar.a.writeLock().unlock();
            hnVar.d.post(new ha(arrayList, hnVar.c, (Throwable) null));
        } catch (Throwable th) {
            hnVar.a.writeLock().unlock();
            throw th;
        }
    }
}
