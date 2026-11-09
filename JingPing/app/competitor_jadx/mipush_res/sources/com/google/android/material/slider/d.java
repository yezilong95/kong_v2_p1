package com.google.android.material.slider;

/* JADX INFO: loaded from: classes.dex */
public final class d implements Runnable {
    public int E = -1;
    public final /* synthetic */ BaseSlider F;

    public d(BaseSlider baseSlider) {
        this.F = baseSlider;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.F.L.x(this.E, 4);
    }
}
