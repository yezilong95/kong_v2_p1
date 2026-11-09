package defpackage;

import android.widget.EditText;
import androidx.appcompat.widget.SwitchCompat;
import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: classes.dex */
public final class un extends fn {
    public final /* synthetic */ int a = 0;
    public final WeakReference b;

    public un(EditText editText) {
        this.b = new WeakReference(editText);
    }

    @Override // defpackage.fn
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

    @Override // defpackage.fn
    public final void b() {
        switch (this.a) {
            case 0:
                vn.a((EditText) this.b.get(), 1);
                break;
            default:
                SwitchCompat switchCompat = (SwitchCompat) this.b.get();
                if (switchCompat != null) {
                    switchCompat.c();
                }
                break;
        }
    }

    public un(SwitchCompat switchCompat) {
        this.b = new WeakReference(switchCompat);
    }
}
