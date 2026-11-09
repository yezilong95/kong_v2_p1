package defpackage;

import android.view.View;
import com.google.android.material.datepicker.m;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public final class p50 implements View.OnClickListener {
    public final /* synthetic */ int b;
    public final /* synthetic */ m c;

    public /* synthetic */ p50(m mVar, int i) {
        this.b = i;
        this.c = mVar;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        switch (this.b) {
            case 0:
                m mVar = this.c;
                Iterator it = mVar.h0.iterator();
                if (!it.hasNext()) {
                    mVar.C(false, false);
                    return;
                } else {
                    if (it.next() != null) {
                        throw new ClassCastException();
                    }
                    mVar.E().getClass();
                    throw null;
                }
            default:
                m mVar2 = this.c;
                Iterator it2 = mVar2.i0.iterator();
                while (it2.hasNext()) {
                    ((View.OnClickListener) it2.next()).onClick(view);
                }
                mVar2.C(false, false);
                return;
        }
    }
}
