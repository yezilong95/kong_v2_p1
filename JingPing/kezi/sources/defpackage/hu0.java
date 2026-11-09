package defpackage;

import android.view.WindowInsetsAnimation;

/* JADX INFO: loaded from: classes.dex */
public final class hu0 extends iu0 {
    public final WindowInsetsAnimation e;

    public hu0(WindowInsetsAnimation windowInsetsAnimation) {
        super(0, null, 0L);
        this.e = windowInsetsAnimation;
    }

    @Override // defpackage.iu0
    public final long a() {
        return this.e.getDurationMillis();
    }

    @Override // defpackage.iu0
    public final float b() {
        return this.e.getInterpolatedFraction();
    }

    @Override // defpackage.iu0
    public final int c() {
        return this.e.getTypeMask();
    }

    @Override // defpackage.iu0
    public final void d(float f) {
        this.e.setFraction(f);
    }
}
