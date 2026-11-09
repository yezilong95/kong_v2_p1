package defpackage;

import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

/* JADX INFO: loaded from: classes.dex */
public final class ua extends pw {
    public final /* synthetic */ int a;
    public final /* synthetic */ BottomAppBar b;

    public ua(BottomAppBar bottomAppBar, int i) {
        this.b = bottomAppBar;
        this.a = i;
    }

    @Override // defpackage.pw
    public final void a(FloatingActionButton floatingActionButton) {
        int i = BottomAppBar.V0;
        floatingActionButton.setTranslationX(this.b.C(this.a));
        floatingActionButton.show(new ta(this));
    }
}
