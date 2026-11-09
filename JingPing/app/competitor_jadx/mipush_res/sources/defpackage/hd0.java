package defpackage;

import com.google.android.material.datepicker.m;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public final class hd0 {
    public final /* synthetic */ int a;
    public final /* synthetic */ vz b;

    public /* synthetic */ hd0(int i, vz vzVar) {
        this.a = i;
        this.b = vzVar;
    }

    public final void a() {
        switch (this.a) {
            case 0:
                ((m) this.b).k1.setEnabled(false);
                break;
            default:
                Iterator it = ((od0) this.b).v0.iterator();
                while (it.hasNext()) {
                    ((hd0) it.next()).a();
                }
                break;
        }
    }

    public final void b(Object obj) {
        switch (this.a) {
            case 0:
                m mVar = (m) this.b;
                String strB = mVar.E().b(mVar.g());
                mVar.h1.setContentDescription(mVar.E().a(mVar.y()));
                mVar.h1.setText(strB);
                mVar.k1.setEnabled(mVar.E().f());
                break;
            default:
                Iterator it = ((od0) this.b).v0.iterator();
                while (it.hasNext()) {
                    ((hd0) it.next()).b(obj);
                }
                break;
        }
    }
}
