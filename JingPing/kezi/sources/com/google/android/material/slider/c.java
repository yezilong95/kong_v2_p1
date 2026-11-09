package com.google.android.material.slider;

/* JADX INFO: loaded from: classes.dex */
public final class c implements Runnable {
    public int b = -1;
    public final /* synthetic */ BaseSlider c;

    public c(BaseSlider baseSlider) {
        this.c = baseSlider;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.c.h.x(this.b, 4);
    }
}
