package defpackage;

import android.animation.TimeInterpolator;
import android.content.Context;
import android.util.Log;
import android.view.View;
import com.google.android.material.R$attr;

/* JADX INFO: loaded from: classes.dex */
public abstract class lc0 {
    public final TimeInterpolator a;
    public final View b;
    public final int c;
    public final int d;
    public final int e;
    public l9 f;

    public lc0(View view) {
        this.b = view;
        Context context = view.getContext();
        this.a = s90.V(context, R$attr.motionEasingStandardDecelerateInterpolator, lk0.b(0.0f, 0.0f, 0.0f, 1.0f));
        this.c = s90.U(context, R$attr.motionDurationMedium2, 300);
        this.d = s90.U(context, R$attr.motionDurationShort3, 150);
        this.e = s90.U(context, R$attr.motionDurationShort2, 100);
    }

    public final l9 a() {
        if (this.f == null) {
            Log.w("MaterialBackHelper", "Must call startBackProgress() and updateBackProgress() before cancelBackProgress()");
        }
        l9 l9Var = this.f;
        this.f = null;
        return l9Var;
    }
}
