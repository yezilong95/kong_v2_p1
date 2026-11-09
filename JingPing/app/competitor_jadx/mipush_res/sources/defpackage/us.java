package defpackage;

import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class us extends z91 {
    public final /* synthetic */ vs b0;

    public us(vs vsVar) {
        this.b0 = vsVar;
    }

    @Override // defpackage.z91
    public final void V(Throwable th) {
        this.b0.a.d(th);
    }

    @Override // defpackage.z91
    public final void Y(tb tbVar) {
        vs vsVar = this.b0;
        vsVar.c = tbVar;
        vsVar.b = new i6(vsVar.c, new di(9), vsVar.a.h);
        zs zsVar = vsVar.a;
        zsVar.getClass();
        ArrayList arrayList = new ArrayList();
        zsVar.a.writeLock().lock();
        try {
            zsVar.c = 1;
            arrayList.addAll(zsVar.b);
            zsVar.b.clear();
            zsVar.a.writeLock().unlock();
            zsVar.d.post(new wb(arrayList, zsVar.c, (Throwable) null));
        } catch (Throwable th) {
            zsVar.a.writeLock().unlock();
            throw th;
        }
    }
}
