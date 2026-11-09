package defpackage;

import android.util.Log;
import android.view.View;
import androidx.fragment.app.c;

/* JADX INFO: loaded from: classes.dex */
public final class ko implements ti0 {
    public final /* synthetic */ mo a;

    public ko(mo moVar) {
        this.a = moVar;
    }

    @Override // defpackage.ti0
    public final void onChanged(Object obj) {
        if (((x80) obj) != null) {
            mo moVar = this.a;
            if (moVar.C0) {
                View viewZ = moVar.z();
                if (viewZ.getParent() != null) {
                    throw new IllegalStateException("DialogFragment can not be attached to a container view");
                }
                if (moVar.G0 != null) {
                    if (c.B(3)) {
                        Log.d("FragmentManager", "DialogFragment " + this + " setting the content view on " + moVar.G0);
                    }
                    moVar.G0.setContentView(viewZ);
                }
            }
        }
    }
}
