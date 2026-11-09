package defpackage;

import android.view.CollapsibleActionView;
import android.view.View;
import android.widget.FrameLayout;

/* JADX INFO: loaded from: classes.dex */
public final class fe0 extends FrameLayout implements rf {
    public final CollapsibleActionView E;

    /* JADX WARN: Multi-variable type inference failed */
    public fe0(View view) {
        super(view.getContext());
        this.E = (CollapsibleActionView) view;
        addView(view);
    }

    @Override // defpackage.rf
    public final void b() {
        this.E.onActionViewExpanded();
    }

    @Override // defpackage.rf
    public final void e() {
        this.E.onActionViewCollapsed();
    }
}
