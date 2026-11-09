package defpackage;

import android.util.Log;
import android.view.View;
import androidx.fragment.app.c;

/* JADX INFO: loaded from: classes.dex */
public final class sk implements ba0 {
    public final /* synthetic */ uk a;

    public sk(uk ukVar) {
        this.a = ukVar;
    }

    @Override // defpackage.ba0
    public final void onChanged(Object obj) {
        if (((u10) obj) != null) {
            uk ukVar = this.a;
            if (ukVar.Z) {
                View viewZ = ukVar.z();
                if (viewZ.getParent() != null) {
                    throw new IllegalStateException("DialogFragment can not be attached to a container view");
                }
                if (ukVar.d0 != null) {
                    if (c.C(3)) {
                        Log.d("FragmentManager", "DialogFragment " + this + " setting the content view on " + ukVar.d0);
                    }
                    ukVar.d0.setContentView(viewZ);
                }
            }
        }
    }
}
