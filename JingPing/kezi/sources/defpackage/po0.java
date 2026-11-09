package defpackage;

import android.view.MenuItem;
import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.widget.b;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class po0 implements p1, g60 {
    public final /* synthetic */ Toolbar b;

    public /* synthetic */ po0(Toolbar toolbar) {
        this.b = toolbar;
    }

    @Override // defpackage.g60
    public void i(i60 i60Var) {
        Toolbar toolbar = this.b;
        b bVar = toolbar.b.u;
        if (bVar == null || !bVar.j()) {
            Iterator it = ((CopyOnWriteArrayList) toolbar.H.d).iterator();
            if (it.hasNext()) {
                throw hk.e(it);
            }
        }
    }

    @Override // defpackage.g60
    public boolean m(i60 i60Var, MenuItem menuItem) {
        this.b.getClass();
        return false;
    }
}
