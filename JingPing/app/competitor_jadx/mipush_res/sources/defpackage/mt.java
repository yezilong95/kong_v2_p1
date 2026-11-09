package defpackage;

import android.widget.EditText;
import androidx.appcompat.widget.SwitchCompat;
import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: classes.dex */
public final class mt extends xs {
    public final /* synthetic */ int a = 0;
    public final WeakReference b;

    public mt(EditText editText) {
        this.b = new WeakReference(editText);
    }

    @Override // defpackage.xs
    public void a() {
        switch (this.a) {
            case 1:
                SwitchCompat switchCompat = (SwitchCompat) this.b.get();
                if (switchCompat != null) {
                    switchCompat.c();
                }
                break;
        }
    }

    @Override // defpackage.xs
    public final void b() {
        switch (this.a) {
            case 0:
                nt.a((EditText) this.b.get(), 1);
                break;
            default:
                SwitchCompat switchCompat = (SwitchCompat) this.b.get();
                if (switchCompat != null) {
                    switchCompat.c();
                }
                break;
        }
    }

    public mt(SwitchCompat switchCompat) {
        this.b = new WeakReference(switchCompat);
    }
}
