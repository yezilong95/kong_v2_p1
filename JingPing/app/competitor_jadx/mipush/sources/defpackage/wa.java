package defpackage;

import androidx.appcompat.widget.ActionMenuView;
import com.google.android.material.bottomappbar.BottomAppBar;

/* JADX INFO: loaded from: classes.dex */
public final class wa implements Runnable {
    public final /* synthetic */ ActionMenuView E;
    public final /* synthetic */ int F;
    public final /* synthetic */ boolean G;
    public final /* synthetic */ BottomAppBar H;

    public wa(BottomAppBar bottomAppBar, ActionMenuView actionMenuView, int i, boolean z) {
        this.H = bottomAppBar;
        this.E = actionMenuView;
        this.F = i;
        this.G = z;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i = this.F;
        boolean z = this.G;
        BottomAppBar bottomAppBar = this.H;
        this.E.setTranslationX(bottomAppBar.B(r3, i, z));
    }
}
