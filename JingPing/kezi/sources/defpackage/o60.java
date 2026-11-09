package defpackage;

import android.view.CollapsibleActionView;
import android.view.View;
import android.widget.FrameLayout;

/* JADX INFO: loaded from: classes.dex */
public final class o60 extends FrameLayout implements wd {
    public final CollapsibleActionView b;

    /* JADX WARN: Multi-variable type inference failed */
    public o60(View view) {
        super(view.getContext());
        this.b = (CollapsibleActionView) view;
        addView(view);
    }

    @Override // defpackage.wd
    public final void b() {
        this.b.onActionViewExpanded();
    }

    @Override // defpackage.wd
    public final void e() {
        this.b.onActionViewCollapsed();
    }
}
