package defpackage;

import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class gz implements ei {
    public final /* synthetic */ int a;
    public final /* synthetic */ Object b;

    public /* synthetic */ gz(int i, Object obj) {
        this.a = i;
        this.b = obj;
    }

    @Override // defpackage.ei
    public final void accept(Object obj) {
        switch (this.a) {
            case 0:
                hz hzVar = (hz) obj;
                if (hzVar == null) {
                    hzVar = new hz(-3);
                }
                ((i6) this.b).H(hzVar);
                return;
            default:
                hz hzVar2 = (hz) obj;
                synchronized (iz.c) {
                    try {
                        vv0 vv0Var = iz.d;
                        ArrayList arrayList = (ArrayList) vv0Var.getOrDefault((String) this.b, null);
                        if (arrayList == null) {
                            return;
                        }
                        vv0Var.remove((String) this.b);
                        for (int i = 0; i < arrayList.size(); i++) {
                            ((ei) arrayList.get(i)).accept(hzVar2);
                        }
                        return;
                    } finally {
                    }
                }
        }
    }
}
