package defpackage;

import android.os.Handler;
import android.view.View;
import android.view.Window;
import androidx.fragment.app.FragmentActivity;

/* JADX INFO: loaded from: classes.dex */
public final class wz extends z91 implements s51, x80, k00 {
    public final FragmentActivity b0;
    public final FragmentActivity c0;
    public final Handler d0;
    public final h00 e0;
    public final /* synthetic */ FragmentActivity f0;

    public wz(FragmentActivity fragmentActivity) {
        this.f0 = fragmentActivity;
        Handler handler = new Handler();
        this.e0 = new h00();
        this.b0 = fragmentActivity;
        this.c0 = fragmentActivity;
        this.d0 = handler;
    }

    @Override // defpackage.z91
    public final View W(int i) {
        return this.f0.findViewById(i);
    }

    @Override // defpackage.z91
    public final boolean X() {
        Window window = this.f0.getWindow();
        return (window == null || window.peekDecorView() == null) ? false : true;
    }

    @Override // defpackage.s51
    public final r51 d() {
        return this.f0.d();
    }

    @Override // defpackage.x80
    public final s80 getLifecycle() {
        return this.f0.X;
    }

    @Override // defpackage.k00
    public final void b() {
    }
}
