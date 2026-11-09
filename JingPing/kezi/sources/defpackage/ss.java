package defpackage;

import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class ss implements pg {
    public final /* synthetic */ int a;
    public final /* synthetic */ Object b;

    public /* synthetic */ ss(int i, Object obj) {
        this.a = i;
        this.b = obj;
    }

    @Override // defpackage.pg
    public final void a(Object obj) {
        switch (this.a) {
            case 0:
                ts tsVar = (ts) obj;
                if (tsVar == null) {
                    tsVar = new ts(-3);
                }
                ((e5) this.b).I(tsVar);
                return;
            default:
                ts tsVar2 = (ts) obj;
                synchronized (us.c) {
                    try {
                        fj0 fj0Var = us.d;
                        ArrayList arrayList = (ArrayList) fj0Var.getOrDefault((String) this.b, null);
                        if (arrayList == null) {
                            return;
                        }
                        fj0Var.remove((String) this.b);
                        for (int i = 0; i < arrayList.size(); i++) {
                            ((pg) arrayList.get(i)).a(tsVar2);
                        }
                        return;
                    } finally {
                    }
                }
        }
    }
}
