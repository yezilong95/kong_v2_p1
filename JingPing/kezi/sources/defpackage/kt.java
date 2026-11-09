package defpackage;

import android.os.Handler;
import android.view.View;
import android.view.Window;
import androidx.fragment.app.FragmentActivity;

/* JADX INFO: loaded from: classes.dex */
public final class kt extends nv0 implements ls0, u10, yt {
    public final FragmentActivity t;
    public final FragmentActivity u;
    public final Handler v;
    public final vt w;
    public final /* synthetic */ FragmentActivity x;

    public kt(FragmentActivity fragmentActivity) {
        this.x = fragmentActivity;
        Handler handler = new Handler();
        this.w = new vt();
        this.t = fragmentActivity;
        this.u = fragmentActivity;
        this.v = handler;
    }

    @Override // defpackage.nv0
    public final View E(int i) {
        return this.x.findViewById(i);
    }

    @Override // defpackage.nv0
    public final boolean F() {
        Window window = this.x.getWindow();
        return (window == null || window.peekDecorView() == null) ? false : true;
    }

    @Override // defpackage.ls0
    public final ks0 d() {
        return this.x.d();
    }

    @Override // defpackage.u10
    public final p10 getLifecycle() {
        return this.x.w;
    }

    @Override // defpackage.yt
    public final void a() {
    }
}
