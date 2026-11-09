package defpackage;

import android.util.Log;
import android.view.animation.BaseInterpolator;
import androidx.recyclerview.widget.RecyclerView;

/* JADX INFO: loaded from: classes.dex */
public final class vd0 {
    public int a;
    public int b;
    public int c;
    public int d;
    public BaseInterpolator e;
    public boolean f;
    public int g;

    public final void a(RecyclerView recyclerView) {
        int i = this.d;
        if (i >= 0) {
            this.d = -1;
            recyclerView.M(i);
            this.f = false;
            return;
        }
        if (!this.f) {
            this.g = 0;
            return;
        }
        BaseInterpolator baseInterpolator = this.e;
        if (baseInterpolator != null && this.c < 1) {
            throw new IllegalStateException("If you provide an interpolator, you must set a positive duration");
        }
        int i2 = this.c;
        if (i2 < 1) {
            throw new IllegalStateException("Scroll duration must be a positive number");
        }
        recyclerView.b0.b(this.a, this.b, i2, baseInterpolator);
        int i3 = this.g + 1;
        this.g = i3;
        if (i3 > 10) {
            Log.e("RecyclerView", "Smooth Scroll action is being updated too frequently. Make sure you are not changing it unless necessary");
        }
        this.f = false;
    }
}
