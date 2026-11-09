package defpackage;

import androidx.appcompat.widget.ActionMenuView;
import com.google.android.material.bottomappbar.BottomAppBar;

/* JADX INFO: loaded from: classes.dex */
public final class y8 implements Runnable {
    public final /* synthetic */ ActionMenuView b;
    public final /* synthetic */ int c;
    public final /* synthetic */ boolean d;
    public final /* synthetic */ BottomAppBar e;

    public y8(BottomAppBar bottomAppBar, ActionMenuView actionMenuView, int i, boolean z) {
        this.e = bottomAppBar;
        this.b = actionMenuView;
        this.c = i;
        this.d = z;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i = this.c;
        boolean z = this.d;
        BottomAppBar bottomAppBar = this.e;
        this.b.setTranslationX(bottomAppBar.B(r3, i, z));
    }
}
