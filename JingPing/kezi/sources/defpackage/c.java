package defpackage;

import android.view.View;
import androidx.appcompat.widget.ActionBarContextView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

/* JADX INFO: loaded from: classes.dex */
public final class c implements dt0 {
    public boolean a;
    public int b;
    public final View c;

    public c(FloatingActionButton floatingActionButton) {
        this.a = false;
        this.b = 0;
        this.c = floatingActionButton;
    }

    @Override // defpackage.dt0
    public void a() {
        if (this.a) {
            return;
        }
        ActionBarContextView actionBarContextView = (ActionBarContextView) this.c;
        actionBarContextView.g = null;
        super/*android.view.View*/.setVisibility(this.b);
    }

    @Override // defpackage.dt0
    public void b() {
        this.a = true;
    }

    @Override // defpackage.dt0
    public void c() {
        super/*android.view.View*/.setVisibility(0);
        this.a = false;
    }

    public c(ActionBarContextView actionBarContextView) {
        this.c = actionBarContextView;
        this.a = false;
    }
}
