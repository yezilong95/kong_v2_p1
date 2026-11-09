package defpackage;

import android.animation.TimeInterpolator;
import android.content.Context;
import android.util.Log;
import android.view.View;
import com.google.android.material.R$attr;

/* JADX INFO: loaded from: classes.dex */
public abstract class v40 {
    public final TimeInterpolator a;
    public final View b;
    public final int c;
    public final int d;
    public final int e;
    public r7 f;

    public v40(View view) {
        this.b = view;
        Context context = view.getContext();
        this.a = k9.F(context, R$attr.motionEasingStandardDecelerateInterpolator, ob0.b(0.0f, 0.0f, 0.0f, 1.0f));
        this.c = k9.E(context, R$attr.motionDurationMedium2, 300);
        this.d = k9.E(context, R$attr.motionDurationShort3, 150);
        this.e = k9.E(context, R$attr.motionDurationShort2, 100);
    }

    public final r7 a() {
        if (this.f == null) {
            Log.w("MaterialBackHelper", "Must call startBackProgress() and updateBackProgress() before cancelBackProgress()");
        }
        r7 r7Var = this.f;
        this.f = null;
        return r7Var;
    }
}
