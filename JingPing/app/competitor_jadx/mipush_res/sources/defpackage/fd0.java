package defpackage;

import android.view.View;
import com.google.android.material.datepicker.m;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public final class fd0 implements View.OnClickListener {
    public final /* synthetic */ int E;
    public final /* synthetic */ m F;

    public /* synthetic */ fd0(m mVar, int i) {
        this.E = i;
        this.F = mVar;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        switch (this.E) {
            case 0:
                m mVar = this.F;
                Iterator it = mVar.K0.iterator();
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
                m mVar2 = this.F;
                Iterator it2 = mVar2.L0.iterator();
                while (it2.hasNext()) {
                    ((View.OnClickListener) it2.next()).onClick(view);
                }
                mVar2.C(false, false);
                return;
        }
    }
}
