package defpackage;

import android.os.Handler;
import androidx.lifecycle.Lifecycle$Event;

/* JADX INFO: loaded from: classes.dex */
public final class ec0 implements u10 {
    public static final ec0 j = new ec0();
    public int b;
    public int c;
    public Handler f;
    public boolean d = true;
    public boolean e = true;
    public final w10 g = new w10(this);
    public final z1 h = new z1(17, this);
    public final d90 i = new d90(this);

    public final void a() {
        int i = this.c + 1;
        this.c = i;
        if (i == 1) {
            if (this.d) {
                this.g.e(Lifecycle$Event.ON_RESUME);
                this.d = false;
            } else {
                Handler handler = this.f;
                oy.g(handler);
                handler.removeCallbacks(this.h);
            }
        }
    }

    @Override // defpackage.u10
    public final p10 getLifecycle() {
        return this.g;
    }
}
