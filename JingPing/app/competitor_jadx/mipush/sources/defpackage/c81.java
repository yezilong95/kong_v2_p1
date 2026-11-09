package defpackage;

import android.view.WindowInsetsAnimation;

/* JADX INFO: loaded from: classes.dex */
public final class c81 extends d81 {
    public final WindowInsetsAnimation e;

    public c81(WindowInsetsAnimation windowInsetsAnimation) {
        super(0, null, 0L);
        this.e = windowInsetsAnimation;
    }

    @Override // defpackage.d81
    public final long a() {
        return this.e.getDurationMillis();
    }

    @Override // defpackage.d81
    public final float b() {
        return this.e.getInterpolatedFraction();
    }

    @Override // defpackage.d81
    public final int c() {
        return this.e.getTypeMask();
    }

    @Override // defpackage.d81
    public final void d(float f) {
        this.e.setFraction(f);
    }
}
