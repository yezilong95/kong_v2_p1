package defpackage;

import com.google.android.material.datepicker.m;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public final class q50 {
    public final /* synthetic */ int a;
    public final /* synthetic */ jt b;

    public /* synthetic */ q50(int i, jt jtVar) {
        this.a = i;
        this.b = jtVar;
    }

    public final void a() {
        switch (this.a) {
            case 0:
                ((m) this.b).D0.setEnabled(false);
                break;
            default:
                Iterator it = ((x50) this.b).S.iterator();
                while (it.hasNext()) {
                    ((q50) it.next()).a();
                }
                break;
        }
    }

    public final void b(Object obj) {
        switch (this.a) {
            case 0:
                m mVar = (m) this.b;
                String strC = mVar.E().c(mVar.g());
                mVar.A0.setContentDescription(mVar.E().b(mVar.y()));
                mVar.A0.setText(strC);
                mVar.D0.setEnabled(mVar.E().g());
                break;
            default:
                Iterator it = ((x50) this.b).S.iterator();
                while (it.hasNext()) {
                    ((q50) it.next()).b(obj);
                }
                break;
        }
    }
}
